/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Animesh Samanta
 */
@Entity
@Table(name = "currenttableorder")
public class CurrentTableBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @OneToOne
    @JoinColumn(name = "table_number")
    public TablesDineIn table;
    @OneToMany(mappedBy = "currentTableOrder", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;

    public TablesDineIn getTable() {
        return table;
    }

    public void setTable(TablesDineIn table) {
        this.table = table;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
     public CurrentTableBooking() {
        // default constructor
    }
    
}
