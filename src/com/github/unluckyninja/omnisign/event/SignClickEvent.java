package com.github.unluckyninja.omnisign.event;

import com.github.unluckyninja.omnisign.SignType;
import com.github.unluckyninja.omnisign.sign.OmniSign;
import org.bukkit.block.Sign;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Administrator
 */
public class SignClickEvent extends PlayerInteractEvent implements SignEvent{
    private static final HandlerList handlers = new HandlerList();
    private Sign sign;
    private boolean update;
    
    public SignClickEvent(PlayerInteractEvent event){
        super(event.getPlayer(),event.getAction(),event.getItem(),event.getClickedBlock(),event.getBlockFace());
        sign = (Sign)this.blockClicked.getState();
        update = false;
    }

    @Override
    public OmniSign getOmniSign() {
        return null;
    }

    @Override
    public SignType getSignType() {
        String line1 = sign.getLine(0);
        if(line1.startsWith("[") && line1.endsWith("]") && line1.length() > 3){
            return SignType.getType(line1.substring(1,line1.length()-1));
        }else{
            return SignType.NORMAL;
        }
    }
    
    @Override
    public boolean update() {
        return update;
    }
    
    @Override
    public void update(boolean bool) {
        update = bool;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
