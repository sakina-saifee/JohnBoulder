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
public class ItemException extends Exception {
    
    public ItemException() {
        super("Invalid item information.");
    }

    public ItemException(String msg) {
        super(msg);
    }
    
    /// hashmap <keys, values>
    
    public static void checkQuantity(HashMap<String, Double> quantity) throws ItemException {
        /*
        keyset() brings index of hashmap in loop
        values() brings value of hashmap in loop
        */
        for (double d : quantity.values()) {
            if (d <= 0) {
                throw new ItemException("Cost cannot be less than and equal to 0.");
            }
        }
        
        /*
        
        for (string d : quantity.keySet()) {
            if (quantity.get(d) <= 0) {
                throw exception
            }
        }
        
        */
    }
}
