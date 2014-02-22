package com.github.unluckyninja.omnisign;

import com.github.unluckyninja.omnisign.sign.OmniSign;
import com.github.unluckyninja.omnisign.sign.OmniSignState;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;

public class SignsManager {
    private static HashMap<Plugin, SignsManager> managers = new HashMap<Plugin, SignsManager>();
    private Plugin plugin;
    private HashMap<Block, OmniSign> omnisigns = new HashMap<Block, OmniSign>(128);

    private SignsManager(final Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public OmniSign getOmniSign(Block block) {
        if(!(block.getState() instanceof Sign)){
            return null;
        }
        if (omnisigns.containsKey(block)) {
            return omnisigns.get(block);
        } else {
            return createOmniSign((Sign)block.getState());
        }
    }

    private void putOmniSign(Sign sign, OmniSign omnisign) {
        omnisigns.put(sign.getBlock(), omnisign);
    }

    public static SignsManager getInstance(Plugin plugin) {
        if (managers.containsKey(plugin)) {
            return (SignsManager) managers.get(plugin);
        } else {
            SignsManager OSM = new SignsManager(plugin);
            managers.put(plugin, OSM);
            return OSM;
        }
    }

    private OmniSign createOmniSign(Sign sign) {
        return new OmniSign(new OmniSignState(sign));
    }

    private HashMap<Object, FixedMetadataValue> metadataValus = new HashMap<Object, FixedMetadataValue>();

    /**
     * @param sign
     * @param key
     * @return
     * @deprecated No persist solution.
     */
    public Object getMetadata(Sign sign, String key) {
        List<MetadataValue> values = sign.getMetadata(key);
        for (MetadataValue value : values) {
            if (value.getOwningPlugin().getDescription().getName().equals(plugin.getDescription().getName())) {
                return value.value();
            }
        }
        return null;
    }

    /**
     * @param sign
     * @param key
     * @param value
     * @deprecated No persist solution.
     */
    public void setMetadata(Sign sign, String key, Object value) {
        sign.setMetadata(key, new FixedMetadataValue(plugin, value));
    }

    public boolean update(OmniSignState state) {
        getOmniSign(state.getBlock()).parse(state);
        return true;
    }
}
