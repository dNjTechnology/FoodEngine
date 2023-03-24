/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import static com.dnj.fooding.controller.ServiceManController.staticUserNameDiaplay;
import com.dnj.fooding.support.AESEncryption;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class StaffManagementController implements Initializable {

    @FXML
    private BorderPane mainpane;
    @FXML
    private MenuButton userNameDisplay;
    @FXML
    private VBox vboxButtonsService;
    public static MenuButton staticUserNameDiaplay;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     vboxButtonsService.setSpacing(5);
                staticUserNameDiaplay=userNameDisplay;
                addBotton();
//        Object object=App.loadFXMLPanel("homeService");
//        if(object instanceof Pane){
//       Pane pane=(Pane)object;
//       mainpane.setCenter(pane);
//               }
//        else if(object instanceof ScrollPane){
//            ScrollPane pane=(ScrollPane)object;
//          mainpane.setCenter(pane);  
//        }
        userNameDisplay.setText(App.currentUser.getName());
        
        // userNameDisplay= new MenuButton("menuButton");
 
        // create menuitems
        MenuItem m1 = new MenuItem("Role:-"+App.currentUser.getDesignation());
        MenuItem m2 = new MenuItem("My Access");
        MenuItem m3 = new MenuItem("My Account");
        MenuItem m4 = new MenuItem("Create USB Key");
 m2.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.INFORMATION, "Access Network", ButtonType.OK);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.setContentText("My Access Privilage:-"+App.currentUser.getAccess().toString());
        a.setWidth(300);
        a.setHeight(100);
        a.show();
    }
});
 m3.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
      
        Object object=App.loadFXMLPanel("myAccount");
        if(object instanceof Pane){
       Pane pane=(Pane)object;
       mainpane.setCenter(pane);
               }
        else if(object instanceof ScrollPane){
            ScrollPane pane=(ScrollPane)object;
          mainpane.setCenter(pane);  
        }
    }
});
 m4.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        String[] DRIVE_LETTERS = {"D", "E", "F", "G"};
    String INI_FILE_NAME = "restro365key.ini";
    boolean found=false;
       for (String driveLetter : DRIVE_LETTERS) {
            File drive = new File(driveLetter + ":/");
            if (drive.exists() && drive.canRead()) {
                found=true;
                System.out.println("USB drive detected: " + driveLetter);
                File iniFile = new File(drive, INI_FILE_NAME);
                try {
                    FileWriter writer = new FileWriter(iniFile);
                    String admin=AESEncryption.encrypt(App.currentUser.getUserid());
                    String password=AESEncryption.encrypt(App.currentUser.getPassword());
                    writer.write("[restronusbpasskey]\n");
                    writer.write("userid="+admin+"\n");
                    writer.write("passkey="+password+"\n");
                    writer.close();
                    Alert a=new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Passkey Created, USB can be used for Login.");
                    a.setTitle("Security");
                    a.setHeaderText("USB Passkey Creation");
                    a.show();
                    break;
                    //System.out.println("INI file created: " + iniFile.getAbsolutePath());
                } catch (IOException e) {
                    Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Passkey creation failed");
                    a.setTitle("Security");
                    a.setHeaderText("Passkey Error");
                    a.show();
                }
            }
        }
       if(!found){
           Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("No USB Found on any PORT.");
                    a.setTitle("Security");
                    a.setHeaderText("Passkey Error");
                    a.show();
       }
    }
});
        // add menu items to menu
        userNameDisplay.getItems().add(m1);
        userNameDisplay.getItems().add(m2);
        userNameDisplay.getItems().add(m3);
        userNameDisplay.getItems().add(m4);
    }    
    public void addBotton(){
    Button addEmpBtn = new Button();
    addEmpBtn.setStyle("-fx-background-color:#b1c1ec;");
    addEmpBtn.setText("Add New Employee");
    addEmpBtn.setMaxHeight(50);
    addEmpBtn.setMaxWidth(220);
    addEmpBtn.setPrefWidth(160);
    addEmpBtn.setPrefHeight(100);
    addEmpBtn.minWidth(100);
    addEmpBtn.minHeight(30);
    Button auditBtn = new Button();
    auditBtn.setStyle("-fx-background-color:#b1c1ec;");
    auditBtn.setText("Audit");
    auditBtn.setMaxHeight(50);
    auditBtn.setMaxWidth(220);
    auditBtn.setPrefWidth(160);
    auditBtn.setPrefHeight(100);
    auditBtn.minWidth(100);
    auditBtn.minHeight(30);
    vboxButtonsService.getChildren().add(vboxButtonsService.getChildren().size(), addEmpBtn);
    vboxButtonsService.getChildren().add(vboxButtonsService.getChildren().size(), auditBtn);
    }
    
}
