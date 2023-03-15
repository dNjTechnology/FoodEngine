/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;

import com.dnj.fooding.dao.TablesDineInDao;
import com.dnj.fooding.model.CurrentTableBooking;
import com.dnj.fooding.model.TablesDineIn;
import java.util.List;

/**
 *
 * @author Animesh Samanta
 */
public class TableDineInService {
    private static TableDineInService instance;
    private TableDineInService(){
        
    }
    public static TableDineInService getInstance(){
        if(instance==null){
            instance=new TableDineInService();
        }
        return instance;
    }
    public List<TablesDineIn> getAllTables(){
        List<TablesDineIn> tables=TablesDineInDao.getInstance().getAllTablesDao();
        return tables;
    }
    public CurrentTableBooking getTableWorkFlow(TablesDineIn table){
        CurrentTableBooking booking=TablesDineInDao.getInstance().getCurrentTableWorkFlow(table.getTableNumber());
        return booking;
    }
}
