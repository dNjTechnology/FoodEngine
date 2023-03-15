/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.model.Customer;
import com.dnj.fooding.support.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Animesh Samanta
 */
public class CustomerDao {
     private static CustomerDao customerDaoInstance=null;

    
    private CustomerDao(){
        
    }
    public static CustomerDao getInstance(){
        if(customerDaoInstance==null){
            customerDaoInstance=new CustomerDao();
            return customerDaoInstance;
        }
        return customerDaoInstance;
    }
    public List<Customer> getCustomerByPhone(String phoneNumber){
        List<Customer> customerList=null;
 Session session = HibernateUtil.getSessionFactory().openSession();
CriteriaBuilder builder = session.getCriteriaBuilder();

// create a CriteriaQuery for the Customer class
CriteriaQuery<Customer> query = builder.createQuery(Customer.class);

// create a Root object to specify the entity class and alias
Root<Customer> root = query.from(Customer.class);

// add a Predicate to the CriteriaQuery to filter by phone number

Predicate phonePredicate = builder.equal(root.get("phone"), phoneNumber);
query.where(phonePredicate);

// execute the CriteriaQuery to get a list of matching customers
List<Customer> customers = session.createQuery(query).getResultList();
if(customers!=null&&customers.size()>0){
    customerList=new ArrayList<>();
    for(Customer cust:customers){
        customerList.add(cust);
    }
}

return customerList;
    }
     public Customer doesCustomerExists(Customer customer){
           Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("FROM Customer WHERE name = :name AND phone=:phone AND email=:email");
    query.setParameter("name", customer.getName());
    query.setParameter("email", customer.getEmail());
    query.setParameter("phone", customer.getPhone());
    List<Customer> customers = query.list();
    session.close();
    if(!customers.isEmpty()){
        return customers.get(0);
    }
    return null;
            
        }
     public Customer addCustomer(Customer customer){
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction trans=null;
         try{
             trans=session.beginTransaction();
             session.save(customer);
            // Customer savedCustomer=(Customer)session.get(Customer.class, customer.getId());
             trans.commit();
             return customer;
         }
         catch(Exception e){
             e.printStackTrace();
             trans.rollback();
             
         }
         finally{
             session.close();
         }
         return null;
     }
}
