package com.github.UnluckyNinja.signplus;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class SignPlus extends JavaPlugin {
    private Configuration config;
    private SignsManager SM = SignsManager.getInstance(this);
    int i;
    
    @Override
    public  void onEnable(){
        config = this.getConfig();
        config.options().copyDefaults(true);
        getLogger().info("[OmniSigns] OmniSigns has been enabled.");
    }
    @Override
    public void onDisable(){
        getLogger().info("[OmniSigns] OmniSigns has been disabled.");
    }
    public SignsManager getOmniSignsManager(){
        return SM;
    }
}
