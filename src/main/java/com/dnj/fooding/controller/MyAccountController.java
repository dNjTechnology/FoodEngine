/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import com.dnj.fooding.dao.LoginDao;
import com.dnj.fooding.exeptions.AuthenticationException;
import com.dnj.fooding.service.MyAccountService;
import com.dnj.fooding.support.AESEncryption;
import com.dnj.fooding.support.DataBaseConnection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            LoginDao.getInstance().resetPassword(newPassword.getText());
         Alert a=new Alert(Alert.AlertType.INFORMATION, "Info", ButtonType.OK);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.setContentText("Password changed Successfully.");
        a.setWidth(300);
        a.setHeight(100);
        a.show();
        App.currentUser.setPassword(newPassword.getText());
        newPassword.setText("");
        confirmPassword.setText("");
        if(App.logInBy.equals("USB_KEY")){
            a.close();
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
                    Alert a3=new Alert(Alert.AlertType.INFORMATION);
                    a3.setContentText("Passkey also Created, USB can be used for Login.");
                    a3.setTitle("Security");
                    a3.setHeaderText("USB Passkey Creation");
                    a3.show();
                    break;
                    //System.out.println("INI file created: " + iniFile.getAbsolutePath());
                } catch (IOException e) {
                    Alert a2=new Alert(Alert.AlertType.ERROR);
                    a2.setContentText("Passkey creation failed");
                    a2.setTitle("Security");
                    a2.setHeaderText("Passkey Error");
                    a2.show();
                }
            }
        }
       if(!found){
           Alert a1=new Alert(Alert.AlertType.ERROR);
                    a1.setContentText("No USB Found on any PORT.");
                    a1.setTitle("Security");
                    a1.setHeaderText("Passkey Error");
                    a1.show();
       }
        }
        
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
    catch (AuthenticationException ex) {
        Alert a=new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.setContentText("Wrong Password Entered.");
        a.setWidth(300);
        a.setHeight(100);
        a.show();
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
