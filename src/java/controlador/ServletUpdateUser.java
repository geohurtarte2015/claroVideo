
package controlador;

import dao.ClaroUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletUpdateUser extends HttpServlet {

    
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
        String nombre = request.getParameter("NombreUpdate");
        String apellido = request.getParameter("ApellidoUpdate");   
        String usuario = request.getParameter("UsuarioUpdate");
        String password = request.getParameter("PasswordUpdate");  
        String rol = request.getParameter("idRolHidden");  
        
         try  {
            long id=  Integer.parseInt(idUser);
            int idRol=  Integer.parseInt(rol);
            ClaroUser claroUser = new ClaroUser();
            claroUser.update(id, nombre, apellido, usuario, password, idRol);
            response.sendRedirect("user.jsp");
        
        } finally{        
                
            out.close();
        }
        
        
    }
        
        
        
    }

 


