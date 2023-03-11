/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import com.dnj.fooding.exeptions.AuthenticationException;
import com.dnj.fooding.service.MyAccountService;
import com.dnj.fooding.support.DataBaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Animesh Samanta
 */
public class MyAccountController implements Initializable {
@FXML
private ListView accessList;
@FXML
private TextField mynameEdit;
@FXML
private TextField newPassword;
@FXML
private TextField confirmPassword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mynameEdit.setText(App.currentUser.getName());
        for(String acc:App.currentUser.getAccess()){
            accessList.getItems().add(acc);
        }
    }  
    public void resetPassword(){
    try {
        if(newPassword.getText().equals(confirmPassword.getText())){
        Connection connection=DataBaseConnection.CONNECT();
       
        PreparedStatement stmt=connection.prepareStatement("UPDATE user SET password=? WHERE userid=?");
        stmt.setString(1,newPassword.getText());
        stmt.setString(2,App.currentUser.getUserid());
        stmt.executeUpdate();
         Alert a=new Alert(Alert.AlertType.INFORMATION, "Info", ButtonType.OK);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.setContentText("Password changed Successfully.");
        a.setWidth(300);
        a.setHeight(100);
        a.show();
        App.currentUser.setPassword(newPassword.getText());
        }
        else{
            Alert a=new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.setContentText("Enter the same password as confirm password.");
        a.setWidth(300);
        a.setHeight(100);
        a.show();
        }
        
    }
    catch (SQLException ex) {
        Logger.getLogger(MyAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
@SuppressWarnings("static-access")
    public void updateName(){
    try {
        MyAccountService.getInstance().changeName(mynameEdit.getText());
        ServiceManController.staticUserNameDiaplay.setText(App.currentUser.getName());
    }
    catch(AuthenticationException ex){
        Alert a=new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.setContentText("Entered password is wrong.");
        a.setWidth(300);
        a.setHeight(100);
        a.show();
        Logger.getLogger(MyAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (SQLException ex) {
        Alert a=new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.setContentText("Broken!!!...Please contact super admin.");
        a.setWidth(300);
        a.setHeight(100);
        a.show();
        Logger.getLogger(MyAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (Exception ex) {
        if(ex.getMessage().equals("UPTO_DATE_DATA")){
             Alert a=new Alert(Alert.AlertType.INFORMATION, "Information", ButtonType.OK);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.setContentText("Data is upto date nothing to update.");
        a.setWidth(300);
        a.setHeight(100);
        a.show();
        }
        Logger.getLogger(MyAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
