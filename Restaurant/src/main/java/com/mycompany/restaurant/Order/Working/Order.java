/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant.Order.Working;

import java.util.ArrayList;

/**
 *
 * @author hitma
 */
public class Order {

    private String table_no;
    private ArrayList<Item> order_item = new ArrayList<>();
    private double totalBill = 0;

//    public Order() {
//
//    }

    public Order(String table_no) {
        this.table_no = table_no;
    }

//    public Order(String table_no, Item order_item) {
//        this.table_no = table_no;
//        this.order_item.add(order_item);
//    }
//
//    public Order(String table_no, ArrayList<Item> order_item) {
//        this.table_no = table_no;
//        this.order_item.addAll(order_item);
//    }
//
//    public void assignTable(String table_no) {
//        this.table_no = table_no;
//    }

    public void addItem(Item item) {
        this.order_item.add(item);
    }

//    public void addItem(ArrayList<Item> order_item) {
//        this.order_item.addAll(order_item);
//    }
    
    public boolean removeItem(Item order_item) {
        int i = 0;
        for (Item item : this.order_item) {
            if (item.name.equals(order_item.name)) {
                for (String x : order_item.quantity.keySet()) {
                    if (item.quantity.get(x) != null) {
                        this.order_item.remove(i);
                        
                        return true;
                    }
                }
            }
            i++;
        }
        
        return false;
    }

    public double getTotal() {
        
        for (Item x : order_item) {
            for (Double y : x.quantity.values()) {
                this.totalBill += y;
            }
        }

        return this.totalBill;
    }
    
    public ArrayList<Item> getItems() {
        return this.order_item;
    }
//    
//    public String printOrderDetails() {
//        String details = "";
//        for (Item i : order_item) {
//            for (String key : i.quantity.keySet()) {
//                details += i.name + " (" + key + ")";
//            }
//        }
//        
//        return details;
//    }
}
