/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;



import com.dnj.fooding.model.Billing;
import com.dnj.fooding.model.Customer;
import com.dnj.fooding.model.Order;
import com.dnj.fooding.model.TablesDineIn;
import com.dnj.fooding.service.BillingService;
import com.dnj.fooding.service.TableDineInService;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class BillingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private WebView webViewBilling;
    public static List<Order> ordersList;
    public static TablesDineIn table;
    private static Double amount;
    private static Double total;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //String htmlContent = "<html><body><h1>Hello, World!</h1></body></html>";
        fetchBill();
        webViewBilling.getEngine().loadContent(createBill());
        
    }
public static void fetchBill(){
   BillingController.createBilling();
    BillingController.ordersList=TableDineInService.getInstance().getOrderFor(ViewTableOrderController.table);
    
} 
public String createBill(){
    String html="<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"	<title>Pre-Invoice</title>\n" +
"	<style>\n" +
"		body {\n" +
"			font-family: Arial, sans-serif;\n" +
"			margin: 0;\n" +
"			padding: 0;\n" +
"		}\n" +
"		h1 {\n" +
"			text-align: center;\n" +
"			margin: 20px 0;\n" +
"		}\n" +
"		table {\n" +
"			width: 80%;\n" +
"			margin: 0 auto;\n" +
"			border-collapse: collapse;\n" +
"			border: 1px solid #000;\n" +
"		}\n" +
"		th, td {\n" +
"			padding: 10px;\n" +
"			text-align: left;\n" +
"			border: 1px solid #000;\n" +
"		}\n" +
"		.total {\n" +
"			font-weight: bold;\n" +
"			text-align: right;\n" +
"		}\n" +
"		.tax {\n" +
"			font-weight: bold;\n" +
"			text-align: right;\n" +
"		}\n" +
"	</style>\n" +
"</head>\n" +
"<body>\n" +
"	<h1>Pre-Invoice</h1>\n" +
"	<table>\n" +
"		<thead>\n" +
"			<tr>\n" +
"				<th>Product Name</th>\n" +
"				<th>Quantity</th>\n" +
"				<th>Price (per unit)</th>\n" +
"				<th>Total</th>\n" +
"			</tr>\n" +
"		</thead>\n" +
"		<tbody>\n" +
"			<tr>\n" +
            getOrder()+
"			</tr>\n" +
"			<tr>\n" +
"				<td colspan=\"3\" class=\"total\">"+amount+"</td>\n" +
"				<td>250</td>\n" +
"			</tr>\n" +
"			<tr>\n" +
"				<td colspan=\"3\" class=\"tax\">CGST (9%)</td>\n" +
"				<td>"+amount*(9.0/100.0)+"</td>\n" +
"			</tr>\n" +
"			<tr>\n" +
"				<td colspan=\"3\" class=\"tax\">SGST (9%)</td>\n" +
"				<td>"+amount*(9.0/100.0)+"</td>\n" +
"			</tr>\n" +
"			<tr>\n" +
"				<td colspan=\"3\" class=\"total\">Grand Total</td>\n" +
"				<td>"+((amount+amount*(9.0/100.0)+amount*(9.0/100.0)))+"</td>\n" +
"			</tr>\n" +
"		</tbody>\n" +
"	</table>\n" +
"</body>\n" +
"</html>";
    return html;
}
public String getOrder(){
    amount=0.0;
    StringBuffer html=new StringBuffer();
    for(Order order:ordersList){
       String item="<tr>\n" +
"<td>"+order.getMenu().getItemName()+"</td>\n" +
"<td>"+order.getQuantity()+"</td>\n" +
"<td>"+order.getMenu().getItemPrice()+"</td>\n" +
"<td>"+order.getMenu().getItemPrice()*order.getQuantity()+"</td>\n" +
"</tr>\n"; 
       html.append(item);
       amount+=order.getMenu().getItemPrice()*order.getQuantity();
       //amount+=amount+(amount*(9.0/100.0)+amount*(9.0/100.0));
    }
    total=amount+(amount*(9.0/100.0)+amount*(9.0/100.0));
    return html.toString();
    
}
public static void createBilling(){
    Billing bill=BillingService.getInstance().collectBillForTable(BillingController.table);
    if(bill==null){
        return;
    }
    System.out.println(bill.toString());
    
}

    
}
