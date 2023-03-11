/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

/**
 *
 * @author Animesh Samanta
 */
public class MenuDish {
private Integer id=null;
    private String name=null;
    private Double price=null;
    private String available=null;
    private String category=null;
    private Integer unitsSoldTotal=null;
    private Integer unitSoldToday=null;

    public void MenuDish(){
        
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getUnitsSoldTotal() {
        return unitsSoldTotal;
    }

    public void setUnitsSoldTotal(Integer unitsSoldTotal) {
        this.unitsSoldTotal = unitsSoldTotal;
    }

    public Integer getUnitSoldToday() {
        return unitSoldToday;
    }

    public void setUnitSoldToday(Integer unitSoldToday) {
        this.unitSoldToday = unitSoldToday;
    }
    
    
}
