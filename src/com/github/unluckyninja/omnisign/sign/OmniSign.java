package com.github.unluckyninja.omnisign.sign;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * Created by UnluckyNinja on 14-1-20.
 */
public class OmniSign implements ConfigurationSerializable {
    public static final ChatColor enableColor = ChatColor.GREEN;
    public static final ChatColor disableColor = ChatColor.YELLOW;
    public static final ChatColor brokenColor = ChatColor.RED;

    private int id;
    private Block block;

    boolean active;

    private SignBehavior behavior;

    public OmniSign(OmniSignState state) {
        block = state.getSign().getBlock();
    }

    public boolean parse(OmniSignState state) {
        String line1 = state.getLine(0);
        SignType type = SignType.NORMAL;
        if (line1.startsWith("[") && line1.endsWith("]") && line1.length() > 3) {
            type = SignType.getType(line1.substring(1, line1.length() - 1));
        }
        switch (type) {
            case TIME:
                behavior = new TimeSign(state);
                break;
            case WEATHER:
                behavior = new WeatherSign(state);
                break;
            default:
                behavior = new NormalSign(state);
        }
        return canEnable();
    }

    /**
     * @see com.github.unluckyninja.omnisign.sign.SignBehavior#canEnable(OmniSign)
     */
    public boolean canEnable() {
        return behavior.canEnable(this);
    }

    ;

    /**
     * @see com.github.unluckyninja.omnisign.sign.SignBehavior#onEnable(OmniSign)
     */
    public boolean onEnable() {
        return behavior.onEnable(this);
    }

    /**
     * @see com.github.unluckyninja.omnisign.sign.SignBehavior#act(OmniSign)
     */
    public boolean act() {
        return behavior.act(this);
    }

    /**
     * @see com.github.unluckyninja.omnisign.sign.SignBehavior#onDisable(OmniSign)
     */
    public boolean onDisable() {
        return behavior.onDisable(this);
    }

    /**
     * @see com.github.unluckyninja.omnisign.sign.SignBehavior#interact(OmniSign)
     */
    public boolean interact() {
        if (active) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return active;
    }

    public int getID() {
        return id;
    }

    public SignType getType() {
        return behavior.getType();
    }

    public Map<String, Object> serialize() {
        return null;
    }

    public boolean toggle() {
        if (active) {
            return onDisable();
        } else {
            return onEnable();
        }
    }

    public OmniSignState getOmniSignState() {
        BlockState state = block.getState();
        if (!(state instanceof Sign)) {
            throw new IllegalStateException("There is no more a sign at " + state.getLocation());
        }
        Sign sign = (Sign) state;
        sign.setLine(0, this.toString(0));
        sign.setLine(1, this.toString(1));
        sign.setLine(2, this.toString(2));
        sign.setLine(3, this.toString(3));
        return new OmniSignState(sign);
    }

    public String toString(int line) {
        return behavior.toString(line);
    }
}
