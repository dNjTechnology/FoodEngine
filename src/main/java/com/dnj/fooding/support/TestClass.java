/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

/**
 *
 * @author Animesh Samanta
 */
public class TestClass {
    public static void main(String[] args) {
        collectBillForTable();
    }
    public static void collectBillForTable(){
        double amount=1000.00;
        double cgst=2.5;
        double sgst=2.5;
        double amountAfterTax=0.0;
        double sgstAmount=0.0;
        double cgstAmount=0.0;
        sgstAmount = (amount * cgst) / 100;
        cgstAmount = (amount * sgst) / 100;
        
        // Calculate the actual price of the product
        double actualPrice = amount - (sgstAmount + cgstAmount);
        
        // Display the results
        System.out.println("SGST: " + sgstAmount);
        System.out.println("CGST: " + cgstAmount);
        System.out.println("Actual price: " + actualPrice);
      System.out.println("Selling price: " + amount);
            
        }
    }

