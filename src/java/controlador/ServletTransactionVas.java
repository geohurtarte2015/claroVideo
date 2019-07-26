
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
import pojos.TbTransactionVas;


public class ServletTransactionVas extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            String dateInit = String.valueOf(request.getParameter("dateinit"));
            String dateFinal = String.valueOf(request.getParameter("datefinish"));
            
            
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();           
            TransactionVas transactionVas = new TransactionVas();  
            TransactionVasWsResp transactionVasWsResp = new TransactionVasWsResp();  
            //List<TbTransactionVas> tbTransactionVasList = transactionVas.getTransactionVasAll();
            List<TbTransactionVas> tbTransactionVasList = transactionVas.getTransactionVasDate(dateInit, dateFinal);
            DataTableObject dataTableObject = new DataTableObject();
            List<Object> objectTbTransaction = new ArrayList();
            
            
            objectTbTransaction=transactionVas.getTransactionVasWithWebservice(tbTransactionVasList, transactionVasWsResp);
            
            dataTableObject.setAaData(objectTbTransaction);     

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            doGet(request, response);
    }

}
