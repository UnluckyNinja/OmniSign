/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.unluckyninja.omnisign.sign;

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
        if(string.equalsIgnoreCase("command")){
            return COMMAND;
        }else if(string.equalsIgnoreCase("time")){
            return TIME;
        }else if(string.equalsIgnoreCase("weather")){
            return WEATHER;
        }else if(string.equalsIgnoreCase("give")){
            return GIVE;
        }else if(string.equalsIgnoreCase("container")){
            return CONTAINER;
        }else if(string.equalsIgnoreCase("power")){
            return POWER;
        }else if(string.equalsIgnoreCase("note")){
            return NOTE;
        }else if(string.equalsIgnoreCase("quiz")){
            return QUIZ;
        }else if(string.equalsIgnoreCase("information")){
            return INFORMATION;
        }else if(string.equalsIgnoreCase("protect")){
            return PROTECT;
        }else if(string.equalsIgnoreCase("goto")){
            return GOTO;
        }else if(string.equalsIgnoreCase("normal")){
            return NORMAL;
        }else{
            return OTHER;
        }
    }
}
