/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

/**
 *
 * @author Animesh Samanta
 */
import javax.persistence.*;

@Entity
@Table(name="menu")
public class Menu {
    
    @Id
    @Column(name="menu_id")
    private int menuId;
    
    @Column(name="item_name")
    private String itemName;
    
    @Column(name="category")
    private String category;
    
    @Column(name="item_price")
    private double itemPrice;
    
    @Column(name="description")
    private String description;
    
    // Constructors, getters and setters
    
   
    
    public int getMenuId() {
        return menuId;
    }
    
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public double getItemPrice() {
        return itemPrice;
    }
    
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
     public Menu() {
        // default constructor
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.menuId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        return this.menuId == other.menuId;
    }
     
}

