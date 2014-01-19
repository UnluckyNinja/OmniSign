
package com.github.UnluckyNinja.signplus;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/**
 * Generate the information to show.
 */
public class SignsEditor {
    private Plugin plugin;
    private String pluginname = "{PLUGINNAME}";
    private String pluginversion = "{VERSION}";
    
    public SignsEditor(Plugin plugin){
        this.plugin = plugin;
    }
    
    public String format(String string){
        // Replace the color codes.
        StringBuilder builder = new StringBuilder(ChatColor.translateAlternateColorCodes('&', string));
        // Replace the plugin name.
        for(int i = 0;builder.indexOf(pluginname)!=-1;){
            i = builder.indexOf(pluginname);
            builder.replace(i, i+pluginname.length()-1, plugin.getDescription().getName());
        }
        // Replace the plugin version.
        for(int i = 0;builder.indexOf(pluginname)!=-1;){
            i = builder.indexOf(pluginname);
            builder.replace(i, i+pluginversion.length()-1, plugin.getDescription().getVersion());
        }
        return builder.toString();
    }
}
