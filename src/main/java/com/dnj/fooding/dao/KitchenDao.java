/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.model.CurrentTableBooking;
import com.dnj.fooding.model.Customer;
import com.dnj.fooding.model.Menu;
import com.dnj.fooding.model.Order;
import com.dnj.fooding.model.TablesDineIn;
import com.dnj.fooding.support.HibernateUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Animesh Samanta
 */
public class KitchenDao {
     private static KitchenDao instance;
     private void KitchenDao(){
        
    }
    public static KitchenDao getInstance(){
        if(instance==null){
            instance=new KitchenDao();
            return instance;
        }
        return instance;
    }
   public void sendOrderInKitchen(Map<Menu,Integer> mapQuantity,Customer customer,TablesDineIn table){
       Session session = HibernateUtil.getSessionFactory().openSession();
       CurrentTableBooking booking=TablesDineInDao.getInstance().getCurrentTableBookingForTable(table);
         Transaction tx =null; 
         try{
             tx=session.beginTransaction();
       List<Order> orders=new ArrayList<>();
        for(Map.Entry<Menu,Integer> entry:mapQuantity.entrySet()){
            
        Order order=new Order();
        order.setCustomer(customer);
        order.setMenu(entry.getKey());
        order.setQuantity(entry.getValue());
        order.setStatus("In Kitchen");
        order.setDate(LocalDateTime.now());
        order.setTablesDineIn(table);
        order.setCurrentTableOrder(booking);
        orders.add(order);
         session.persist(order);
        
        
        }
        
        
       
        tx.commit();
        //String hql = "update Order set currentTableBooking=:currentTableBooking where orderId IN(:orderId)";
        //
        //Query query=session.createQuery(hql);
        //query.setParameter("currentTableBooking",booking);
        //query.setParameterList("orderId",orders.stream().map(Order::getOrderId).collect(Collectors.toList()));
        //query.executeUpdate();
        //tx.commit();
         }catch(Exception e){
             e.printStackTrace();
             tx.rollback();
         }finally{
             session.close();
         }
    } 
}
