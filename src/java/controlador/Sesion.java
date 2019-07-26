
package controlador;

import dao.ClaroUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojos.TbUserClaroVideo;



public class Sesion extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
           
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
         PrintWriter out = response.getWriter();
         try  {
             
            boolean valUsuario = false;
           
            
            String usuario = request.getParameter("name");
            String password = request.getParameter("pass");    
            
            
            ClaroUser claroUser = new ClaroUser();
                List<Object> daoClaroUser = claroUser.getValidateUser(password,usuario);
               
                
            valUsuario = (daoClaroUser.size()!=0);
            if (valUsuario){
                Object[] arrayPaisError = (Object[]) daoClaroUser.get(0);
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("nombre", arrayPaisError[0]);
                httpSession.setAttribute("apellido", arrayPaisError[1]);
            }else{
                String error="errorusuario";
                out.println(error.trim());
            }
            
        } finally{        
                
            out.close();
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
