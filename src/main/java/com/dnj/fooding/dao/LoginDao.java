/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.exeptions.UserNotFoundException;
import com.dnj.fooding.model.User;
import com.dnj.fooding.support.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public User getUserById(String userid) throws SQLException, UserNotFoundException{
        User currentUser=null;
        Connection connection=DataBaseConnection.CONNECT();
        PreparedStatement stmt=connection.prepareStatement("select * from user where userid=?");
                stmt.setString(1,userid);  
                ResultSet result = stmt.executeQuery();
                 if (!result.next()) {
                     throw new UserNotFoundException();
                 }
                 else{
                     currentUser=new User();
        currentUser.setAccess(result.getString("Network"));
        currentUser.setDesignation(result.getString("designation"));
        currentUser.setName(result.getString("name"));
        currentUser.setUserid(result.getString("userid"));
        currentUser.setPassword(result.getString("password"));
        
                 }
                return currentUser;
    }
    
}
