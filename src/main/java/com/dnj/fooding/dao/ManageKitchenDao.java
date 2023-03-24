/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.model.Order;
import com.dnj.fooding.support.HibernateUtil;
import java.util.Arrays;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Animesh Samanta
 */
public class ManageKitchenDao {
    private static ManageKitchenDao instance;
     private void ManageKitchenDao(){
        
    }
    public static ManageKitchenDao getInstance(){
        if(instance==null){
            instance=new ManageKitchenDao();
            return instance;
        }
        return instance;
    }
    
    
    public List<Order> getAllOrdersInKitche(){
        Session session = HibernateUtil.getSessionFactory().openSession();
CriteriaBuilder builder = session.getCriteriaBuilder();

// create a CriteriaQuery for the Customer class
CriteriaQuery<Order> query = builder.createQuery(Order.class);

// create a Root object to specify the entity class and alias
Root<Order> root = query.from(Order.class);

// add a Predicate to the CriteriaQuery to filter by phone number
List<String> li=Arrays.asList("In Kitchen","Preparing","Food Ready");
Predicate statusPredicate = root.get("status").in(li);
query.where(statusPredicate);

// execute the CriteriaQuery to get a list of matching customers
List<Order> order = session.createQuery(query).getResultList();
return order;
    }
     public void setOrderStatus(Order order){
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
         try{
             session.update(order);
             tx.commit();
         }catch(Exception e){
             tx.rollback();
         }
         finally{
           session.close();
         }
    }
}
