
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
import pojos.TransactionVasResult;



public class TransactionVas extends ManagedSessionUnitTest{



  public TransactionVas() {
   
  }
  
  //return TransactionVassAll
  public List<TbTransactionVas> getTransactionVasAll(){
  
     Session session = this.createNewSessionAndTransaction();
      
      List<TbTransactionVas> tbTransactionVas = null;
       
       try{
           
           tbTransactionVas= session.createQuery("from TbTransactionVas").list();   
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbTransactionVas;
  
  }  
  
  
  public List<TbTransactionVas> getTransactionVasDate(String initDate,String endDate){
      Session session = this.createNewSessionAndTransaction();
      
      List<TbTransactionVas> tbTransactionVas = null;
       
       try{
           
           tbTransactionVas= session.createQuery("from TbTransactionVas transaction where transaction.dateRegistered between to_date('"+initDate+"','DD-MM-YYYY HH:MI:SS PM') and to_date('"+endDate+"','DD-MM-YYYY HH:MI:SS PM')").list();
        
       
       } finally
       {
           
           this.commitTransaction(session);
        
       }
      
       return tbTransactionVas;
  }
  
  
  public List<Object> getTransactionVasGroupByCountryError(){
  
     Session session = this.createNewSessionAndTransaction();
      
      List<Object> tbTransactionVas = null;
       
       try{
           
           tbTransactionVas= session.createQuery("select transaction.countryCode as pais,  transaction.wsResp as tipo, count(*) as errores from \n" +
            "TbTransactionVas transaction where transaction.wsResp='-101' or  transaction.wsResp='-102' or transaction.wsResp='-103' or transaction.wsResp='-104' or transaction.wsResp='-999'\n" +
            "group by transaction.countryCode, transaction.wsResp").list();   
       
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbTransactionVas;
  
  }
  
  
  public List<Object> getTransactionVasGroupByCountrySuccess(){
  
     Session session = this.createNewSessionAndTransaction();
      
      List<Object> tbTransactionVas = null;
       
       try{
           
           tbTransactionVas= 
           session.createQuery("select transaction.countryCode, count(*) as exitosos from TbTransactionVas transaction where (to_number(transaction.wsResp))>0 group by transaction.countryCode").list();   
       
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbTransactionVas;
  
  }
  
  
  public List<Object> getTransactionVasGroupByCountryUnknown(){
  
     Session session = this.createNewSessionAndTransaction();
      
      List<Object> tbTransactionVas = null;
       
       try{
           
           tbTransactionVas= 
           session.createQuery("select transaction.countryCode as pais, count(*) as desconocidos from TbTransactionVas transaction where  transaction.wsResp!='-101'  and transaction.wsResp!='-102'  and\n" +
           "transaction.wsResp!='-103' and transaction.wsResp!='-104' and  transaction.wsResp!='-999' and  (TO_NUMBER(transaction.wsResp))<0        \n" +
           "group by transaction.countryCode").list();   
       
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbTransactionVas;
  
  }
  
  
  //group by type error
  public List<Object> getTransactionVasGroupByError(String error){
  
     Session session = this.createNewSessionAndTransaction();
      
      List<Object> tbTransactionVas = null;
       
       try{
           
           tbTransactionVas= session.createQuery("select transaction.countryCode as pais, count(*) as errores from \n" +
            "TbTransactionVas transaction where transaction.wsResp='"+error.trim()+"'\n" +
            "group by transaction.countryCode order by transaction.countryCode asc").list();   
       
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbTransactionVas;
  
  }
  
  
  //group by country
  public List<Object> getTransactionVasGroupByCountry(){
  
     Session session = this.createNewSessionAndTransaction();
      
      List<Object> tbTransactionVas = null;
       
       try{
           
           tbTransactionVas= 
           session.createQuery("select transaction.countryCode from TbTransactionVas transaction  group by transaction.countryCode order by transaction.countryCode asc").list();   
       
       } finally
       {
           
           this.commitTransaction(session);
        
       }
       
       return tbTransactionVas;
  
  }
  
  
  //return TransactionVasAll+description WebService
  public List<Object> getTransactionVasWithWebservice
  (List<TbTransactionVas> tbTransactionVasList, TransactionVasWsResp transactionVasWsResp){
      
    List<Object> objectTbTransaction = new ArrayList();
      for (Iterator tbTransactionIterator = tbTransactionVasList.iterator(); 
                 tbTransactionIterator.hasNext();
                )
            {
            TbTransactionVas tbTransactionVas = (TbTransactionVas) tbTransactionIterator.next(); 
            List<Object> object = new ArrayList();    
            
            object.add(tbTransactionVas.getId());
            object.add(tbTransactionVas.getCountryCode());
            object.add(tbTransactionVas.getMsisdn());
            object.add(tbTransactionVas.getMsisdn());
            object.add(tbTransactionVas.getCustomerCode());
            object.add(tbTransactionVas.getName());
            object.add(tbTransactionVas.getName2());
            object.add(tbTransactionVas.getMail());
            object.add(tbTransactionVas.getTbTransactionVasState().getDescription());
            object.add(tbTransactionVas.getDateRegistered());
            object.add(tbTransactionVas.getWsResp());
            object.add(transactionVasWsResp.validTransactionResponseWs(transactionVasWsResp.getTransactionVasWsRespAll(),tbTransactionVas.getWsResp()));
           
            objectTbTransaction.add(object);
            }
     
            

    return objectTbTransaction;
  }
  
    
}
