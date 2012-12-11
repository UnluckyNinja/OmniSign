package com.github.UnluckyNinja.signplus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.block.Sign;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class SignsManager {
    private Plugin plugin;
    private static Map<Plugin,SignsManager> managers = new HashMap<>();
    
    private SignsManager(final Plugin plugin){
        this.plugin = plugin;
    }
    
    public Plugin getPlugin(){
        return plugin;
    }
    
    public Object getMetadata(Sign sign, String key) {
        List<MetadataValue> values = sign.getMetadata(key);
        for (MetadataValue value : values) {
            if (value.getOwningPlugin().getDescription().getName().equals(plugin.getDescription().getName())) {
                return value.value();
            }
        }
        return null;
    }
    public static SignsManager getInstance(Plugin plugin){
        if(managers.containsKey(plugin)){
            return (SignsManager)managers.get(plugin);
        }else{
            SignsManager OSM = new SignsManager(plugin);
            managers.put(plugin, OSM);
            return OSM;
        }
    }
}
