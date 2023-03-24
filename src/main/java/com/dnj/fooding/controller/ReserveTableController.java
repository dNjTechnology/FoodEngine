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
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class ReserveTableController implements Initializable {

    public static Button clickedButton;
    private static Integer custId;
public static Stage stage;
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
      @FXML
     private TextField customerGstin;
     @FXML
     private TextArea customerAddress;
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
        ReserveTableController.stage.close();
        App.subActiveStage=null;
    }
    
    @FXML
    private void closeA(){
        try{
        clickedButton.setDisable(false);
        }
        catch(Exception e){
        ReserveTableController.stage.close();
        App.subActiveStage=null;
        }
        finally{
            ReserveTableController.stage.close();
            ReserveTableController.stage=null;
        
        }
    }
    @FXML
    private void searchCustomer(){
        if(addCustomer.getText().equals("Search")){
        List<Customer> customers=CustomerService.getInstance().searchForCustomer(customerPhone.getText());
        if(customers!=null){
                     customerPhone.textProperty().addListener((observable, oldValue, newValue) -> {
    // do something when the text changes
customerName.setText("");
customerEmail.setText("");
customerAddress.setText("");
customerGstin.setText("");
dropBoxSelection.setVisible(false);
dropBoxSelection.getItems().clear();
    addCustomer.setText("Search");
    addCustomer.setDisable(false);
    
});
             customerEmail.textProperty().addListener((observable, oldValue, newValue) -> {
    // do something when the text changes


    addCustomer.setText("Update");
    addCustomer.setDisable(false);
    
});customerAddress.textProperty().addListener((observable, oldValue, newValue) -> {
    // do something when the text changes


    addCustomer.setText("Update");
    addCustomer.setDisable(false);
    
});
customerGstin.textProperty().addListener((observable, oldValue, newValue) -> {
    // do something when the text changes


    addCustomer.setText("Update");
    addCustomer.setDisable(false);
    
});
        
        
	
            if(customers.size()==1){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("This number is linked to existing customer. Click on 'OK' to Auto fill options.");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() || result.get()==ButtonType.OK) {
               Customer cust= customers.get(0);
               ReserveTableController.reserveForCustomer=cust;
               custId=cust.getId();
               customerName.setText(cust.getName());
               if(cust.getEmail()==null){
               customerEmail.setText("");
               }else{
                  customerEmail.setText(cust.getEmail());
               }
               if(cust.getAddress()==null){
               customerAddress.setText("");
               }else{
                  customerAddress.setText(cust.getAddress());
               }
               if(cust.getGstin()==null){
               customerGstin.setText("");
               }else{
                  customerGstin.setText(cust.getGstin());
               }
            }
   
             
            addCustomer.setText("Add Customer");
            return;
            }
            else{
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("This number is linked to many customer. Click on 'OK' to get options.");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() || result.get()==ButtonType.OK) {
                Map<String,Customer> customerEmailMapping=new HashMap<>();
       for(Customer cust:customers){
           dropBoxSelection.setVisible(true);
           dropBoxSelection.getItems().add(cust.getName());
          customerEmailMapping.put(cust.getName(),cust);
           
       }
            
       dropBoxSelection.setOnAction(e -> {
            String selectedOption = (String) dropBoxSelection.getValue();
            customerName.setText(selectedOption);
            customerEmail.setText(customerEmailMapping.get(selectedOption).getEmail()!=null?customerEmailMapping.get(selectedOption).getEmail():"");
            customerGstin.setText(customerEmailMapping.get(selectedOption).getGstin()!=null?customerEmailMapping.get(selectedOption).getGstin():"");
            customerAddress.setText(customerEmailMapping.get(selectedOption).getAddress()!=null?customerEmailMapping.get(selectedOption).getAddress():"");
            Customer customer=new Customer();
          customer.setEmail(customerEmail.getText());
          customer.setName(customerName.getText());
          customer.setPhone(customerPhone.getText());
          customer.setAddress(customerAddress.getText());
          customer.setGstin(customerGstin.getText());
           ReserveTableController.reserveForCustomer=CustomerService.getInstance().addCustomer(customer);
            addCustomer.setText("Add Customer");
            
            
        });
            }
            }
           
        
    
    
    }
        else{
                     customerPhone.textProperty().addListener((observable, oldValue, newValue) -> {
    // do something when the text changes
customerName.setText("");
customerEmail.setText("");
customerAddress.setText("");
customerGstin.setText("");
dropBoxSelection.setVisible(false);
dropBoxSelection.getItems().clear();
    addCustomer.setText("Search");
    addCustomer.setDisable(false);
    
});
             customerEmail.textProperty().addListener((observable, oldValue, newValue) -> {
    // do something when the text changes


    addCustomer.setText("Update");
    addCustomer.setDisable(false);
    
});customerAddress.textProperty().addListener((observable, oldValue, newValue) -> {
    // do something when the text changes


    addCustomer.setText("Update");
    addCustomer.setDisable(false);
    
});
customerGstin.textProperty().addListener((observable, oldValue, newValue) -> {
    // do something when the text changes


    addCustomer.setText("Update");
    addCustomer.setDisable(false);
    
});

          addCustomer.setText("Add Customer");
          return;
        }
}
        if(addCustomer.getText().equalsIgnoreCase("Add Customer"))
        {
          Customer customer=new Customer();
          customer.setEmail(customerEmail.getText());
          customer.setName(customerName.getText());
          customer.setPhone(customerPhone.getText());
          customer.setGstin(customerGstin.getText());
          customer.setAddress(customerAddress.getText());
          ReserveTableController.reserveForCustomer=CustomerService.getInstance().addCustomer(customer);
          if(ReserveTableController.reserveForCustomer!=null){
          addCustomer.setDisable(true);
          confirmButton.setDisable(false);
          }
          
        }
        if(addCustomer.getText().equalsIgnoreCase("Update")){
           ReserveTableController.reserveForCustomer.setEmail(customerEmail.getText());
           ReserveTableController.reserveForCustomer.setGstin(customerGstin.getText());
           ReserveTableController.reserveForCustomer.setAddress(customerAddress.getText());
           ReserveTableController.reserveForCustomer=CustomerService.getInstance().updateCustomer(ReserveTableController.reserveForCustomer);
          if(ReserveTableController.reserveForCustomer!=null){
          addCustomer.setDisable(true);
          confirmButton.setDisable(false);
          }
        }
        
    }}
