/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.validation;

import com.dnj.fooding.exeptions.UserPasswordFieldException;

/**
 *
 * @author Animesh Samanta
 */
public class PasswordValidation {
    public static void validateInput(String userid,String password) throws UserPasswordFieldException{
        if(userid==null||userid.isBlank()||userid.isEmpty()||userid.contains(" ")){
            throw new UserPasswordFieldException("USER_ID_FIEL_EMPTY");
        }
        if(password==null||password.isBlank()||password.isEmpty()){
            throw new UserPasswordFieldException("PASSWORD_FIEL_EMPTY");
        }
    }
    }
