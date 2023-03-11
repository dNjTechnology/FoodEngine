/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.model.MenuDish;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class EditMenuFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField dishnameInput;
    @FXML
    private TextField priceTextField;
    @FXML
    private ComboBox categorySelection;
    @FXML
    private CheckBox availabilitydishCheck;
    @FXML
    private Label labelForCat;
    
    
    private static TextField dishnameInputstatic;
    
    private static TextField priceTextFieldstatic;
    
    private static ComboBox categorySelectionstatic;
    
    private static CheckBox availabilitydishCheckstatic;
    private static Stage edistStage;
    private static Label labelForCatstatic;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dishnameInputstatic=dishnameInput;
        priceTextFieldstatic=priceTextField;
        categorySelectionstatic=categorySelection;
        availabilitydishCheckstatic=availabilitydishCheck;
        labelForCatstatic=labelForCat;
    }
public static void loadEditForm(MenuDish menu,Stage stage){
    edistStage=stage;
    dishnameInputstatic.setText(menu.getName());
    priceTextFieldstatic.setText(menu.getPrice().toString());
    labelForCatstatic.setText(menu.getCategory());
    categorySelectionstatic.getItems().add("Chinese");
    
    if(menu.getAvailable().equalsIgnoreCase("Yes")){
        availabilitydishCheckstatic.setSelected(true);
    }
    else{
        availabilitydishCheckstatic.setSelected(false);
    }
} 
public void close(){
    edistStage.close();
} 
    
}
