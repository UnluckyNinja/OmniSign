/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.unluckyninja.omnisign.listener;

import com.github.unluckyninja.omnisign.OmniSignMain;
import com.github.unluckyninja.omnisign.SignType;
import com.github.unluckyninja.omnisign.SignsManager;
import com.github.unluckyninja.omnisign.event.SignClickEvent;
import com.github.unluckyninja.omnisign.event.SignCreateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

/**
 * @deprecated Now for debug
 * @author Administrator
 */
public class TimeSignListener implements Listener {
    private OmniSignMain SP;
    private SignsManager SM;
    
    public TimeSignListener(OmniSignMain SP){
        this.SP = SP;
        SM = SP.getSignsManager();
    }
    
    @EventHandler
    public void onTimeSignClicked(SignClickEvent event){
        if(event.getSignType() == SignType.TIME){
            event.getPlayer().sendMessage("time sign listener called."+event.getSignType().toString());
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
                event.getPlayer().sendMessage("Your right click the sign.");
            } else if(event.getAction() == Action.LEFT_CLICK_BLOCK){
                event.getPlayer().sendMessage("Your left click the sign.");
                event.getPlayer().sendMessage("size: "+event.getPlayer().getInventory().getSize());
                int i =0;
                for(ItemStack item:event.getPlayer().getInventory()){
                    i++;
                }
                event.getPlayer().sendMessage("items: "+i);

            }
        }
    }

    @EventHandler
    public void onTimeSignCreated(SignCreateEvent event){
        if(event.getSignType() == SignType.TIME){
            event.getPlayer().sendMessage("time sign listener called.");
            event.getPlayer().sendMessage("time sign has been created");
        }
    }
}
