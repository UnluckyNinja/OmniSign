package com.github.unluckyninja.omnisign.sign;

import com.github.unluckyninja.omnisign.SignType;
import org.bukkit.block.Sign;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * Created by Administrator on 14-1-20.
 */
public abstract class OmniSign implements ConfigurationSerializable {
    private int id;
    private Sign sign;
    private boolean active;

    protected OmniSign(int id, Sign sign) {
        this.id = id;
        this.sign = sign;
    }

    /**
     * Determines if a sign can be enabled.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign can be enabled.
     */
    public boolean canEnable(){
        return true;
    };

    /**
     * Determines what to do while enabling a sign.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign is enabled.
     */
    public boolean onEnable(){
        return true;
    }

    /**
     * Determines what to do while interacting with a sign.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign successfully acted.
     */
    public boolean act(){
        return true;
    }

    /**
     * Determines what to do while enabling a sign.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign was successfully disabled.
     */
    public boolean onDisable(){
        return true;
    }

    /**
     * Determines what to do while interacting with a disabled sign.
     * Don't be concerned about permissions, it's done within listeners.
     * Offered to some special signs, like quiz, to modify the contents when disabled.
     *
     * @return true if the disabled sign successfully acted.
     */
    public boolean interact(){
        return true;
    }

    public boolean isEnabled(){
        return active;
    }

    public int getID(){
        return id;
    }

    public Sign getSign(){
        return sign;
    }

    public abstract SignType getType();

    public abstract Map<String, Object> serialize();

}
