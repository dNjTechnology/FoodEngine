/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

/**
 *
 * @author Animesh Samanta
 */
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "visiting_history")
public class VisitingHistory {
    
    @Id
    @Column(name = "id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    
    @Column(name = "table_number")
    private int tableNumber;
    
    @Column(name = "visit_date")
    private Date visitDate;

    public VisitingHistory() {
    }

    public VisitingHistory(int id, Customer customer, int tableNumber, Date visitDate) {
        this.id = id;
        this.customer = customer;
        this.tableNumber = tableNumber;
        this.visitDate = visitDate;
    }

    // Getter and Setter methods for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    // toString method for debugging purposes

    @Override
    public String toString() {
        return "VisitingHistory [id=" + id + ", customer=" + customer.toString() + ", tableNumber=" + tableNumber + ", visitDate=" + visitDate + "]";
    }
}

