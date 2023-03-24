/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import com.dnj.fooding.model.Menu;
import com.dnj.fooding.service.MenuService;
import com.dnj.fooding.model.MenuDish;
import com.dnj.fooding.model.Order;
import com.dnj.fooding.service.KitchenService;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class AddOrderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static Map<Menu,Integer> orders;
    public static Stage stage;
    public static Stage stage1;
    public static boolean isRoutFromNextSceen=false;
    @FXML
    private TableView tableViewForMenu;
    @FXML
    private TableView tableViewFinalOrder;
    private static TableView tableViewFinalOrderstatic;
    @FXML
    private ComboBox categoryChoice;
    @FXML
    private ComboBox itemChoice;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(!AddOrderController.isRoutFromNextSceen){
            tableViewFinalOrderstatic=tableViewFinalOrder;
            orders=new HashMap<>();
        TableColumn<Menu,String> column1=new TableColumn<>("Dish Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        TableColumn<Menu,String> column2=new TableColumn<>("Category");
        column2.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<Menu,Double> column3=new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        
        tableViewForMenu.getColumns().add(column1);
        tableViewForMenu.getColumns().add(column2);
        tableViewForMenu.getColumns().add(column3);
        List<Menu> listMenu=MenuService.getInstance().filterByCategory(null,null);
        tableViewForMenu.getItems().addAll(listMenu);
        
        addButtonToTable();//Add button to table
        categoryChoice.getItems().add("All");
        categoryChoice.getItems().addAll(MenuService.getInstance().getAllCategory());
        }
        else {
            
    ObservableList<Map.Entry<Menu,Integer>> items = FXCollections.observableArrayList(orders.entrySet());

    TableColumn<Map.Entry<Menu,Integer>, String> nameColumn = new TableColumn<>("Item Name");
    nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey().getItemName()));
    tableViewFinalOrder.getColumns().add(nameColumn);

    TableColumn<Map.Entry<Menu,Integer>, String> categoryColumn = new TableColumn<>("Category");
    categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey().getCategory()));
    tableViewFinalOrder.getColumns().add(categoryColumn);

    TableColumn<Map.Entry<Menu,Integer>, Double> priceColumn = new TableColumn<>("Price");
    priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getKey().getItemPrice()).asObject());
    tableViewFinalOrder.getColumns().add(priceColumn);

    TableColumn<Map.Entry<Menu,Integer>, Integer> quantityColumn = new TableColumn<>("Quantity");
    quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getValue()).asObject());
    quantityColumn.setCellFactory(column -> new TableCell<Entry<Menu,Integer>,Integer>() {
            private TextField textField;
            
            @Override
            public void startEdit() {
                super.startEdit();
                
                if (textField == null) {
                    createTextField();
                }
                
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
            
            @Override
            public void cancelEdit() {
                super.cancelEdit();
                setText(getItem().toString());
                setGraphic(null);
            }
            
            @Override
            public void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (isEditing()) {
                        if (textField != null) {
                            textField.setText(getString());
                        }
                        
                        setText(null);
                        setGraphic(textField);
                    } else {
                        setText(getString());
                        setGraphic(null);
                    }
                }
                
            }
            
            private void createTextField() {
                textField = new TextField(getString());
                textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
                textField.setOnAction(event -> commitEdit(Integer.parseInt(textField.getText())));
                textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        commitEdit(Integer.parseInt(textField.getText()));
                    }
                });
            }
            
            private String getString() {
                return getItem() == null ? "" : getItem().toString();
            }
        });
quantityColumn.setOnEditCommit(event -> {
    TablePosition<Entry<Menu, Integer>, Integer> pos = event.getTablePosition();
    Entry<Menu, Integer> entry = pos.getTableView().getItems().get(pos.getRow());
    
    Integer newQuantity = event.getNewValue();
    if(newQuantity!=0){
    orders.put(entry.getKey(),newQuantity);
    }else{
        orders.remove(entry.getKey());
    }

    // do something with the new quantity and entry
});
    tableViewFinalOrder.getColumns().add(quantityColumn);
     TableColumn<Map.Entry<Menu,Integer>, Double> amountColumn = new TableColumn<>("Amount");
     //((Integer)cellData.getValue().getKey())*((Integer)cellData.getValue().getValue())
     //
    amountColumn.setCellValueFactory(cellData->{
        double priceOfItem=(cellData.getValue().getKey().getItemPrice())*cellData.getValue().getValue();
        return new SimpleDoubleProperty(priceOfItem).asObject();
    });
   
    tableViewFinalOrder.getColumns().add(amountColumn);

    tableViewFinalOrder.setItems(items);
    tableViewFinalOrder.setEditable(true);
    
}

        
    }
