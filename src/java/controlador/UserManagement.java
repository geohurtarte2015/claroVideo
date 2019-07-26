
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


public class UserManagement extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
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
  
            String nombre = request.getParameter("Nombre");
            String apellido = request.getParameter("Apellido");   
            String usuario = request.getParameter("Usuario");
            String password = request.getParameter("Password"); 
            String rol = request.getParameter("Rol");
            long idRol = Integer.parseInt(rol);
            
         try  {
             
            ClaroUser claroUser = new ClaroUser();
            claroUser.setUser(password, usuario, nombre, apellido,idRol);
            response.sendRedirect("user.jsp");
        
        } finally{        
                
            out.close();
        }
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
