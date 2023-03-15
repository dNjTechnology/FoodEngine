/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import static com.dnj.fooding.App.loadFXML;
import static com.dnj.fooding.App.scene;
import com.dnj.fooding.model.CurrentTableBooking;
import com.dnj.fooding.model.TablesDineIn;
import com.dnj.fooding.service.TableDineInService;
import com.dnj.fooding.support.HibernateUtil;
import com.dnj.fooding.support.RealTimeSystem;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class TableDineInController implements Initializable {

    
    @FXML
private GridPane gripPaneForTableView;
    public static GridPane gripPaneForTableViewstatic;
    public static List<TablesDineIn> tables;
    public static List<TablesDineIn> newTableList;
    public static Map<Integer,VBox> tabletoVboxMap=new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        gripPaneForTableView.setPrefHeight(400);
         gripPaneForTableView.setPrefHeight(600);
         
        gripPaneForTableViewstatic=gripPaneForTableView;
        System.out.println(gripPaneForTableViewstatic.toString());
        tables=TableDineInService.getInstance().getAllTables();
        createView();
        if(!App.isTableInspectOn){
            App.isTableInspectOn=true;
        Thread t2 = new Thread(new RealTimeSystem());
        t2.start();
        }
    }
public void createView() {
    double noOfCellRequired = (double) tables.size();
    int numberOfRows = (int) Math.ceil(noOfCellRequired / 6.0);
    int numberOfColumns = (int) Math.ceil(noOfCellRequired / numberOfRows);

    for (int i = 0; i < numberOfRows; i++) {
        RowConstraints row = new RowConstraints();
        row.setPrefHeight(200); // Set the height of the row
        
        gripPaneForTableView.getRowConstraints().add(row);
    }

    for (int i = 0; i < numberOfColumns; i++) {
        ColumnConstraints col = new ColumnConstraints();
        col.setPrefWidth(300); // Set the width of the column
        gripPaneForTableView.getColumnConstraints().add(col);
    }

   

    for (int i = 0; i < numberOfRows; i++) {
        for (int j = 0; j < numberOfColumns; j++) {
            // calculate the index of the current table
            int index = i * numberOfColumns + j;

            // check if the index is less than the number of tables
            if (index < tables.size()) {
                Image image=null;
                HBox hbox=new HBox();
              
                if(tables.get(index).isAvailable())
                {
                     image = new Image("file:///C:/freetable.jpg");
                     Button reserve = new Button("Reserve table "+tables.get(index).getTableNumber());
reserve.setOnAction(event ->{ 
   Stage stage=new Stage();
   ReserveTableController.currentSelection=tables.get(index);
   ReserveTableController.clickedButton=reserve;
   App.subActiveStage=stage;
                         try {
                             scene = new Scene(App.loadFXML("reserveTable"), 640, 480);
                         }
                         catch (IOException ex) {
                             Logger.getLogger(TableDineInController.class.getName()).log(Level.SEVERE, null, ex);
                         }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        reserve.setDisable(true);
});
                     hbox.getChildren().add(0, reserve);
                }
                else{
                    Button viewOrderForTable = new Button("View/Order table "+tables.get(index).getTableNumber());
viewOrderForTable.setOnAction(event->{
     TablesDineIn tempTable=new TablesDineIn();
        tempTable.setTableNumber(tables.get(index).getTableNumber());
        tempTable.setAvailable(true);
        tempTable.setSeatingCapacity(tables.get(index).getSeatingCapacity());
    CurrentTableBooking booking=TableDineInService.getInstance().getTableWorkFlow(tempTable);
        ViewTableOrderController.servingCustomer=booking.getCustomer();
        ViewTableOrderController.ordersList=booking.getOrders();
        ViewTableOrderController.table=booking.getTable();
        if( ViewTableOrderController.servingCustomer!=null && !ViewTableOrderController.servingCustomer.getName().isBlank()){
            Stage stage=new Stage();
            App.subActiveStage=stage;
             try {
                             scene = new Scene(App.loadFXML("viewTableOrder"), 640, 480);
                         }
                         catch (IOException ex) {
                             Logger.getLogger(TableDineInController.class.getName()).log(Level.SEVERE, null, ex);
                         }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();  }      
});

                     hbox.getChildren().add(0, viewOrderForTable);
                    image = new Image("file:///C:/busytable.jpg");
                }
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(100);
imageView.setFitWidth(100);
VBox vbox = new VBox();
tabletoVboxMap.put(tables.get(index).getTableNumber(), vbox);

vbox.getChildren().add(imageView);
vbox.getChildren().add(hbox);                
                
               

                gripPaneForTableView.add(vbox, j, i);
            }
        }
    }

    System.out.println("Grid setup completed");
}
public static void updateView(Map<Integer,Boolean> map,List<TablesDineIn> tables){
    try{
    Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(Map.Entry<Integer,Boolean> changeMap : map.entrySet()){
                     Optional<TablesDineIn> result = tables.stream().filter(s -> s.getTableNumber()==changeMap.getKey()).findFirst();
                    VBox vbox=tabletoVboxMap.get(changeMap.getKey());
                    HBox hbox=(HBox)vbox.getChildren().get(1);
                    
                    
                    Image image=null;
                    if(changeMap.getValue())
                {
                     image = new Image("file:///C:/freetable.jpg");
                     Button reserve = new Button("Reserve table "+changeMap.getKey());
reserve.setOnAction(event ->{ 
   Stage stage=new Stage();
  
   ReserveTableController.currentSelection=result.get();
   App.subActiveStage=stage;
                         try {
                             scene = new Scene(App.loadFXML("reserveTable"), 640, 480);
                         }
                         catch (IOException ex) {
                             Logger.getLogger(TableDineInController.class.getName()).log(Level.SEVERE, null, ex);
                         }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
});
                     hbox.getChildren().set(0, reserve);
                }
                else{
                         Button viewOrderForTable = new Button("View/Order table "+changeMap.getKey());
viewOrderForTable.setOnAction(event->{
     TablesDineIn tempTable=new TablesDineIn();
        tempTable.setTableNumber(changeMap.getKey());
        tempTable.setAvailable(false);
        tempTable.setSeatingCapacity(result.get().getSeatingCapacity());
         CurrentTableBooking booking=TableDineInService.getInstance().getTableWorkFlow(tempTable);
        ViewTableOrderController.servingCustomer=booking.getCustomer();
        ViewTableOrderController.ordersList=booking.getOrders();
        ViewTableOrderController.table=booking.getTable();
        if( ViewTableOrderController.servingCustomer!=null && !ViewTableOrderController.servingCustomer.getName().isBlank()){
            Stage stage=new Stage();
            App.subActiveStage=stage;
             try {
                             scene = new Scene(App.loadFXML("viewTableOrder"), 640, 480);
                         }
                         catch (IOException ex) {
                             Logger.getLogger(TableDineInController.class.getName()).log(Level.SEVERE, null, ex);
                         }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
        }
});
  hbox.getChildren().set(0, viewOrderForTable);
                    image = new Image("file:///C:/busytable.jpg");
                }
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(100);
imageView.setFitWidth(100);




vbox.getChildren().set(0,imageView);

                }
                TableDineInController.tables=tables;
            }
        });
    }
    catch(Exception e){
        
    }
}

    
}
