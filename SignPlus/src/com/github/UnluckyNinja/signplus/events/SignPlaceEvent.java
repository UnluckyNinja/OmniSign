package com.github.UnluckyNinja.signplus.events;

import com.github.UnluckyNinja.signplus.SignType;
import org.bukkit.block.Sign;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;

public class SignPlaceEvent extends BlockPlaceEvent implements SignEvent{
    private static final HandlerList handlers = new HandlerList();
    private Sign sign;
    private boolean update;
    
    public SignPlaceEvent(BlockPlaceEvent event){
        super(event.getBlockPlaced(),event.getBlockReplacedState(),event.getBlockAgainst(),event.getItemInHand(),event.getPlayer(),event.canBuild());
        sign = (Sign)this.block.getState();
        update = false;
    }
    @Override
    public Sign getSign(){
        return sign;
    }
    
    @Override
    public SignType getSignType() {
        String line1 = sign.getLine(1);
        return SignType.getType(line1.substring(1,line1.length()-2 ));
    }

    @Override
    public boolean isNormalSign() {
        return !(sign.getLine(1).startsWith("[") && sign.getLine(1).endsWith("]"));
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
