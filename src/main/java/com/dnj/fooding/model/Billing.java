/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

/**
 *
 * @author Animesh Samanta
 */
import com.dnj.fooding.model.support.Status;
import com.dnj.fooding.model.support.TransactionType;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="billing_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    

    
    @Column(name = "invoice_id",nullable = true)
    private String invoice;

   
    @Column(name = "sgst",nullable = true)
    private String sgst;

    
    @Column(name = "cgst",nullable = true)
    private String cgst;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", length = 50,nullable = true)
    private TransactionType transactionType;

    @Column(name = "transaction_date",nullable = true)
    private Date transactionDate;
    
    @Column(name = "selling",nullable = true)
    private Double sellingPrice;
    
    @Column(name = "amount",nullable = true)
    private Double totalAmount;

    @Column(name = "payment_method", length = 50,nullable = true)
    private String paymentMethod;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private Status status;

    @Column(name = "notes", length = 65535,nullable = true)
    private String notes;
    
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id",nullable = true)
    private User billedby;

    public User getBilledby() {
        return billedby;
    }

    public void setBilledby(User billedby) {
        this.billedby = billedby;
    }
    
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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Billing{" + "id=" + id + ", customer=" + customer + ", invoice=" + invoice + ", sgst=" + sgst + ", cgst=" + cgst + ", transactionType=" + transactionType + ", transactionDate=" + transactionDate + ", sellingPrice=" + sellingPrice + ", totalAmount=" + totalAmount + ", paymentMethod=" + paymentMethod + ", status=" + status + ", notes=" + notes + '}';
    }

    

    
}

