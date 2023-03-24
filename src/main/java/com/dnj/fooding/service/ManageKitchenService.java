/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;

import com.dnj.fooding.dao.ManageKitchenDao;
import com.dnj.fooding.model.Order;
import java.util.List;

/**
 *
 * @author Animesh Samanta
 */
public class ManageKitchenService {
    private static ManageKitchenService instance;
     private void ManageKitchenService(){
        
    }
    public static ManageKitchenService getInstance(){
        if(instance==null){
            instance=new ManageKitchenService();
            return instance;
        }
        return instance;
    }
    
    public List<Order> getOrdersKitchen(){
       return ManageKitchenDao.getInstance().getAllOrdersInKitche();
    }
    public void setOrderStatus(Order order){
        ManageKitchenDao.getInstance().setOrderStatus(order);
    }
}
