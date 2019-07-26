package dao;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import pojos.TbRolClaroVideo;
import pojos.TbTransactionVas;
import pojos.TbUserClaroVideo;



public class ClaroUser extends ManagedSessionUnitTest{
    
    
    public ClaroUser(){
    
    }
    
     public List<Object> getValidateUser(String password, String user){
  
     Session session = this.createNewSessionAndTransaction();
      
      List<Object> tbClaroUser = null;
       
       try{
           
           tbClaroUser= session.createQuery("select claroUser.nombre,claroUser.apellido "
           + "from TbUserClaroVideo claroUser where "
           + "claroUser.password='"+password+"' and claroUser.username='"+user+"'").list();   
           
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbClaroUser;
  
  }
     
     public List<TbUserClaroVideo> getUser(String id){
  
     long val=0;
     val=Integer.parseInt(id);
     
     Session session = this.createNewSessionAndTransaction();
      
      List<TbUserClaroVideo> tbClaroUser = null;
       
       try{
           
           tbClaroUser= session.createQuery("from TbUserClaroVideo claroUser where claroUser.idUser="+val+"").list();   
           
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbClaroUser;
  
  }
     
     public void setUser(String password, String username , String nombre, String apellido, long idRol){
     TbRolClaroVideo rolClaroVideo = null;
     
         
     Session session = this.createNewSessionAndTransaction();
     
     Date dateCreate = new Date();
     rolClaroVideo = (TbRolClaroVideo) session.get(TbRolClaroVideo.class, idRol);
     TbUserClaroVideo user = new TbUserClaroVideo(apellido, nombre,password,dateCreate,username);
     user.setRolClaroVideo(rolClaroVideo);
     
     try{     
    
        session.persist(user);  
    
        } finally
        {
                
            this.commitTransaction(session);
    
        }
  
  }  
     
      //return TransactionVassAll
     public List<TbUserClaroVideo> getListUser(){

           Session session = this.createNewSessionAndTransaction();

            List<TbUserClaroVideo> listUser = null;

             try{

                 listUser= session.createQuery("from TbUserClaroVideo").list();   
             } finally
             {

                 this.commitTransaction(session);

             }

             return listUser;

        }
      
     public TbUserClaroVideo update(long idUser, String nombre, String apellido, String usuario, String password, long idRol){
        TbUserClaroVideo user = null;
        TbRolClaroVideo rolClaroVideo = null;
        Session session = this.createNewSessionAndTransaction();
     
       try{           
           
           user = (TbUserClaroVideo) session.get(TbUserClaroVideo.class, idUser);  
           rolClaroVideo = (TbRolClaroVideo) session.get(TbRolClaroVideo.class, idRol);  
           
           user.setNombre(nombre);
           user.setApellido(apellido);
           user.setUsername(usuario);
           user.setPassword(password);
           user.setRolClaroVideo(rolClaroVideo);
         
           session.update(user);
        
       }catch(HibernateException he){
        throw he; 
       
       } finally {
           
          this.commitTransaction(session);
       }
       
       
       return user;
   
   
   }
     
     public TbUserClaroVideo delete(long idUser){
        TbUserClaroVideo user = null;
        TbRolClaroVideo rolClaroVideo = null;
        Session session = this.createNewSessionAndTransaction();
     
       try{
           user = (TbUserClaroVideo) session.get(TbUserClaroVideo.class, idUser);  
           session.delete(user);
        
       }catch(HibernateException he){
        throw he; 
       
       } finally {
           
          this.commitTransaction(session);
       }
       
       
       return user;
   
   
   }
     


    
}