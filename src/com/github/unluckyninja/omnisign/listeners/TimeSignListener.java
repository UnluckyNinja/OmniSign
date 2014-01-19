/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.unluckyninja.omnisign.listeners;

import com.github.unluckyninja.omnisign.OmniSign;
import com.github.unluckyninja.omnisign.SignType;
import com.github.unluckyninja.omnisign.SignsManager;
import com.github.unluckyninja.omnisign.events.SignClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

/**
 * @deprecated Now for debug
 * @author Administrator
 */
public class TimeSignListener implements Listener {
    private OmniSign SP;
    private SignsManager SM;
    
    public TimeSignListener(OmniSign SP){
        this.SP = SP;
        SM = SP.getSignsManager();
    }
    
    @EventHandler
    public void onTimeSignClicked(SignClickEvent event){
        event.getPlayer().sendMessage("time sign listener called."+event.getSignType().toString());
        if(event.getSignType() == SignType.TIME){
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
                event.getPlayer().sendMessage("Your right click the sign.");
                SM.setMetadata(event.getSign(), "SignOwner", event.getPlayer().getName());
            } else if(event.getAction() == Action.LEFT_CLICK_BLOCK){
                event.getPlayer().sendMessage("Your left click the sign.");
                event.getPlayer().sendMessage(SM.getMetadata(event.getSign(), "SignOwner").toString());
            }
        }
    }
}
