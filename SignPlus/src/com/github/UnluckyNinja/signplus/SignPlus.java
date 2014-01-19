package com.github.UnluckyNinja.signplus;

import com.github.UnluckyNinja.signplus.listeners.SignsListener;
import com.github.UnluckyNinja.signplus.listeners.TimeSignListener;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class SignPlus extends JavaPlugin {
    private Configuration config;
    private SignsManager SM;
    int i;
    
    @Override
    public  void onEnable(){
        SM = SignsManager.getInstance(this);
        config = this.getConfig();
        config.options().copyDefaults(true);
        getServer().getPluginManager().registerEvents(new SignsListener(this), this);
        getServer().getPluginManager().registerEvents(new TimeSignListener(this), this);
        getLogger().info("SignPlus has been enabled.");
    }
    @Override
    public void onDisable(){
        getLogger().info("SignPlus has been disabled.");
    }
    public SignsManager getSignsManager(){
        return SM;
    }
}
