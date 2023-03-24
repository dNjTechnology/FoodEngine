/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.model.Order;
import com.dnj.fooding.service.ManageKitchenService;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class MangeKitchenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static  List<Order> orders;
    private static TableView<Order> kitchenTableViewStatic;
    @FXML
    private TableView<Order> kitchenTableView;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MangeKitchenController.kitchenTableViewStatic=kitchenTableView;
        MangeKitchenController.orders=ManageKitchenService.getInstance().getOrdersKitchen();
        createTable();
        
    }
    public void createTable(){
    TableColumn<Order, Integer> idColumn = new TableColumn<>("Order Id");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));

    TableColumn<Order, String> menuColumn = new TableColumn<>("Dish");
    menuColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenu().getItemName()));

   
    TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Quantity");
    quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    TableColumn<Order, Integer> statusColumn = new TableColumn<>("Status");
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    TableColumn<Order, Void> actionColumn = new TableColumn<>("Action");
    
    TableColumn<Order,Integer> tableColumn = new TableColumn<>("Table Number");
    tableColumn.setCellValueFactory(cellData ->{
         IntegerProperty property = new SimpleIntegerProperty(cellData.getValue().getTablesDineIn().getTableNumber());
         int value = property.get(); 
    return new ReadOnlyIntegerWrapper(value).asObject();
    });
kitchenTableView.setRowFactory(tv -> new TableRow<Order>() {
    @Override
    public void updateItem(Order item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setStyle("");
        } else if (item.getStatus().equals("Ready")) {
            setStyle("-fx-background-color: #a2c892;");
        } else if (item.getStatus().equals("Preparing")) {
            setStyle("-fx-background-color: #fff68f;");
        }else if (item.getStatus().equals("In Kitchen")) {
            setStyle("-fx-background-color:#e47b88;");
        } 
        else {
            setStyle("");
        }
    }
});

actionColumn.setCellFactory(param -> new TableCell<Order, Void>() {
    private final Button button = new Button("Action");

    {
        button.setOnAction(event -> {
            Order order = getTableView().getItems().get(getIndex());
           if (order.getStatus().equals("In Kitchen")) {
                button.setText("Start Cooking");
                order.setStatus("Preparing");
                ManageKitchenService.getInstance().setOrderStatus(order);
                //on click set status as To Preparing
            } else if (order.getStatus().equals("Preparing")) {
                button.setText("Completed");
                order.setStatus("Food Ready");
                ManageKitchenService.getInstance().setOrderStatus(order);
                //on click set status as to Food Ready
            }else if (order.getStatus().equals("Food Ready")) {
                button.setText("Serve");
                order.setStatus("Serving");
                ManageKitchenService.getInstance().setOrderStatus(order);
                //on click set status as to Serving
            }
            else if (order.getStatus().equals("Serving")) {
                button.setText("Serving Completed");
                order.setStatus("Completed");
                ManageKitchenService.getInstance().setOrderStatus(order);
                
            }
        });
    }

    @Override
    public void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            Order order = getTableView().getItems().get(getIndex());
            if (order.getStatus().equals("In Kitchen")) {
                button.setText("Start Cooking");
                //on click set status as To Preparing
            } else if (order.getStatus().equals("Preparing")) {
                button.setText("Completed");
                //on click set status as to Food Ready
            }else if (order.getStatus().equals("Food Ready")) {
                button.setText("Serve");
                //on click set status as to Serving
            }
            else if (order.getStatus().equals("Serving")) {
                button.setText("Serving Completed");
                
            }
            setGraphic(button);
            setStyle("-fx-alignment: CENTER;");
        }
    }
});



kitchenTableView.setItems(FXCollections.observableArrayList(orders));
   kitchenTableView.getColumns().setAll(idColumn, menuColumn, quantityColumn,statusColumn,tableColumn,actionColumn);
    }
    public static void updateView(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                kitchenTableViewStatic.setItems(FXCollections.observableArrayList(orders));
                kitchenTableViewStatic.refresh();
                }catch(Exception e){
                    
                }
            }});
    }
    
    

 


    
}
