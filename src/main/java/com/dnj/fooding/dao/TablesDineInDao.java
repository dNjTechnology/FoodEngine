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
import javafx.scene.control.Alert;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Animesh Samanta
 */
public class TablesDineInDao {
     private static TablesDineInDao instance;
    private TablesDineInDao(){
        
    }
    public static TablesDineInDao getInstance(){
        if(instance==null){
            instance=new TablesDineInDao();
        }
        return instance;
    }
    public List<TablesDineIn> getAllTablesDao(){
        Session session = HibernateUtil.getSessionFactory().openSession();
          CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        List<TablesDineIn> tables=null;
        CriteriaQuery<TablesDineIn> tableQuery = criteriaBuilder.createQuery(TablesDineIn.class);
        tableQuery.from(TablesDineIn.class);
        tables = session.createQuery(tableQuery).list();
        return tables;
    }
    public void reserveTable(TablesDineIn table,Customer customer){
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx =null; 
         try{
        tx=session.beginTransaction();
        List<Order> orders=new ArrayList<>();
        Menu menu=getComplimentary();
        Order order=new Order();
        order.setCustomer(customer);
        order.setTablesDineIn(table);
        order.setMenu(menu);
        order.setQuantity(1);
        LocalDateTime rightNow = LocalDateTime.now();
        order.setDate(rightNow);
        orders.add(order);
        
        //session.save(orders);
        CurrentTableBooking booking=new CurrentTableBooking();
        booking.setCustomer(customer);
        booking.setOrders(orders);
        booking.setTable(table);
        session.save(booking);
        order.setCurrentTableBooking(booking);
        TablesDineIn tempTable=new TablesDineIn();
        session.update(order);
        tempTable.setTableNumber(table.getTableNumber());
        tempTable.setAvailable(false);
        tempTable.setSeatingCapacity(table.getSeatingCapacity());
        session.update(tempTable);
        tx.commit();

Alert a=new Alert(Alert.AlertType.INFORMATION);
a.setContentText("Table is reservation will be updated in few seconds");
a.show();
         }catch(Exception e){
             tx.rollback();
             e.printStackTrace();
         }
         finally{
           session.close();  
         }
        
    }
    public Menu getComplimentary(){
        Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("FROM Menu WHERE itemName = :itemName");
    query.setParameter("itemName","COMPLIMENTARY");
    List<Menu> menuList = query.list();
    session.close();
    return menuList.get(0);
    }
    public CurrentTableBooking getCurrentTableWorkFlow(Integer tableNumber){
      Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("FROM CurrentTableBooking WHERE table_number = :table");
    query.setParameter("table",tableNumber);
    List<CurrentTableBooking> list = query.list();
    return list.get(0);
    }
}
