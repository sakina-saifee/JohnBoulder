/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

import com.mycompany.restaurant.Order.Working.Order;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.regex.Matcher;

/**
 *
 * @author hitma
 */
public class Working extends Data implements FormatsAndMethods {

    public static String selectedTable;
    //                    table   value
    public static HashMap<String, Order> order = new HashMap<>();

    public Working() {
        super();
    }

    public static void addTables() {
        //for (string x : tables) {
        for (String x : tables) {
            booking_tables.put(x, FormatsAndMethods.Status.EMPTY);
        }
    }
    //if this is the key bring the value
    @Override
    public boolean getIdPassword(String id, String password) {
        boolean isVerified = false;
        for (int key : users.keySet()) {
            if (key == Integer.parseInt(id)) {
                if (users.get(Integer.parseInt(id)).equals(password)) {
                    isVerified = true;
                    break;
                }
            }
        }

        return isVerified;
    }

    //return arr tables
    public String[] getTableNo() {
        return tables;
    }

    @Override
    public void bookTable() {
        booking_tables.replace(selectedTable, FormatsAndMethods.Status.BOOKED);
        if (reserved_timing.get(selectedTable) != null) {
            reserved_timing.remove(selectedTable);
        }
    }
    
    
    //If Table is booked. It should not be reserve.
    //If the table was reserved

    @Override
    public void bookTable(String time) {
        booking_tables.replace(selectedTable, FormatsAndMethods.Status.RESERVED);
        reserved_timing.put(selectedTable, time);
    }

    @Override
    public HashMap getTables() {
        return booking_tables;
    }

    @Override
    public String checkTableStatus() {
        String strStatus;
        var status = booking_tables.get(selectedTable);
        if (status == null) {
            strStatus = "Empty";
        } else {
            switch (status) {
                case BOOKED:
                    strStatus = "Booked";
                    break;
                case RESERVED:
                    LocalTime t = LocalTime.parse(reserved_timing.get(selectedTable)); //TIme of table 13:49
                    int diff = t.getHour() - LocalTime.now().getHour(); //13 - 18 = -5 (if value is less than 0, time is past, if greater time is future, equal is 0 present
                    //e.g reserve time was 21:
                    if (diff <= 0) {
                        strStatus = "Booked";
                        bookTable();
                    } else {
                        strStatus = t.toString();
                    }
                    break;
                default:
                    strStatus = "Empty";
                    break;
            }
        }

        return strStatus;
    }

    @Override
    public void emptyOrNullString(String... args) throws ValidationException {
        for (String arg : args) {
            if (arg == null || arg.isEmpty()) {
                throw new ValidationException("Please enter something.");
            }
        }
    }

    @Override
    public void timeFormat(String time) throws ValidationException {
        Matcher matcher = FormatsAndMethods.PATTERN.matcher(time);
        if (!matcher.find()) {
            throw new ValidationException("Incorrect time format. Enter in 24-hour clock HH:MM.");
        } else {
            int h = Integer.parseInt(Character.toString(time.charAt(0)) + Character.toString(time.charAt(1)));
            int m = Integer.parseInt(Character.toString(time.charAt(3)) + Character.toString(time.charAt(4)));
            
            
            if (h > 23 || h < 0) {
                throw new ValidationException("Hours should be between 00 and 23");
            }

            if (m > 59 || m < 0) {
                throw new ValidationException("Minutes should be between 00 and 59");
            }
            
            
            if (LocalTime.now().compareTo(LocalTime.parse(time)) > 0) {
                throw new ValidationException("Invalid time. Enter only upcoming time. Remember the time is in 24-hour Clock.");
            }
        }
    }
    
    public static void orderComplete() {
        booking_tables.replace(selectedTable, FormatsAndMethods.Status.EMPTY);
        order.remove(selectedTable);
    }
}
