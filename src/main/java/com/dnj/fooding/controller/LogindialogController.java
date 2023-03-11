/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import com.dnj.fooding.support.DataBaseConnection;
import static com.dnj.fooding.App.currentUser;
import com.dnj.fooding.exeptions.AuthenticationException;
import com.dnj.fooding.exeptions.UserNotFoundException;
import com.dnj.fooding.exeptions.UserPasswordFieldException;
import com.dnj.fooding.model.User;
import com.dnj.fooding.service.LoginService;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Animesh Samanta
 */
public class LogindialogController {
    private Boolean auth=false;
    @FXML
    private TextField useridforlogin;
    @FXML
    private PasswordField passwordforlogin;
    @FXML
    private Label messagelogin;
    @FXML
    private void login() throws IOException {
        
        authentication();
        System.out.println("Login button clicked"+useridforlogin.getText()+"and"+passwordforlogin.getText());
    }
    @FXML
    private void cancel() throws IOException {
        Platform.exit();
    }
    
    private void authentication(){
        messagelogin.setText("");
        LoginService loginService=LoginService.getInstance();
        try {
            loginService.authenticationService(useridforlogin.getText(), passwordforlogin.getText());
            App.setNewStage("serviceman");
            Logger.getLogger(LogindialogController.class.getName()).log(Level.INFO,"Authentication Successfull");
        
        }
        catch (SQLException ex) {
            messagelogin.setText("Broken!!!..Please Connect To Super Admin");
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE,  ex.getMessage(), ex);
        }
        catch (UserNotFoundException ex) {
            messagelogin.setText("No user exist with '"+useridforlogin.getText()+"'.");
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE,  ex.getMessage(), ex);
        }
        catch (AuthenticationException ex) {
             messagelogin.setText("Wrong UserId/Password Provided.");
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE,  ex.getMessage(), ex);
        }
        catch (UserPasswordFieldException ex) {
            if(ex.getMessage().equals("USER_ID_FIEL_EMPTY")){
                messagelogin.setText("Provide userid");
            }
            else if(ex.getMessage().equals("PASSWORD_FIEL_EMPTY")){
                messagelogin.setText("Provide valid password");
            }
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        catch(Exception ex){
            messagelogin.setText("Broken!!!...Please contact super admin.");
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE,  ex.getMessage(), ex);
        
        }
    }
}
