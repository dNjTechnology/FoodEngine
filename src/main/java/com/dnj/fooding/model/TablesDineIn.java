/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tables")
public class TablesDineIn {
   @Id
    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "seating_capacity")
    private int seatingCapacity;

    @Column(name = "is_available")
    private boolean isAvailable;
    

    public TablesDineIn(int tableNumber, int seatingCapacity, boolean isAvailable) {
        this.tableNumber = tableNumber;
        this.seatingCapacity = seatingCapacity;
        this.isAvailable = isAvailable;
    }
     public TablesDineIn() {
        // default constructor
    }

    // getters and setters
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
