/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

import java.util.Arrays;

import java.util.List;
import java.util.Set;
import javax.persistence.*;




/**
 *
 * @author Animesh Samanta
 */
@Entity
@Table(name ="user")
public class User {
   @Column(name = "name") 
private String name;
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
private String id;

    
@Column(name = "userid")
   private String userid;
@Column(name = "password")
   private String password;
@Column(name = "designation")
   private String designation;
@Column(name = "Network")
   private String access;
@Column(name = "isLocked")
private Integer isLocked;
   //public Boolean isLoggedIn;
 public User() {
        // default constructor
    }
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
        String arr[]=this.access.split(",");
        
        return Arrays.asList(arr);
    }

    public void setAccess(String access) {
        this.access=access;
    }
      public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }
    @Override
    public String toString() {
        return "User{" + "name=" + name + ", id=" + id + ", userid=" + userid + ", password=" + password + ", designation=" + designation + ", access=" + access + ", isLocked=" + isLocked + '}';
    }
   
}
