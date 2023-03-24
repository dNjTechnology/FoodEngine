/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

import com.dnj.fooding.App;
import com.dnj.fooding.controller.MangeKitchenController;

import com.dnj.fooding.model.Order;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Animesh Samanta
 */
public class RealTimeSystemForKitchen implements Runnable{
    
     private static void waitForNextDataLoad(){
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(RealTimeSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void inspectTable(){
        while (App.running) {
            waitForNextDataLoad();
            
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
            
            MangeKitchenController.orders=order;
            MangeKitchenController.updateView();
//Map<Integer,String> map=detectDifference(order);
//if(map.size()>0){
//    
//    System.out.println("Change Detected on Kitchen");
//    MangeKitchenController.orders=order;
//    MangeKitchenController.updateView();
//}
        }  
        }
      
    

    @Override
    public void run() {
                    
        RealTimeSystemForKitchen.inspectTable();
    }
}
