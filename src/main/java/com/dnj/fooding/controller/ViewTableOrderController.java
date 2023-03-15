/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import com.dnj.fooding.model.Customer;
import com.dnj.fooding.model.Menu;
import com.dnj.fooding.model.MenuDish;
import com.dnj.fooding.model.Order;
import com.dnj.fooding.model.TablesDineIn;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class ViewTableOrderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static Customer servingCustomer;
    public static TablesDineIn table;
    public static List<Order> ordersList;
    @FXML
     private TextField customerName;
     @FXML
     private TextField customerPhone;
     @FXML
     private TextField customerEmail;
     @FXML
     private Label forTable;
     @FXML
     private TableView<Order> tabViewOrder;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        forTable.setText("Managing table "+table.getTableNumber());
        customerName.setText(servingCustomer.getName());
        customerName.setEditable(false);
        customerPhone.setText(servingCustomer.getPhone());
        customerPhone.setEditable(false);
        if(servingCustomer.getEmail()!=null){
            customerEmail.setText(servingCustomer.getEmail());
        }
        else{
            customerEmail.setText("Not available");
        }
        customerEmail.setEditable(false);
        renderOrderTable(ordersList);
    }
    public void closeA(){
        App.subActiveStage.close();
    }
  
public void renderOrderTable(List<Order> ordersList) {
    tabViewOrder.getItems().setAll(ordersList);

    // Set up columns
    TableColumn<Order, Integer> idColumn = new TableColumn<>("Order Id");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));

    TableColumn<Order, String> menuColumn = new TableColumn<>("Dish");
    menuColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenu().getItemName()));

    TableColumn<Order, String> priceColumn = new TableColumn<>("Price");
    priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMenu().getItemPrice())));

    TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Quantity");
    quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

   tabViewOrder.getColumns().setAll(idColumn, menuColumn, priceColumn, quantityColumn);

   
    
}
    
    
}
