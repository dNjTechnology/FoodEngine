/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.model.Menu;
import com.dnj.fooding.model.MenuDish;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class AddOrderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tableViewForMenu;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<Menu,String> column1=new TableColumn<>("Dish Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Menu,String> column2=new TableColumn<>("Category");
        column2.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<Menu,Double> column3=new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Menu,String> column4=new TableColumn<>("Availability");
        column4.setCellValueFactory(new PropertyValueFactory<>("available"));
        column4.setSortable(false);
        
        
        tableViewForMenu.getColumns().add(column1);
        tableViewForMenu.getColumns().add(column2);
        tableViewForMenu.getColumns().add(column3);
        tableViewForMenu.getColumns().add(column4);
        //tableViewForMenu.getItems().add(dish);
    }    
    
}
