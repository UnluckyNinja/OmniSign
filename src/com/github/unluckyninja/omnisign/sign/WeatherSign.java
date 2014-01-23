package com.github.unluckyninja.omnisign.sign;

import com.github.unluckyninja.omnisign.SignType;
import com.github.unluckyninja.omnisign.util.Payment;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Sign;

import java.util.Map;

/**
 * Created by Administrator on 14-1-20.
 */
public class WeatherSign extends OmniSign {

    private static final SignType type = SignType.WEATHER;

    private World world;
    private WeatherType weatherType;

    private boolean opposite = false;
    private boolean active;

    private Payment payment;

    public WeatherSign(int id, Sign sign) {
        super(id,sign);
        sign.setLine(0, "[Weather]");
        world = sign.getWorld();
    }

    @Override
    public boolean canEnable() {
        return weatherType != null;
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
        if(!active) {
            return false;
        }
        world.setStorm(weatherType == WeatherType.DOWNFALL);
        return true;
    }

    @Override
    public boolean onDisable() {
        active = false;
        return true;
    }

    @Override
    public boolean interact() {
        if (active){
            return false;
        }
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
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
