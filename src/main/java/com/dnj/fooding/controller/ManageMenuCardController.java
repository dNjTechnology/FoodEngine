/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;


import com.dnj.fooding.App;
import com.dnj.fooding.model.MenuDish;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class ManageMenuCardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tableViewForMenu;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<MenuDish,String> column1=new TableColumn<>("Dish Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<MenuDish,String> column2=new TableColumn<>("Category");
        column2.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<MenuDish,Double> column3=new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<MenuDish,String> column4=new TableColumn<>("Availability");
        column4.setCellValueFactory(new PropertyValueFactory<>("available"));
        column4.setSortable(false);
        
        
        tableViewForMenu.getColumns().add(column1);
        tableViewForMenu.getColumns().add(column2);
        tableViewForMenu.getColumns().add(column3);
        tableViewForMenu.getColumns().add(column4);
        MenuDish dish=new MenuDish();
        dish.setAvailable("Yes");
        dish.setCategory("Indian");
        dish.setName("Biriyani");
        dish.setPrice(225.0);
        tableViewForMenu.getItems().add(dish);
        addButtonToTable();
        
        
        
    }    
    private void addButtonToTable() {
        TableColumn<MenuDish, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<MenuDish, Void>, TableCell<MenuDish, Void>> cellFactory = new Callback<TableColumn<MenuDish, Void>, TableCell<MenuDish, Void>>() {
            @Override
            public TableCell<MenuDish, Void> call(final TableColumn<MenuDish, Void> param) {
                final TableCell<MenuDish, Void> cell = new TableCell<MenuDish, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            MenuDish data = getTableView().getItems().get(getIndex());
                            Stage stage=new Stage();
                           Scene scene;
                            try {
                                scene = new Scene(App.loadFXML("editMenuForm"), 640, 480);
                                stage.setScene(scene);
                                stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        EditMenuFormController.loadEditForm(data,stage);
                            }
                            catch (IOException ex) {
                                Logger.getLogger(ManageMenuCardController.class.getName()).log(Level.SEVERE, null, ex);
                            }
        
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

        colBtn.setCellFactory(cellFactory);

        tableViewForMenu.getColumns().add(colBtn);

    }
    
}
