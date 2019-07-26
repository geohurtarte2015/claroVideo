
package controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.TransactionVas;
import dao.TransactionVasWsResp;
import estructura.DataTableObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.ResumeError;
import pojos.TbTransactionVas;
import pojos.TransactionVasResult;


public class ServletGraphs extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          try {
              
            TransactionVas transactionVas = new TransactionVas();  
            List<Object> vasGroupByCountry = transactionVas.getTransactionVasGroupByCountry();
            List<Object> vasGroupByCountrySuccess = transactionVas.getTransactionVasGroupByCountrySuccess();
            List<Object> vasGroupByCountryUnknown = transactionVas.getTransactionVasGroupByCountryUnknown();
            List<Object> vasGroupByCountryIncomplete = transactionVas.getTransactionVasGroupByError("-101");
            List<Object> vasGroupByCountryInvalid = transactionVas.getTransactionVasGroupByError("-102");
            List<Object> vasGroupByCountryNotExists = transactionVas.getTransactionVasGroupByError("-103");
            List<Object> vasGroupByCountryErrorIntern = transactionVas.getTransactionVasGroupByError("-104");
            List<Object> vasGroupByCountryServiceExists = transactionVas.getTransactionVasGroupByError("-999");
   

            List<ResumeError> arrayIncomplete = getArrayError(vasGroupByCountryIncomplete,vasGroupByCountry);
            List<ResumeError> arrayInvalid = getArrayError(vasGroupByCountryInvalid,vasGroupByCountry);
            List<ResumeError> arrayNotExists = getArrayError(vasGroupByCountryNotExists,vasGroupByCountry);
            List<ResumeError> arrayErrorIntern = getArrayError(vasGroupByCountryErrorIntern,vasGroupByCountry);
            List<ResumeError> arrayServiceExits = getArrayError(vasGroupByCountryServiceExists,vasGroupByCountry);
            List<ResumeError> arraySuccess = getArrayError(vasGroupByCountrySuccess,vasGroupByCountry);
            List<ResumeError> arrayUnknown = getArrayError(vasGroupByCountryUnknown,vasGroupByCountry);
            
            
            //System.out.println(pais+error+cantidad);
            //System.out.println(vasGroupByCountryError.get(0).getCountryCode()+vasGroupByCountryError.get(0).getWsResp());
            
            
            request.setAttribute("arrayIncomplete",arrayIncomplete);
            request.setAttribute("arrayInvalid",arrayInvalid);
            request.setAttribute("arrayNotExists",arrayNotExists);
            request.setAttribute("arrayErrorIntern",arrayErrorIntern);
            request.setAttribute("arrayServiceExits",arrayServiceExits);
            request.setAttribute("arraySuccess",arraySuccess);
            request.setAttribute("arrayUnknown",arrayUnknown);
            request.setAttribute("vasGroupByCountry",vasGroupByCountry);
            
            
            request.getRequestDispatcher("charts.jsp").forward(request, response);
          
            
            //response.sendRedirect("charts.jsp");
        
            
        } finally {
            out.close();
        }
        
    }
    
    List<ResumeError> getArrayError(List<Object> typeError, List<Object> vasGroupByCountry){
        
          List<ResumeError> arrayIncomplete = new ArrayList();
            
            
            ResumeError resumeError = null;
            for(int fila=0;fila<vasGroupByCountry.size();fila++){
                String pais = (String)vasGroupByCountry.get(fila);
                    //si no existe errores hacer funcion
                    resumeError = new ResumeError();
                    resumeError.setCodeCountry(pais);
                    resumeError.setTotal(new Long(0));
                    if(!typeError.isEmpty()){
                    for(int i=0;i<typeError.size();i++){
                        Object[] arrayPaisError = (Object[]) typeError.get(i);
                        String paisError=(String)arrayPaisError[0];
                        Long TotalError=(Long)arrayPaisError[1];
                            if(pais.contains(paisError)){
                                resumeError = new ResumeError();
                                resumeError.setCodeCountry(paisError);
                                resumeError.setTotal(TotalError);
                                }    
                        }
                    }
                    arrayIncomplete.add(resumeError);
            }
    
    
     return arrayIncomplete;
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    

    
}
