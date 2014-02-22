/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.unluckyninja.omnisign.listener;

import com.github.unluckyninja.omnisign.OmniSignMain;
import com.github.unluckyninja.omnisign.SignsManager;
import com.github.unluckyninja.omnisign.event.SignClickEvent;
import com.github.unluckyninja.omnisign.event.SignCreateEvent;
import com.github.unluckyninja.omnisign.sign.SignType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

/**
 * @author UnluckyNinja
 */
public class TimeSignListener implements Listener {
    private OmniSignMain OS;
    private SignsManager SM;
    
    public TimeSignListener(OmniSignMain OS){
        this.OS = OS;
        SM = OS.getSignsManager();
    }
    
    @EventHandler
    public void onTimeSignClicked(SignClickEvent event){
        event.getPlayer().sendMessage("ClickEvent");
        event.getPlayer().sendMessage(event.getSignType().toString());
        if(event.getSignType() == SignType.TIME){
            event.getPlayer().sendMessage("TimeClickEvent");
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
                event.getPlayer().sendMessage("RightClickEvent");
                event.getOmniSign().act();
            } else if(event.getAction() == Action.LEFT_CLICK_BLOCK){
                event.getPlayer().sendMessage("LeftClickEvent");
                event.getOmniSign().toggle();
            }
        }
    }

    @EventHandler
    public void onTimeSignCreated(SignCreateEvent event){
        if(event.getSignType() == SignType.TIME){
            event.getPlayer().sendMessage("Time sign has been created.");
        }
    }
}
