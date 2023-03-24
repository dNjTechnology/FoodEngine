/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;
import com.dnj.fooding.App;
import com.dnj.fooding.dao.BillingDao;
import com.dnj.fooding.dao.TablesDineInDao;
import com.dnj.fooding.model.Billing;
import com.dnj.fooding.model.CurrentTableBooking;
import com.dnj.fooding.model.Order;
import com.dnj.fooding.model.TablesDineIn;
import com.dnj.fooding.model.support.Status;
import java.util.List;

/**
 *
 * @author Animesh Samanta
 */
public class BillingService {
     private static BillingService instance;
     private void BillingService(){
        
    }
    public static BillingService getInstance(){
        if(instance==null){
            instance=new BillingService();
            return instance;
        }
        return instance;
    }
    public static Billing collectBillForTable(TablesDineIn table){
        CurrentTableBooking currentTableBooking=TablesDineInDao.getInstance().getCurrentTableWorkFlow(table);
        List<Order> orders=currentTableBooking.getOrders();
        Billing bill=new Billing();
        double amount=0.0;
        double amountAfterTax=0.0;
        double sgstAmount=0.0;
        double cgstAmount=0.0;
        for(Order order:orders){
            amount+=order.getQuantity()*order.getMenu().getItemPrice();
        }
        if(App.APPLICATION_PREF.containsKey("APPLY_TAX")&&App.APPLICATION_PREF.get("APPLY_TAX").equalsIgnoreCase("yes")){
            double sgst=App.APPLICATION_PREF.containsKey("SGST")?Double.valueOf(App.APPLICATION_PREF.get("SGST")):0.0;
            double cgst=App.APPLICATION_PREF.containsKey("CGST")?Double.valueOf(App.APPLICATION_PREF.get("CGST")):0.0;
            if(App.APPLICATION_PREF.containsKey("ADJUST_BILL_WITH_TAX")&&App.APPLICATION_PREF.get("ADJUST_BILL_WITH_TAX").equalsIgnoreCase("no")){
                sgstAmount=amount*(sgst/100.0);
                cgstAmount=amount*(cgst/100.0);
                amountAfterTax+=amount+sgstAmount+cgstAmount;
            }
            else{
        sgstAmount = (amount * cgst) / 100;
        cgstAmount = (amount * sgst) / 100;
        amountAfterTax=amount;
        amount= amount - (sgstAmount + cgstAmount);
            }
            bill.setCgst(String.valueOf(cgstAmount));
            bill.setSgst(String.valueOf(sgstAmount));
            bill.setCustomer(currentTableBooking.getCustomer());
            bill.setTotalAmount(amountAfterTax);
            bill.setSellingPrice(amount);
            bill.setStatus(Status.PROCESSING);
            
        }
        else{
            bill.setCgst(null);
            bill.setCgst(null);
            bill.setCustomer(currentTableBooking.getCustomer());
            bill.setTotalAmount(amount);
            bill.setSellingPrice(amount);
            bill.setStatus(Status.PROCESSING);
        }
    bill=BillingDao.getInstance().initiateBilling(bill);
    return bill;
}
}
