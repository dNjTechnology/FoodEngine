/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.model.Customer;
import com.dnj.fooding.model.VisitingHistory;
import com.dnj.fooding.support.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Animesh Samanta
 */
public class Testing {
    public static void get() {
         Session session = HibernateUtil.getSessionFactory().openSession();
          CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        
        // retrieve all customers
        CriteriaQuery<Customer> customerQuery = criteriaBuilder.createQuery(Customer.class);
        customerQuery.from(Customer.class);
        List<Customer> customers = session.createQuery(customerQuery).list();
        System.out.println(customers.toString());
        CriteriaQuery<VisitingHistory> visitingHistory = criteriaBuilder.createQuery(VisitingHistory.class);
        visitingHistory.from(VisitingHistory.class);
        List<VisitingHistory> visits = session.createQuery(visitingHistory).list();
        System.out.println(visits.toString());
       // Transaction transaction = session.beginTransaction();
    }
}
