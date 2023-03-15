/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import static com.dnj.fooding.App.loadFXML;
import static com.dnj.fooding.App.scene;
import static com.dnj.fooding.App.stage;
import com.dnj.fooding.service.ServiceManService;
import com.dnj.fooding.support.AESEncryption;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Animesh Samanta
 */
public class ServiceManController implements Initializable{
    @FXML
    private BorderPane mainpane;
    @FXML
    private MenuButton userNameDisplay;
    @FXML
    private VBox vboxButtonsService;
    public static MenuButton staticUserNameDiaplay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manageAccessButton();
        staticUserNameDiaplay=userNameDisplay;
        Object object=App.loadFXMLPanel("homeService");
        if(object instanceof Pane){
       Pane pane=(Pane)object;
       mainpane.setCenter(pane);
               }
        else if(object instanceof ScrollPane){
            ScrollPane pane=(ScrollPane)object;
          mainpane.setCenter(pane);  
        }
        userNameDisplay.setText(App.currentUser.getName());
        App.isLoggedin=true;
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
        
        //userNameDisplay.set
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void order(){
       
        Object object=App.loadFXMLPanel("order");
        if(object instanceof Pane){
       Pane pane=(Pane)object;
       mainpane.setCenter(pane);
               }
        else if(object instanceof ScrollPane){
            ScrollPane pane=(ScrollPane)object;
          mainpane.setCenter(pane);  
        }
    }
    public void liveRestro(){
        
         Object object=App.loadFXMLPanel("liveRestro");
        if(object instanceof Pane){
       Pane pane=(Pane)object;
       mainpane.setCenter(pane);
               }
        else if(object instanceof ScrollPane){
            ScrollPane pane=(ScrollPane)object;
          mainpane.setCenter(pane);  
        }
    }
    public void manageRestro(){
        
         Object object=App.loadFXMLPanel("mangeRestro");
        if(object instanceof Pane){
       Pane pane=(Pane)object;
       mainpane.setCenter(pane);
               }
        else if(object instanceof ScrollPane){
            ScrollPane pane=(ScrollPane)object;
          mainpane.setCenter(pane);  
        }
    }
    public void home(){
        
         Object object=App.loadFXMLPanel("homeService");
        if(object instanceof Pane){
       Pane pane=(Pane)object;
       mainpane.setCenter(pane);
               }
        else if(object instanceof ScrollPane){
            ScrollPane pane=(ScrollPane)object;
          mainpane.setCenter(pane);  
        }
    }
    public void exitApp(){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you really want to exit ?");
        Optional<ButtonType> result = alert.showAndWait();
        alert.show();
        
	if(!result.isPresent() || result.get() != ButtonType.OK) {
            
		return;
	} else {
            alert.close();
		App.getStage().close();
	}
       return;
    }
    public void manageAccessButton(){
        
        Map<String,String> buttons=ServiceManService.getInstance().getAccessButtons();
        
        vboxButtonsService.setSpacing(5);
        for(Map.Entry<String,String> btnLable : buttons.entrySet()){
        
       Button btn1 = new Button();
       if(buttons.get(btnLable.getKey())!=null){
           btn1.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        Object object=App.loadFXMLPanel(btnLable.getValue());
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
       }
      
       btn1.setText(btnLable.getKey());
    btn1.setMaxHeight(50);
    btn1.setMaxWidth(200);
    btn1.setPrefWidth(150);
    btn1.setPrefHeight(100);
    btn1.minWidth(100);
    btn1.minHeight(30);
    btn1.setStyle("-fx-background-color:#f2c1c1;");
  
    vboxButtonsService.getChildren().add(vboxButtonsService.getChildren().size(), btn1);
        }
    Button exitBtn = new Button();
     exitBtn.setStyle("-fx-background-color:#f58770;");
    exitBtn.setText("Exit App");
    exitBtn.setMaxHeight(50);
    exitBtn.setMaxWidth(200);
    exitBtn.setPrefWidth(150);
    exitBtn.setPrefHeight(100);
    exitBtn.minWidth(100);
    exitBtn.minHeight(30);
    
    Button logOut = new Button();
    logOut.setStyle("-fx-background-color:#b1c1ec;");
    logOut.setText("Log Out");
    logOut.setMaxHeight(50);
    logOut.setMaxWidth(200);
    logOut.setPrefWidth(150);
    logOut.setPrefHeight(100);
    logOut.minWidth(100);
    logOut.minHeight(30);
    logOut.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you really want to log out ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        
	if(!result.isPresent() || result.get() != ButtonType.OK) {
            
		
	} else {
            alert.close();
		App.getStage().close();
                App.stage=new Stage();
                App.currentUser=null;
            try {
                App.scene= new Scene(App.loadFXML("logindialog"), 640, 480);
                App.currentUser=null;
            }
            catch (IOException ex) {
                Logger.getLogger(ServiceManController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
	}
       
    }
});
    vboxButtonsService.getChildren().add(vboxButtonsService.getChildren().size(), exitBtn);
    vboxButtonsService.getChildren().add(vboxButtonsService.getChildren().size(), logOut);
    }
}
