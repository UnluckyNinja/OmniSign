package com.github.unluckyninja.omnisign.sign;

import org.bukkit.ChatColor;
import org.bukkit.World;

/**
 * Created by UnluckyNinja on 14-1-23.
 */
public class TimeSign implements SignBehavior {
    public static final SignType type = SignType.TIME;
    public String[] lines = new String[]{"","","",""};

    private World world;
    private Long time;

    public TimeSign(OmniSignState state) {
        this.lines = state.getLines();

        world = state.getSign().getWorld();

        parse(state);
    }

    public boolean parse(OmniSignState state) {

        String line = state.getLine(1);
        if (line.equalsIgnoreCase("day") || line.equalsIgnoreCase("dawn")) {
            time = 6000L;
        } else if (line.equalsIgnoreCase("night") || line.equalsIgnoreCase("dusk")) {
            time = 18000L;
        } else if (line.equalsIgnoreCase("midnight")) {
            time = 0L;
        } else if (line.equalsIgnoreCase("noon")) {
            time = 12000L;
        } else if (line.matches("^\\d?\\d:\\d\\d$")) {
            String[] times = line.split(":");
            time = Long.parseLong(times[0]) * 1000 + Long.parseLong(times[1]) / 3L * 50L;
        } else {
            time = null;
            return false;
        }

        if (time != null) {
            setLine(0, ChatColor.YELLOW + "[Time]");
        } else {
            setLine(0, ChatColor.RED + "[Time]");
        }
        state.updateSign();
        return true;
    }

    @Override
    public boolean onDisable(OmniSign omniSign) {
        omniSign.active = false;
        setLine(0, ChatColor.YELLOW + "[Time]");
        OmniSignState state = omniSign.getOmniSignState();
        state.updateSign();
        return true;
    }

    @Override
    public boolean interact(OmniSign omniSign) {
        if (!omniSign.active) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEnabled(OmniSign omniSign) {
        return true;
    }

    @Override
    public boolean act(OmniSign omniSign) {
        if (!omniSign.active) {
            return false;
        }
        world.setTime(time);
        return true;
    }

    @Override
    public boolean onEnable(OmniSign omniSign) {
        if (!omniSign.canEnable()) {
            return false;
        }

        setLine(0, ChatColor.GREEN + "[Time]");

        OmniSignState state = omniSign.getOmniSignState();
        state.updateSign();

        omniSign.active = true;

        return true;
    }

    @Override
    public boolean canEnable(OmniSign omniSign) {
        return time != null;
    }

    @Override
    public SignType getType() {
        return SignType.TIME;
    }

    @Override
    public String toString(int line) {
        return lines[line];
    }

    private void setLine(int index, String line){
        lines[index] = line;
    }
}