private void addButtonToTable() {
        TableColumn<Menu, Void> colBtn = new TableColumn("");
TableColumn<Menu, Void> colBtn2 = new TableColumn("");
TableColumn<Menu, Integer> colCount = new TableColumn<>("Quantity");
        Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>> cellFactory = new Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>>() {
            @Override
            public TableCell<Menu, Void> call(final TableColumn<Menu, Void> param) {
                final TableCell<Menu, Void> cell = new TableCell<Menu, Void>() {

                    private final Button btn = new Button("Add");
                   {
                        btn.setOnAction((ActionEvent event) -> {
                            Menu data = getTableView().getItems().get(getIndex());
                            if(orders.get(data)!=null){
                                orders.put(data, orders.get(data)+1);
                            }
                                else{
                                 orders.put(data,1);
                            }
                            tableViewForMenu.refresh();
                           
                        });
                    } 

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>> cellFactory2 = new Callback<TableColumn<Menu, Void>, TableCell<Menu, Void>>() {
            @Override
            public TableCell<Menu, Void> call(final TableColumn<Menu, Void> param) {
                final TableCell<Menu, Void> cell2 = new TableCell<Menu, Void>() {

                    private final Button removebtn = new Button("Remove");
                   {
                        removebtn.setOnAction((ActionEvent event) -> {
                            Menu data = getTableView().getItems().get(getIndex());
                            if(orders.get(data)!=null){
                                if(orders.get(data)==1){
                                    orders.remove(data);
                                }else{
                                orders.put(data, orders.get(data)-1);
                                }
                            }
                                else{
                                 Alert a=new Alert(Alert.AlertType.INFORMATION);
                                 a.setContentText("Item is not in the orderlist");
                                 a.show();
                                         
                            }
                            tableViewForMenu.refresh();
                           
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(removebtn);
                        }
                    }
                };
                return cell2;
            }
        };
        colCount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Menu, Integer>, ObservableValue<Integer>>() {
    @Override
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Menu, Integer> param) {
        Menu data = param.getValue();
        if (orders.get(data) != null) {
            return new SimpleIntegerProperty(orders.get(data)).asObject();
        } else {
            return new SimpleIntegerProperty(0).asObject();
        }
    }
});
colBtn.setCellFactory(cellFactory);
        colBtn2.setCellFactory(cellFactory2);
        
tableViewForMenu.getColumns().add(colCount);
        tableViewForMenu.getColumns().add(colBtn);
        tableViewForMenu.getColumns().add(colBtn2);
        

    }
@FXML
private void filterMenu(){
    filterMenuCustom();
}
@FXML
private void closeStage(){
    AddOrderController.stage.close();
    
}
@FXML
private void nextWindow(){
    AddOrderController.stage.hide();
    AddOrderController.isRoutFromNextSceen=true;
    AddOrderController.stage1=new Stage();
    
    App.setIconForStage(stage1);
            try {
                                Scene scene = new Scene(App.loadFXML("addOrderNext1"), 640, 480);
                                AddOrderController.stage1.setScene(scene);
                                AddOrderController.stage1.setResizable(false);
        AddOrderController.stage1.initStyle(StageStyle.UNDECORATED);
        AddOrderController.stage1.show();
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
}
@FXML
private void backToOrdering(){
    AddOrderController.stage1.close();
    AddOrderController.stage.show();
    App.setIconForStage(AddOrderController.stage);
    AddOrderController.isRoutFromNextSceen=false;
}
@FXML
private void setItemsCombo(){
    List<Menu> list=MenuService.getInstance().filterByCategory(categoryChoice.getSelectionModel().getSelectedItem().toString());
    itemChoice.getItems().clear();
    itemChoice.getItems().add("All");
    for(Menu menu:list){
    itemChoice.getItems().addAll(menu.getItemName());
    }
    filterMenuCustom();
    }

public void filterMenuCustom(){
    String item;
    String category;
    try{
        item=itemChoice.getSelectionModel().getSelectedItem().toString();
    }
    catch(NullPointerException e){
        item=null;
    }
        try{
        category=categoryChoice.getSelectionModel().getSelectedItem().toString();
    }
    catch(NullPointerException e){
        category=null;
    }
    List<Menu> listMenu=MenuService.getInstance().filterByCategory(category,item);
     tableViewForMenu.getItems().clear();
    tableViewForMenu.getColumns().remove(tableViewForMenu.getColumns().size()-1);
        tableViewForMenu.getColumns().remove(tableViewForMenu.getColumns().size()-1); 
        tableViewForMenu.getColumns().remove(tableViewForMenu.getColumns().size()-1); 
    tableViewForMenu.getItems().addAll(listMenu);
    addButtonToTable();
}

@FXML
private void sendOrderToKitchen(){
    KitchenService.getInstance().sendOrderInKitchen(orders, ViewTableOrderController.servingCustomer, ViewTableOrderController.table);
    orders.clear();
    AddOrderController.stage1.close();
    AddOrderController.stage.close();
    
    
}

    
}
