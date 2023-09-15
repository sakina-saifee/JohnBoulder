/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.Order.Working;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author hitma
 */
public class ItemList {
    private static HashMap<String, Double> quantity = new HashMap();
    public ArrayList<Item> items = new ArrayList<>();
    
    //HashMap  | HashTable
    
    public ItemList() {
        quantity = new HashMap();
        quantity.put("Small", 240.00);
        quantity.put("Regular", 450.00);
        quantity.put("Large", 890.00);
        Item Pizza = new Item("Pizza", quantity);
        //Item of Pizza, quantity (small ,reg, large)
        
        quantity = new HashMap();
        quantity.put("Chicken", 200.00);
        quantity.put("Beef", 250.00);
        quantity.put("Zinger", 350.00);
        Item Burger = new Item("Burger", quantity);
        //item of burger, quantity (chicken, beef, zinger)
        
        quantity = new HashMap();
        quantity.put("Single", 120.00);
        quantity.put("Double", 200.00);
        Item Chinese = new Item("Chinese", quantity);
        
        quantity = new HashMap();
        quantity.put("Quarter", 170.00);
        quantity.put("Half", 250.00);
        quantity.put("Full", 400.00);
        Item Lasagna = new Item("Lasagna", quantity);
        
        this.items.add(Pizza);
        this.items.add(Burger);
        this.items.add(Chinese);
        this.items.add(Lasagna);
    }
}
