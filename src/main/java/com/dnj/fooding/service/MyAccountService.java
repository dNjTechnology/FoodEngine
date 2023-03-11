/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;

import com.dnj.fooding.App;
import com.dnj.fooding.dao.GeneralDao;
import java.sql.SQLException;

/**
 *
 * @author Animesh Samanta
 */
public class MyAccountService {
    private static MyAccountService instance=null;
    private MyAccountService(){
        
    }
    public static MyAccountService getInstance(){
        if(instance==null){
            instance=new MyAccountService();
        }
        return instance;
    }
    public static void changeName(String name) throws SQLException, Exception{
        GeneralDao.getInstance().updateName(name);
        App.currentUser.setName(App.currentUser.getName());
    }
}
