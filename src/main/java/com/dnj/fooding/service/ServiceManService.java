/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;

import com.dnj.fooding.App;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Animesh Samanta
 */
public class ServiceManService {
    private static ServiceManService instance;
    private ServiceManService(){
        
    }
    public static ServiceManService getInstance(){
        if(instance==null){
            instance=new ServiceManService();
        }
        return instance;
    }
    @SuppressWarnings("ConvertToStringSwitch")
    public Map<String,String> getAccessButtons(){
        Set<String> buttons=new HashSet<>();
        Map<String,String> accessToXMLMAP=new HashMap<>();
        for(String access:App.currentUser.getAccess()){
             if(access.equals("MANAGE_FOOD")||access.equals("MANAGE_FOOD_RATE")){
    
    buttons.add("Manage Menu");
    accessToXMLMAP.put("Manage Menu","manageMenuCard");
       }
       else if(access.equals("MANAGE_ORDER")){
           
           buttons.add("Manage Order");
           accessToXMLMAP.put("Manage Order", "");
       }
       else if(access.equals("MANAGE_PAYMENTS")){
           
           buttons.add("Manage Payements");
           accessToXMLMAP.put("Manage Payements", "");
       }
       else if(access.equals("MANAGE_SALES")){
           
           buttons.add("Manage Sales");
           accessToXMLMAP.put("Manage Sales", "");
       }
       else if(access.equals("MANAGE_ATTENDANCE")){
           buttons.add("M.Staff");
           accessToXMLMAP.put("M.Staff", "staffManagement");
       }
       else if(access.equals("MANAGE_ROLE")){
           buttons.add("Manage Role");
           accessToXMLMAP.put("Manage Role", "");
       }
             else if(access.equals("MANAGE_TABLES")){
           buttons.add("Manage Table");
           accessToXMLMAP.put("Manage Table", "");
       }
              else if(access.equals("POS")){
           buttons.add("Point of Sale(POS)");
           accessToXMLMAP.put("Point of Sale(POS)", "");
       }
              else if(access.equals("MANAGE_KITCHEN")){
                 buttons.add("Manage Kitchen");
           accessToXMLMAP.put("Manage Kitchen", "mangeKitchen"); 
             }
             else if(access.equals("MANAGE_TABLE")){
                 buttons.add("Manage Table");
           accessToXMLMAP.put("View Dine In", "tableDineIn"); 
             }
            
             
        }
        return accessToXMLMAP;
    }
}
