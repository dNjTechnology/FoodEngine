/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.App;
import com.dnj.fooding.exeptions.AuthenticationException;
import com.dnj.fooding.model.User;
import com.dnj.fooding.support.AskPassword;
import com.dnj.fooding.support.DataBaseConnection;
import com.dnj.fooding.support.HibernateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Animesh Samanta
 */
public class GeneralDao {
    private static GeneralDao dao=null;
    private GeneralDao(){
        
    }
    public static GeneralDao getInstance(){
        if(dao==null){
            dao=new GeneralDao();
        }
        return dao;
    }
     
       public void updateName(String currentName) throws AuthenticationException, SQLException, Exception{
        if(!(currentName.equals(App.currentUser.getName()))){
        AskPassword.askPassword();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user=App.currentUser;
        user.setName(currentName);
        session.saveOrUpdate(user);
        transaction.commit();
       
        App.currentUser.setName(currentName);
        }
        else{
            throw new Exception("UPTO_DATE_DATA");
        }
        }
        
       
    
    
}
