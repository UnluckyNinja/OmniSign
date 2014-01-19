package com.github.unluckyninja.omnisign.events;

import com.github.unluckyninja.omnisign.SignType;
import org.bukkit.block.Sign;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author Administrator
 */
public class SignBreakEvent extends BlockBreakEvent implements SignEvent {
    private static final HandlerList handlers = new HandlerList();
    private Sign sign;
    private boolean update;
    
    public SignBreakEvent(BlockBreakEvent event){
        super(event.getBlock(),event.getPlayer());
        sign = (Sign)this.block.getState();
        update = false;
    }
    @Override
    public Sign getSign(){
        return sign;
    }
    
    @Override
    public SignType getSignType() {
        String line1 = sign.getLine(0);
        if(line1.startsWith("[") && line1.endsWith("]") && line1.length() > 3){
            return SignType.getType(line1.substring(1,line1.length()-1 ));
        }else{
            return SignType.NORMAL;
        }
    }
    
    @Override
    public boolean isNormalSign() {
        return !(sign.getLine(0).startsWith("[") && sign.getLine(0).endsWith("]"));
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
