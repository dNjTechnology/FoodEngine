/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.controller;

import com.dnj.fooding.App;
import com.dnj.fooding.support.DataBaseConnection;
import static com.dnj.fooding.App.currentUser;
import static com.dnj.fooding.App.scene;
import static com.dnj.fooding.App.stage;
import com.dnj.fooding.exeptions.AuthenticationException;
import com.dnj.fooding.exeptions.UserNotFoundException;
import com.dnj.fooding.exeptions.UserPasswordFieldException;
import com.dnj.fooding.model.User;
import com.dnj.fooding.service.LoginService;
import com.dnj.fooding.support.UsbDetection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Animesh Samanta
 */
public class LogindialogController implements Initializable {

    public static void logOutUsbMode() {
        App.getStage().close();
                App.stage=new Stage();
                App.currentUser=null;
            try {
                App.scene= new Scene(App.loadFXML("logindialog"), 640, 480);
                App.currentUser=null;
                stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
            }catch(Exception e){
                e.printStackTrace();
                 App.running=false;
        Platform.exit();
            }
        }

    
    private Boolean auth=false;
    @FXML
    private TextField useridforlogin;
    public static TextField useridforloginstatic;
    @FXML
    private PasswordField passwordforlogin;
    public static PasswordField passwordforloginstatic;
    @FXML
    private Label messagelogin;
    private static Label messageloginstatic;
    public static Boolean usbLogin=false;
    @FXML
    private void login() throws IOException {
        
        authentication();
        System.out.println("Login button clicked"+useridforlogin.getText()+"and"+passwordforlogin.getText());
    }
    @FXML
    private void cancel() throws IOException {
        App.running=false;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
useridforloginstatic=useridforlogin;
passwordforloginstatic=passwordforlogin;
messageloginstatic=messagelogin;
    }
    public static void usbLogin(){
        
      
        LoginService loginService=LoginService.getInstance();
        try {
            loginService.authenticationService(useridforloginstatic.getText(), passwordforloginstatic.getText());
            App.getStage().close();
            
            App.logInBy="USB_KEY";
            App.setNewStage("serviceman");
            Logger.getLogger(LogindialogController.class.getName()).log(Level.INFO,"Authentication Successfull");
        
        }
        catch (SQLException ex) {
            Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Broken!!...Please contact super admin");
                    a.setTitle("Security");
                    a.setHeaderText("Error");
                    a.show();
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE,  ex.getMessage(), ex);
        }
        catch (UserNotFoundException ex) {
           Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Passkey is not valid");
                    a.setTitle("Security");
                    a.setHeaderText("Passkey Error");
                    a.show();
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE,  ex.getMessage(), ex);
        }
        catch (AuthenticationException ex) {
             Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Passkey is not valid");
                    a.setTitle("Security");
                    a.setHeaderText("Passkey Error");
                    a.show();
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE,  ex.getMessage(), ex);
        }
        catch (UserPasswordFieldException ex) {
            if(ex.getMessage().equals("USER_ID_FIEL_EMPTY")){
                Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Passkey is not valid");
                    a.setTitle("Security");
                    a.setHeaderText("Passkey Error");
                    a.show();
            }
            else if(ex.getMessage().equals("PASSWORD_FIEL_EMPTY")){
                Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Passkey is not valid");
                    a.setTitle("Security");
                    a.setHeaderText("Passkey Error");
                    a.show();
            }
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        catch(Exception ex){
            Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Broken!!...Please contact super admin");
                    a.setTitle("Security");
                    a.setHeaderText("Error");
                    a.show();
            Logger.getLogger(LogindialogController.class.getName()).log(Level.SEVERE,  ex.getMessage(), ex);
        }
    }
}
