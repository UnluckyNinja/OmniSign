/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.UnluckyNinja.signplus;

/**
 * Types of the omnisigns.
 */
public enum SignType {
    // General
    NORMAL("Normal"),
    // Admin:
    COMMAND("Command"),
    // Cheat:
    TIME("Time"),
    WEATHER("Weather"),
    GIVE("Give"),
    // Inventory:
    CONTAINER("Container"),
    // Redstone:
    POWER("Power"),
    NOTE("Note"),
    // Fun:
    QUIZ("Quiz"),
    // Message:
    INFORMATION("Information"),
    // Security:
    PROTECT("Protect"),
    // Teleport:
    GOTO("GoTo"),
    // Other signs:
    OTHER("Other");
    
    private String name;
    private SignType(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
    
    public static SignType getType(String string) {
        switch (string.toLowerCase()) {
            case "normal":
                return NORMAL;
            case "command":
                return COMMAND;
            case "time":
                return TIME;
            case "weather":
                return WEATHER;
            case "give":
                return GIVE;
            case "container":
                return CONTAINER;
            case "power":
                return POWER;
            case "note":
                return NOTE;
            case "quiz":
                return QUIZ;
            case "information":
                return INFORMATION;
            case "protect":
                return PROTECT;
            case "goto":
                return GOTO;
            default:
                return OTHER;
        }
    }
}
