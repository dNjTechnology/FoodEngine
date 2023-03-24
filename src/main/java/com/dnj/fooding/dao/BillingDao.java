/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.App;
import com.dnj.fooding.model.Billing;
import com.dnj.fooding.model.CurrentTableBooking;
import com.dnj.fooding.support.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Animesh Samanta
 */
public class BillingDao {
    private static BillingDao instance;
     private void BillingDao(){
        
    }
    public static BillingDao getInstance(){
        if(instance==null){
            instance=new BillingDao();
            return instance;
        }
        return instance;
    }
    public static Billing initiateBilling(Billing billing){
       billing.setBilledby(App.currentUser);
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx =null; 
       try{
            tx=session.beginTransaction();
            session.save(billing);
            tx.commit();
            return billing;
       }
       catch(Exception e){
           tx.rollback();
           e.printStackTrace();
           
       }
       finally{
           session.close();
       }
       return null;
    }
}
