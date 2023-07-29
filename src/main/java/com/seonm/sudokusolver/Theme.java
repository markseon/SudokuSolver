/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.seonm.sudokusolver;

/**
 * Represents a theme containing four colours stored as HexiDecimal values.
 * 
 * @author Mark Seon
 */
public class Theme {
    private String colour1;
    private String colour2;
    private String colour3;
    private String colour4;

    public Theme(String colour1, String colour2, String colour3, String colour4) {
        this.colour1 = colour1;
        this.colour2 = colour2;
        this.colour3 = colour3;
        this.colour4 = colour4;
    }

    public String getColour1() {
        return colour1;
    }

    public String getColour2() {
        return colour2;
    }

    public String getColour3() {
        return colour3;
    }

    public String getColour4() {
        return colour4;
    }

    public void setColour1(String colour1) {
        this.colour1 = colour1;
    }

    public void setColour2(String colour2) {
        this.colour2 = colour2;
    }

    public void setColour3(String colour3) {
        this.colour3 = colour3;
    }

    public void setColour4(String colour4) {
        this.colour4 = colour4;
    }
    
    public String getCSS() {
        return String.format("colour1: %s; colour2: %s; colour3: %s; colour4: %s;"
                            , colour1, colour2, colour3, colour4);
    }
}
