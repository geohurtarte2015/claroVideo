
package controlador;

import dao.ClaroUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletDeleteUser extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             PrintWriter out = response.getWriter();

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
            String idUser = request.getParameter("idUserHidden");
 
        
         try  {
            long id=  Integer.parseInt(idUser);
            ClaroUser claroUser = new ClaroUser();
            claroUser.delete(id);
            response.sendRedirect("user.jsp");
        
        } finally{        
                
            out.close();
        }
        
        
    }
        
        

}
