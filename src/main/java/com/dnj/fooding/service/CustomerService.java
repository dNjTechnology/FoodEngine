/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;

import com.dnj.fooding.dao.CustomerDao;
import com.dnj.fooding.model.Customer;
import java.util.List;

/**
 *
 * @author Animesh Samanta
 */
public class CustomerService {
     private static CustomerService customerServiceInstance=null;
     
    private void CustomerService(){
        
    }
    public static CustomerService getInstance(){
        if(customerServiceInstance==null){
            customerServiceInstance=new CustomerService();
            return new CustomerService();
        }
        return customerServiceInstance;
    }
    public List<Customer> searchForCustomer(String phone){
        List<Customer> customers=null;
        customers=CustomerDao.getInstance().getCustomerByPhone(phone);
        return customers;
    }
        public Customer addCustomer(Customer customer){
            Customer c=doesCustomerExists(customer);
            if(c==null){
                return CustomerDao.getInstance().addCustomer(customer);
            }
            return c;
        
    }
        public Customer doesCustomerExists(Customer customer){
            Customer c=CustomerDao.getInstance().doesCustomerExists(customer);
            if(c!=null){
                return c;
            }
           return null;
            
        }
}
