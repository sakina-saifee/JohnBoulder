/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

import java.util.HashMap;

/**
 *
 * @author hitma
 */
public abstract class Data {
    protected static final HashMap<Integer, String> users = new HashMap<>();
    protected static final String[] tables = {"Table No. 1", "Table No. 2", "Table No. 3", "Table No. 4", "Table No. 5", "Table No. 6", "Table No. 7", "Table No. 8", "Table No. 9", "Table No. 10", "Table No. 11", "Table No. 12", "Table No. 13", "Table No. 14", "Table No. 15", "Table No. 16", "Table No. 17", "Table No. 18", "Table No. 19", "Table No. 20", "Table No. 21"};
    protected static final HashMap<String, FormatsAndMethods.Status> booking_tables = new HashMap<>();
    protected static final HashMap<String, String> reserved_timing = new HashMap<>();
    protected Data() {
        users.put(4001, "1234");
        users.put(4002, "4567");
        users.put(4003, "0010");
        users.put(4004, "employee123");
        users.put(4005, "6781hello");
        users.put(4006, "umbrella");
        users.put(4007, "777");
        users.put(1, "2");
    }
    
    public abstract boolean getIdPassword(String id, String password);
    
    public abstract void bookTable(); //book
    
    public abstract void bookTable(String time); //reserver
    
    public abstract HashMap getTables(); //return tables with status
    
    public abstract String checkTableStatus();
}
