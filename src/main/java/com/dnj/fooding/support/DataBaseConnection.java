/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;
//import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Animesh Samanta
 */
public class DataBaseConnection {
    private static Connection connection=null;
    private DataBaseConnection(){
        try {
            System.out.println("Connecting...");
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/restro365","root","");
        System.out.println("Connection Completed");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Connection failed");
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            System.out.println("Dailing failed");
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection CONNECT(){
        if(connection==null){
            new DataBaseConnection();
            return connection;
        }
        return connection;
    }
     
    }


