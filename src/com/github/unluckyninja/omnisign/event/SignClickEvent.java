package com.github.unluckyninja.omnisign.event;

import com.github.unluckyninja.omnisign.sign.OmniSignState;
import com.github.unluckyninja.omnisign.sign.SignType;
import org.bukkit.block.Sign;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Administrator
 */
public class SignClickEvent extends PlayerInteractEvent implements SignEvent{
    private static final HandlerList handlers = new HandlerList();
    private OmniSignState omniSignState;
    private Sign sign;
    
    public SignClickEvent(PlayerInteractEvent event){
        super(event.getPlayer(),event.getAction(),event.getItem(),event.getClickedBlock(),event.getBlockFace());
        sign = (Sign)this.blockClicked.getState();
        omniSignState = new OmniSignState(sign);
    }

    @Override
    public Sign getSign() {
        return sign;
    }

    @Override
    public OmniSignState getOmniSignState() {
        return omniSignState;
    }

    @Override
    public SignType getSignType() {
        return omniSignState.getOmniSign().getType();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
