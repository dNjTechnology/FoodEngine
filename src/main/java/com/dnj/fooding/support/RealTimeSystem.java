/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

import com.dnj.fooding.controller.TableDineInController;
import com.dnj.fooding.model.TablesDineIn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import com.dnj.fooding.App;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RealTimeSystem implements Runnable {
    private static void waitForNextDataLoad(){
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(RealTimeSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void inspectTable(){
        while (App.running) {
            waitForNextDataLoad();
            
           Session session = HibernateUtil.getSessionFactory().openSession();
          CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        List<TablesDineIn> tables=null;
        CriteriaQuery<TablesDineIn> tableQuery = criteriaBuilder.createQuery(TablesDineIn.class);
        tableQuery.from(TablesDineIn.class);
        tables = session.createQuery(tableQuery).list();
         Map<Integer,Boolean> map=detectDifference(tables);
        if(map.size()!=0){
            System.out.println("Change Detected");
            TableDineInController.updateView(map,tables);
        }
        //TableDineInController.tables=tables;
        }  
        }
      private static Map<Integer, Boolean> detectDifference(List<TablesDineIn> tables){
          Map<Integer,Boolean> map=new HashMap<>();
          List<TablesDineIn> oldTable=TableDineInController.tables;
          for(int i=0;i<oldTable.size();i++){
              if(tables.get(i).getTableNumber()==oldTable.get(i).getTableNumber()){
                  if(tables.get(i).isAvailable()!=oldTable.get(i).isAvailable()){
                      map.put(tables.get(i).getTableNumber(),tables.get(i).isAvailable());
                  }
              }
          }
          return map;
      }  
    

    @Override
    public void run() {
                    
        RealTimeSystem.inspectTable();
    }
}
