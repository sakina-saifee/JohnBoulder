/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.restaurant;

import java.util.regex.Pattern;

/**
 *
 * @author hitma
 */
public interface FormatsAndMethods {
    public enum Status {
        BOOKED,
        RESERVED,
        EMPTY
    }
    Pattern PATTERN = Pattern.compile("^(\\d\\d:\\d\\d)");
    
    public void emptyOrNullString(String... args) throws ValidationException;
    public void timeFormat(String time) throws ValidationException;
}
