/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;

import com.dnj.fooding.App;
import com.dnj.fooding.dao.LoginDao;
import com.dnj.fooding.exeptions.AuthenticationException;
import com.dnj.fooding.exeptions.UserNotFoundException;
import com.dnj.fooding.exeptions.UserPasswordFieldException;
import com.dnj.fooding.model.User;
import com.dnj.fooding.validation.PasswordValidation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Animesh Samanta
 */

public class LoginService {
    private static LoginService loginSeviceInstance=null;
    private void LoginService(){
        
    }
    public static LoginService getInstance(){
        if(loginSeviceInstance==null){
            loginSeviceInstance=new LoginService();
            return new LoginService();
        }
        return loginSeviceInstance;
    }
    public void authenticationService(String userid,String password) throws SQLException, UserNotFoundException, AuthenticationException, UserPasswordFieldException{
        try {
            PasswordValidation.validateInput(userid, password);
            LoginDao logindao=LoginDao.getInstance();
            User user=logindao.getUserById(userid);
            if(user.getPassword().equals(password)){
                App.currentUser=user;
            }
            else{
                throw new AuthenticationException();
            }
            
        }
        catch (UserPasswordFieldException ex) {
            throw ex;
        }
        catch(Exception e){
            throw e;
        }
    }
    public void rememberMe(){
        
    }
}
