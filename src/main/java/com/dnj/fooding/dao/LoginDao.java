/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.App;
import com.dnj.fooding.exeptions.AuthenticationException;
import com.dnj.fooding.exeptions.UserNotFoundException;
import com.dnj.fooding.model.User;
import com.dnj.fooding.support.AskPassword;
import com.dnj.fooding.support.DataBaseConnection;
import com.dnj.fooding.support.HibernateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Animesh Samanta
 */
public class LoginDao {
     private static LoginDao loginDaoInstance=null;

    
    private LoginDao(){
        
    }
    public static LoginDao getInstance(){
        if(loginDaoInstance==null){
            loginDaoInstance=new LoginDao();
            return loginDaoInstance;
        }
        return loginDaoInstance;
    }
     @SuppressWarnings("static-access")
    public User getUserById(String userid) throws SQLException, UserNotFoundException{
        User currentUser=null;
        Session session = HibernateUtil.getSessionFactory().openSession();
                 Transaction transaction = session.beginTransaction();
                String hql = "FROM User WHERE userid = :userid";
                Query query = session.createQuery(hql);
query.setParameter("userid", userid);
                currentUser=(User)query.uniqueResult();
                //Testing.get();
                return currentUser;
    }
    public void resetPassword(String password) throws AuthenticationException{
   AskPassword.askPassword();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user=App.currentUser;
        user.setPassword(password);
        session.saveOrUpdate(user);
        transaction.commit();
       
        App.currentUser.setPassword(password);
    }
    
}
