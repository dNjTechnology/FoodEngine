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
        Pane pane=App.loadFXMLPanel("homeService");
        mainpane.setCenter(pane);
        userNameDisplay.setText(App.currentUser.getName());
        // userNameDisplay= new MenuButton("menuButton");
 
        // create menuitems
        MenuItem m1 = new MenuItem("Role:-"+App.currentUser.getDesignation());
        MenuItem m2 = new MenuItem("My Access");
        MenuItem m3 = new MenuItem("My Account");
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
       Pane pane=App.loadFXMLPanel("myAccount");
        mainpane.setCenter(pane);
    }
});
        // add menu items to menu
        userNameDisplay.getItems().add(m1);
        userNameDisplay.getItems().add(m2);
        userNameDisplay.getItems().add(m3);
        
        //userNameDisplay.set
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void order(){
        Pane pane=App.loadFXMLPanel("order");
        mainpane.setCenter(pane);
    }
    public void liveRestro(){
        Pane pane=App.loadFXMLPanel("liveRestro");
        mainpane.setCenter(pane);
    }
    public void manageRestro(){
        Pane pane=App.loadFXMLPanel("mangeRestro");
        mainpane.setCenter(pane);
    }
    public void home(){
        Pane pane=App.loadFXMLPanel("homeService");
        mainpane.setCenter(pane);
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
       Pane pane=App.loadFXMLPanel(btnLable.getValue());
        mainpane.setCenter(pane);
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
        alert.show();
        
	if(!result.isPresent() || result.get() != ButtonType.OK) {
            
		
	} else {
            alert.close();
		App.getStage().close();
                App.stage=new Stage();
                App.currentUser=null;
            try {
                App.scene= new Scene(App.loadFXML("logindialog"), 640, 480);
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
