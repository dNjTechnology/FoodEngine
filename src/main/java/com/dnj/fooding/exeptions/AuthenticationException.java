/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.exeptions;

/**
 *
 * @author Animesh Samanta
 */
public class AuthenticationException extends Exception {
    public AuthenticationException(){
        super("USERID_PASSWORD_INCORRECT");
    }
}
