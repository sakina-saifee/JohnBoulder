/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.Order.Working;

import java.util.HashMap;

/**
 *
 * @author hitma
 */
public class Item {

    public final String name;
    public HashMap<String, Double> quantity;
    
    //this hashmap = constructor hashmap

    public Item(String name, HashMap<String, Double> quantity) {
        this.name = name;
        try {
            ItemException.checkQuantity(quantity);
            this.quantity = quantity;
        } catch (ItemException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
