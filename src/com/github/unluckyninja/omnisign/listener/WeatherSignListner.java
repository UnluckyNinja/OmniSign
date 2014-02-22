package com.github.unluckyninja.omnisign.listener;

import com.github.unluckyninja.omnisign.OmniSignMain;
import com.github.unluckyninja.omnisign.SignsManager;
import com.github.unluckyninja.omnisign.event.SignClickEvent;
import com.github.unluckyninja.omnisign.event.SignCreateEvent;
import com.github.unluckyninja.omnisign.sign.SignType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

/**
 * Created by UnluckyNinja on 14-1-23.
 */
public class WeatherSignListner implements Listener {
    private OmniSignMain OS;
    private SignsManager SM;

    public WeatherSignListner(OmniSignMain OS){
        this.OS = OS;
        SM = OS.getSignsManager();
    }

    @EventHandler
    public void onWeatherSignClicked(SignClickEvent event){
        if(event.getSignType() == SignType.WEATHER){
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
                event.getOmniSign().act();
            } else if(event.getAction() == Action.LEFT_CLICK_BLOCK){
                event.getOmniSign().toggle();
            }
        }
    }

    @EventHandler
    public void onWeatherSignCreated(SignCreateEvent event){
        if(event.getSignType() == SignType.WEATHER){
            event.getPlayer().sendMessage("Weather sign has been created.");
        }
    }
}
