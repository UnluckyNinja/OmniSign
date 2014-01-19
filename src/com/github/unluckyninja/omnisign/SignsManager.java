package com.github.unluckyninja.omnisign;

import org.bukkit.block.Sign;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignsManager {
    private static Map<String,SignsManager> managers = new HashMap<String,SignsManager>();
    private Plugin plugin;
    private Map<Object,FixedMetadataValue> metadataValus = new HashMap<Object,FixedMetadataValue>();
    
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
    
    public void setMetadata(Sign sign, String key, Object value){
        sign.setMetadata(key, new FixedMetadataValue(plugin,value));
    }
    
    public static SignsManager getInstance(Plugin plugin){
        String name = plugin.getDescription().getName();
        if(managers.containsKey(name)){
            return (SignsManager)managers.get(name);
        }else{
            SignsManager OSM = new SignsManager(plugin);
            managers.put(name, OSM);
            return OSM;
        }
    }
}
