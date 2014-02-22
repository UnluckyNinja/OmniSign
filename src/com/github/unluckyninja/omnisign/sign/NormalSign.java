package com.github.unluckyninja.omnisign.sign;

import java.util.Map;

/**
 * Created by UnluckyNinja on 14-1-23.
 */
public class NormalSign implements SignBehavior {
    private static final SignType type = SignType.NORMAL;

    private OmniSign omniSign;

    public NormalSign(OmniSign omniSign) {
        this.omniSign = omniSign;
    }

    @Override
    public boolean canEnable(OmniSign omniSign) {
        return false;
    }

    @Override
    public boolean onEnable(OmniSign omniSign) {
        return false;
    }

    @Override
    public boolean act(OmniSign omniSign) {
        return false;
    }

    @Override
    public boolean onDisable(OmniSign omniSign) {
        return false;
    }

    @Override
    public boolean interact(OmniSign omniSign) {
        return true;
    }

    @Override
    public boolean isEnabled(OmniSign omniSign) {
        return false;
    }

    @Override
    public SignType getType() {
        return type;
    }

    public Map<String, Object> serialize() {
        return null;
    }
}
