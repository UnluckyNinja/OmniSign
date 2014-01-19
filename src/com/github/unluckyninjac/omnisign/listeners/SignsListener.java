package com.github.unluckyninjac.omnisign.listeners;

import com.github.unluckyninjac.omnisign.OmniSign;
import com.github.unluckyninjac.omnisign.SignsManager;
import com.github.unluckyninjac.omnisign.events.SignBreakEvent;
import com.github.unluckyninjac.omnisign.events.SignClickEvent;
import com.github.unluckyninjac.omnisign.events.SignPlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignsListener implements Listener {
    private OmniSign SP;
    private SignsManager SM;
    
    public SignsListener(OmniSign SP){
        this.SP = SP;
        SM = SP.getSignsManager();
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void callSignPlaceEvent(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getState() instanceof Sign) {
            SignPlaceEvent signevent = new SignPlaceEvent(event);
            Bukkit.getServer().getPluginManager().callEvent(signevent);
            if (signevent.update() && signevent.getBlock().getState() instanceof Sign) {
                signevent.getSign().update();
            }
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void callSignClickEvent(PlayerInteractEvent event) {
        if (event.hasBlock()) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                SignClickEvent signevent = new SignClickEvent(event);
                Bukkit.getServer().getPluginManager().callEvent(signevent);
                if (signevent.update() && signevent.getClickedBlock().getState() instanceof Sign) {
                    signevent.getSign().update();
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void callSignBreakEvent(BlockBreakEvent event) {
        if (event.getBlock().getState() instanceof Sign) {
            SignBreakEvent signevent = new SignBreakEvent(event);
            Bukkit.getServer().getPluginManager().callEvent(signevent);
            if (signevent.update() && signevent.getBlock().getState() instanceof Sign) {
                signevent.getSign().update();
            }
        }
    }
}
