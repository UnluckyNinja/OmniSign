package com.github.unluckyninja.omnisign.sign;

import com.github.unluckyninja.omnisign.OmniSignMain;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;

/**
 * Created by UnluckyNinja on 14-2-7.
 */
public class OmniSignState {
    private Sign sign;

    public OmniSignState(Sign sign) {
        this.sign = sign;
    }

    public OmniSignState newInstance(Block block){
        BlockState sign = block.getState();
        if(sign instanceof  Sign){
            return new OmniSignState((Sign)sign);
        }
        return null;
    }

    public boolean updateOmniSign(){
        if(!(sign.getBlock().getState() instanceof Sign)){
            return false;
        }
        return OmniSignMain.getSignsManager().update(this);
    }

    public boolean updateSign(){
        return sign.update(false);
    }

    public boolean updateSign(boolean force){
        return sign.update(force);
    }

    public OmniSign getOmniSign() {
        return OmniSignMain.getSignsManager().getOmniSign(sign.getBlock());
    }

    public Sign getSign(){
        return sign;
    }

    public String getLine(int i) {
        return sign.getLine(i);
    }

    public void setLine(int index, String line) {
        sign.setLine(index, line);
    }

    public String[] getLines() {
        return sign.getLines();
    }
}
