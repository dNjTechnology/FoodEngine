/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import com.dnj.fooding.dao.TablesDineInDao;
import com.dnj.fooding.model.Customer;
import com.dnj.fooding.model.TablesDineIn;
import com.dnj.fooding.service.CustomerService;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class ReserveTableController implements Initializable {

    public static Button clickedButton;
    

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label tableNumberLable;
     @FXML
    private Label seatingCapacityLable;
     @FXML
     private TextField customerName;
     @FXML
     private TextField customerPhone;
     @FXML
     private TextField customerEmail;
      @FXML
     private VBox vBoxId;
      @FXML
      private Button addCustomer;
       @FXML
      private Button confirmButton;
      @FXML
      private ChoiceBox dropBoxSelection;
      
      public static Customer reserveForCustomer;
    public static TablesDineIn currentSelection;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        confirmButton.setDisable(true);
        if(ReserveTableController.currentSelection!=null){
        tableNumberLable.setText(currentSelection.getTableNumber()+"");
        seatingCapacityLable.setText(currentSelection.getSeatingCapacity()+"");
        }
    }  
    @FXML
    private void reserveTableForDineIn(){
        
        
        TablesDineInDao.getInstance().reserveTable(ReserveTableController.currentSelection,ReserveTableController.reserveForCustomer);
        App.subActiveStage.close();
        App.subActiveStage=null;
    }
    
    @FXML
    private void closeA(){
        clickedButton.setDisable(false);
        App.subActiveStage.close();
        App.subActiveStage=null;
    }
    @FXML
    private void searchCustomer(){
        if(addCustomer.getText().equals("Search")){
        List<Customer> customers=CustomerService.getInstance().searchForCustomer(customerPhone.getText());
        if(customers!=null){
            
        
        
	
            if(customers.size()==1){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("This number is linked to existing customer. Click on 'OK' to Auto fill options.");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() || result.get()==ButtonType.OK) {
               Customer cust= customers.get(0);
               customerName.setText(cust.getName());
               if(cust.getEmail()==null){
               customerEmail.setText("");
               }else{
                  customerEmail.setText(cust.getEmail());
               }
            }
            }
            else{
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("This number is linked to many customer. Click on 'OK' to get options.");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() || result.get()==ButtonType.OK) {
                Map<String,String> customerEmailMapping=new HashMap<>();
       for(Customer cust:customers){
           dropBoxSelection.setVisible(true);
           dropBoxSelection.getItems().add(cust.getName());
          customerEmailMapping.put(cust.getName(), cust.getEmail());
           
       }
            
       dropBoxSelection.setOnAction(e -> {
            String selectedOption = (String) dropBoxSelection.getValue();
            customerName.setText(selectedOption);
            customerEmail.setText(customerEmailMapping.get(selectedOption));
            confirmButton.setDisable(false);
            addCustomer.setText("Add Customer");
            
            
        });
            }
            }
           
        
    
    
    }
        else{
          addCustomer.setText("Add Customer");  
        }
}
        if(addCustomer.getText().endsWith("Add Customer"))
        {
          Customer customer=new Customer();
          customer.setEmail(customerEmail.getText());
          customer.setName(customerName.getText());
          customer.setPhone(customerPhone.getText());
          ReserveTableController.reserveForCustomer=CustomerService.getInstance().addCustomer(customer);
          if(ReserveTableController.reserveForCustomer!=null){
          addCustomer.setDisable(true);
          confirmButton.setDisable(false);
          }
          
        }
        
    }}
