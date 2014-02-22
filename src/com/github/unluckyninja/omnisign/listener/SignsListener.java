package com.github.unluckyninja.omnisign.listener;

import com.github.unluckyninja.omnisign.OmniSignMain;
import com.github.unluckyninja.omnisign.SignsManager;
import com.github.unluckyninja.omnisign.event.SignBreakEvent;
import com.github.unluckyninja.omnisign.event.SignClickEvent;
import com.github.unluckyninja.omnisign.event.SignCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignsListener implements Listener {
    private OmniSignMain SP;
    private SignsManager SM;

    public SignsListener(OmniSignMain SP) {
        this.SP = SP;
        SM = SP.getSignsManager();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void callSignCreateEvent(SignChangeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        SignCreateEvent signevent = new SignCreateEvent(event);
        Bukkit.getServer().getPluginManager().callEvent(signevent);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void callSignClickEvent(PlayerInteractEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.hasBlock()) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                SignClickEvent signevent = new SignClickEvent(event);
                Bukkit.getServer().getPluginManager().callEvent(signevent);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void callSignBreakEvent(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getBlock().getState() instanceof Sign) {
            SignBreakEvent signevent = new SignBreakEvent(event);
            Bukkit.getServer().getPluginManager().callEvent(signevent);
        }
    }
}
