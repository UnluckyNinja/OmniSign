package com.github.unluckyninja.omnisign.sign;

import com.github.unluckyninja.omnisign.SignType;
import org.bukkit.block.Sign;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * Created by Administrator on 14-1-20.
 */
public interface OmniSign extends ConfigurationSerializable {
    /**
     * Determines if a sign can be enabled.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign can be enabled.
     */
    public boolean canEnable();

    /**
     * Determines what to do while enabling a sign.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign is enabled.
     */
    public boolean onEnable();

    /**
     * Determines what to do while interacting with a sign.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign successfully acted.
     */
    public boolean act();

    /**
     * Determines what to do while enabling a sign.
     * Don't be concerned about permissions, it's done within listeners.
     */
    public void onDisable();
    /**
     * Determines what to do while interacting with a disabled sign.
     * Don't be concerned about permissions, it's done within listeners.
     * Offered to some special signs, like quiz, to modify the contents when disabled.
     *
     * @return true if the disabled sign successfully acted.
     */
    public boolean ediact();

    public boolean isEnabled();

    public int getID();

    public Sign getSign();

    public SignType getType();

    public Map<String, Object> serialize();

}
