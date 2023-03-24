/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;

import com.dnj.fooding.dao.KitchenDao;
import com.dnj.fooding.model.CurrentTableBooking;
import com.dnj.fooding.model.Customer;
import com.dnj.fooding.model.Menu;
import com.dnj.fooding.model.Order;
import com.dnj.fooding.model.TablesDineIn;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

/**
 *
 * @author Animesh Samanta
 */
public class KitchenService {
    private static KitchenService instance;
     private void KitchenService(){
        
    }
    public static KitchenService getInstance(){
        if(instance==null){
            instance=new KitchenService();
            return instance;
        }
        return instance;
    }
    public void sendOrderInKitchen(Map<Menu,Integer> mapQuantity,Customer customer,TablesDineIn table){
        KitchenDao.getInstance().sendOrderInKitchen(mapQuantity, customer, table);
    }
}
