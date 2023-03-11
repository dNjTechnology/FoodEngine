/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

import java.util.Arrays;

import java.util.List;

/**
 *
 * @author Animesh Samanta
 */
public class User {
private String name;
   private String userid;
   private String password;
   private String designation;
   private List<String> access;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<String> getAccess() {
        return access;
    }

    public void setAccess(String access) {
        String arr[]=access.split(",");
        this.access = Arrays.asList(arr);
    }
   
}
