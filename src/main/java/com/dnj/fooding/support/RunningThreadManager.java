/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

import com.dnj.fooding.App;
import com.dnj.fooding.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Animesh Samanta
 */
public class RunningThreadManager {
    public static List<Thread> threads=new ArrayList<>();
    
    
    public static void closeAllThreads(){
        for(Thread thread:threads){
            thread.stop();
        }
    }
    public static void runRequiredThreadForUser(){
        try{
            
        List<String> network=App.currentUser.getAccess();
        if(network!=null){
            for(String access:network){
             if(access.equals("MANAGE_FOOD")||access.equals("MANAGE_FOOD_RATE")){
    
    //no process
       }
       else if(access.equals("MANAGE_ORDER")){
           
         
       }
       else if(access.equals("MANAGE_PAYMENTS")){
           
         
       }
       else if(access.equals("MANAGE_SALES")){
           
          
       }
       else if(access.equals("MANAGE_ATTENDANCE")){
          
       }
       else if(access.equals("MANAGE_ROLE")){
         
       }
       else if(access.equals("MANAGE_TABLES")){
          
       }
       else if(access.equals("POS")){
          
       }
       else if(access.equals("MANAGE_KITCHEN")){
                Thread t2 = new Thread(new RealTimeSystemForKitchen());
                threads.add(t2);
                t2.start();
        }
        else if(access.equals("MANAGE_TABLE")){   
        App.isTableInspectOn=true;
        Thread t2 = new Thread(new RealTimeSystem());
        threads.add(t2);
        t2.start();
        
        }
        
        }
        }
        }catch(Exception e){
           
        }
    }
    public static void runApplicationThreads(){
          Thread t = new Thread(new UsbDetection());
          threads.add(t);
        t.start();
        
    }
}
