package com.github.unluckyninja.omnisign.event;

import com.github.unluckyninja.omnisign.sign.OmniSignState;
import com.github.unluckyninja.omnisign.sign.SignType;
import org.bukkit.block.Sign;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.SignChangeEvent;

public class SignCreateEvent extends SignChangeEvent implements SignEvent{
    private static final HandlerList handlers = new HandlerList();
    private Sign sign;
    private boolean update;
    private OmniSignState omniSignState;
    
    public SignCreateEvent(SignChangeEvent event){
        super(event.getBlock(), event.getPlayer(), event.getLines());
        sign = (Sign)event.getBlock().getState();
        omniSignState = new OmniSignState(sign);
        update = false;
    }

    @Override
    public Sign getSign() {
        return sign;
    }

    @Override
    public OmniSignState getOmniSignState() {
        return omniSignState;
    }

    public void setOmniSign(OmniSignState omniSignState){
        this.omniSignState = omniSignState;
    }

    @Override
    public SignType getSignType() {
        String line1 = getLine(0);
        if(line1.startsWith("[") && line1.endsWith("]") && line1.length() > 3){
            return SignType.getType(line1.substring(1,line1.length()-1 ));
        }else{
            return SignType.NORMAL;
        }
    }

    @Override
    public void setLine(int index, String line) throws IndexOutOfBoundsException {
        if (index == 0){
            throw new IndexOutOfBoundsException("You can't modify the first line in SignCreateEvent, modify sign instead");
        }
        super.setLine(index, line);
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
