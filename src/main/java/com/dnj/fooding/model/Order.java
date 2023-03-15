/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Animesh Samanta
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private CurrentTableBooking currentTableBooking;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CurrentTableBooking getCurrentTableBooking() {
        return currentTableBooking;
    }

    public void setCurrentTableBooking(CurrentTableBooking currentTableBooking) {
        this.currentTableBooking = currentTableBooking;
    }
    @OneToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private Menu menu;
    
    @ManyToOne
    @JoinColumn(name = "table_number", referencedColumnName = "table_number")
    private TablesDineIn tablesDineIn;

    public TablesDineIn getTablesDineIn() {
        return tablesDineIn;
    }

    public void setTablesDineIn(TablesDineIn tablesDineIn) {
        this.tablesDineIn = tablesDineIn;
    }
    
    @Column(name = "order_quantity")
    private int quantity;
    
    @Column(name = "order_date")
    private LocalDateTime date;
    
    // constructors, getters, and setters
    
    
    
    // getters and setters
    
    public int getId() {
        return orderId;
    }
    
    public void setId(int orderId) {
        this.orderId = orderId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
   
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
     public Order() {
        // default constructor
    }
}

