package com.github.unluckyninja.omnisign.sign;

import com.github.unluckyninja.omnisign.SignType;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Sign;

import java.util.Map;

/**
 * Created by Administrator on 14-1-20.
 */
public class TimeSign implements OmniSign {

    private static final SignType type = SignType.TIME;

    private int id;
    private final Sign sign;

    private World world;
    private WeatherType weatherType;

    private boolean opposite = false;
    private boolean active;

    public TimeSign(int id, Sign sign) {
        this.id = id;
        this.sign = sign;
        world = sign.getWorld();
    }

    @Override
    public boolean canEnable() {
        return false;
    }

    @Override
    public boolean onEnable() {
        if(!canEnable()){
            return false;
        }

        return active = true;
    }

    @Override
    public boolean act() {
        return false;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean ediact() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public Sign getSign() {
        return sign;
    }

    @Override
    public SignType getType() {
        return type;
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}
