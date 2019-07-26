
package dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import pojos.TbTransactionVas;
import pojos.TbTransactionVasWsResp;


public class TransactionVasWsResp extends ManagedSessionUnitTest {



  public TransactionVasWsResp() {

  }
  
  public List<TbTransactionVasWsResp> getTransactionVasWsRespAll() {
  
      
      List<TbTransactionVasWsResp> tbTransactionVasWsResp = null;
      Session session = this.createNewSessionAndTransaction();
       
       try{
           
           tbTransactionVasWsResp= session.createQuery("from TbTransactionVasWsResp").list();           
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbTransactionVasWsResp;
  
  }
  
  public String validTransactionResponseWs(List<TbTransactionVasWsResp> ListWsResp , String resp ){
      
       List<TbTransactionVasWsResp> tbTransactionVasWsRespList = null;

       String wsResp="";
       int compareResp=-1;
       
     if(resp!=null){
         int valResp = Integer.valueOf(resp);
         
       try{
   
           tbTransactionVasWsRespList= ListWsResp;
           
           for (Iterator tbTransactionVasWsRespIterator = tbTransactionVasWsRespList.iterator(); 
                 tbTransactionVasWsRespIterator.hasNext();
                )
            {
                TbTransactionVasWsResp tbTransactionVasWsResp = (TbTransactionVasWsResp) tbTransactionVasWsRespIterator.next(); 
                 
                if(valResp>=0){
                    wsResp = "Exitoso";
                }else{
                    compareResp=tbTransactionVasWsResp.getState().intValueExact();
                    
                    if(tbTransactionVasWsResp.getState().intValueExact()==valResp){
                        wsResp=tbTransactionVasWsResp.getDescription();
                        return wsResp;
                        
                    }else{
                        wsResp="Desconocido";
                    }
                }
            }
           
       } finally
       {
           
           

        
       }
     
     
     }
       
       return wsResp;
  
  }
  


  
    
}
