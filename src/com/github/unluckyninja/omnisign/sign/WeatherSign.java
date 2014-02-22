package com.github.unluckyninja.omnisign.sign;

import com.github.unluckyninja.omnisign.util.Payment;
import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Sign;

import java.util.Map;

/**
 * Created by UnluckyNinja on 14-1-20.
 */
public class WeatherSign implements SignBehavior {

    public static final SignType type = SignType.WEATHER;

    private OmniSign omniSign;

    private World world;
    private WeatherType weatherType;

    private boolean opposite = false;

    private Payment payment;

    public WeatherSign(OmniSign omniSign) {
        this.omniSign = omniSign
        world = omniSign.getWorld();
        parseWeather();
        if (weatherType != null || opposite) {
            sign.setLine(0, ChatColor.YELLOW+"[Weather]");
        } else {
            sign.setLine(0, ChatColor.RED+"[Weather]");
        }
        sign.update(true);
    }

    public void parseWeather() {
        String weatherline = getSign().getLine(1).toLowerCase();
        if (weatherline.equals("sun") || weatherline.equals("sunny") || weatherline.equals("sunshine") || weatherline.equals("fine")) {
            weatherType = WeatherType.CLEAR;
        } else if (weatherline.equals("rain") || weatherline.equals("rainy") || weatherline.equals("storm") || weatherline.equals("snow") || weatherline.equals("snowy")) {
            weatherType = WeatherType.DOWNFALL;
        } else if (weatherline.equals("toggle")){
            opposite = true;
        } else{
            weatherType = null;
            opposite = false;
        }
    }

    @Override
    public boolean canEnable() {
        Sign sign = getSign();
        if(!active){
            if (weatherType != null || opposite){
                sign.setLine(0, ChatColor.YELLOW + "[Weather]");
            } else{
                sign.setLine(0, ChatColor.RED + "[Weather]");
            }
            sign.update();
        }
        return weatherType != null || opposite;
    }

    @Override
    public boolean onEnable() {
        Sign sign = getSign();
        if(super.onEnable()){
            getSign().setLine(0, ChatColor.GREEN + "[Weather]");
            sign.update();
            return true;
        }
        return false;
    }

    @Override
    public boolean act() {
        if (!active) {
            return false;
        }
        if(weatherType != null){
            world.setStorm(weatherType == WeatherType.DOWNFALL);
        }else{
            world.setStorm(!world.hasStorm());
        }
        return true;
    }

    @Override
    public boolean onDisable() {
        Sign sign = getSign();
        if(super.onDisable()){
            getSign().setLine(0, ChatColor.YELLOW + "[Weather]");
            sign.update();
            return true;
        }
        return false;
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
