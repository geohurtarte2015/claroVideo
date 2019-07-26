
package controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.ClaroUser;
import estructura.DataTableObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.TbUserClaroVideo;


public class ServletListUsers extends HttpServlet {

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)            
            throws ServletException, IOException {
            processRequest(request, response);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();           
            ClaroUser claroUser = new ClaroUser(); 
            List<TbUserClaroVideo> listUser = claroUser.getListUser();
                    
            DataTableObject dataTableObject = new DataTableObject();
            List<Object> objectUsuarios= new ArrayList();
           
            
            for (Iterator analistaIterator = listUser.iterator(); 
                 analistaIterator.hasNext();
                )
            {
            TbUserClaroVideo usuario = (TbUserClaroVideo) analistaIterator.next(); 
            List<Object> object = new ArrayList();  
            object.add(usuario.getIdUser());
            object.add(usuario.getNombre());
            object.add(usuario.getApellido());
            object.add(usuario.getDateCreate());
            object.add(usuario.getUsername());
            object.add(usuario.getRolClaroVideo().getDescripcion());
            objectUsuarios.add(object);
            }
            
            
            dataTableObject.setAaData(objectUsuarios);     

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        
       
        
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
