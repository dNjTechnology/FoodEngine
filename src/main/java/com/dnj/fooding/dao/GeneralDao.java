/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.App;
import com.dnj.fooding.exeptions.AuthenticationException;
import com.dnj.fooding.support.AskPassword;
import com.dnj.fooding.support.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        
        Connection connection=DataBaseConnection.CONNECT();
        
        PreparedStatement stmt=connection.prepareStatement("UPDATE user SET name=? WHERE userid=?");
        stmt.setString(1,currentName);
        stmt.setString(2,App.currentUser.getUserid());
        stmt.executeUpdate();
        App.currentUser.setName(currentName);
        }
        else{
            throw new Exception("UPTO_DATE_DATA");
        }
        }
        
       
    
    
}
