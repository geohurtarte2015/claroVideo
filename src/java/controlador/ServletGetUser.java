
package controlador;

import dao.ClaroUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.TbUserClaroVideo;


public class ServletGetUser extends HttpServlet {


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
        try {
            processRequest(request, response);
            PrintWriter out = response.getWriter();
            
            String text = "Update successfull"; //message you will recieve
            String name = request.getParameter("id");
            
            ClaroUser user = new ClaroUser();
            
            List<TbUserClaroVideo> tbClaroUser =  user.getUser(name);
            
            JSONObject json = new JSONObject();
            json.put("nombre", tbClaroUser.get(0).getNombre());
            json.put("apellido", tbClaroUser.get(0).getApellido());
            json.put("email", tbClaroUser.get(0).getEmail());
            json.put("password", tbClaroUser.get(0).getPassword());
            json.put("usuario", tbClaroUser.get(0).getUsername());
            json.put("rol", tbClaroUser.get(0).getRolClaroVideo().getIdRol());
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
            
            //out.println(name + " " + text);
        } catch (JSONException ex) {
            Logger.getLogger(ServletGetUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
  

}
