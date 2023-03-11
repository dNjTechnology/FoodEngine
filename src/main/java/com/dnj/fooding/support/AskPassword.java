/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

import com.dnj.fooding.App;
import com.dnj.fooding.exeptions.AuthenticationException;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Animesh Samanta
 */
public class AskPassword {
    public static void askPassword() throws AuthenticationException{
        TextInputDialog passwordNow=new TextInputDialog();
        passwordNow.setContentText("Enter your password");
        passwordNow.setHeaderText("Password Check");
        passwordNow.setTitle("Security");
            passwordNow.getEditor().setPromptText("Password");
        passwordNow.getDialogPane().setMinWidth(300);
        passwordNow.showAndWait();
        passwordNow.getEditor().getText();
        if(!(passwordNow.getEditor().getText().equals(App.currentUser.getPassword()))){
            throw new AuthenticationException();
        }
    }
}
