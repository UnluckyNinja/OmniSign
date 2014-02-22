package com.github.unluckyninja.omnisign.sign;

/**
 * Created by UnluckyNinja on 14-2-8.
 */
public interface SignBehavior {

    public boolean parse(OmniSignState state);

    /**
     * Determines if a sign can be enabled.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign can be enabled.
     */
    public boolean canEnable(OmniSign omniSign);

    /**
     * Determines what to do while enabling a sign.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign is enabled.
     */
    public boolean onEnable(OmniSign omniSign);

    /**
     * Determines what to do while interacting with a sign.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign successfully acted.
     */
    public boolean act(OmniSign omniSign);

    /**
     * Determines what to do while enabling a sign.
     * Don't be concerned about permissions, it's done within listeners.
     *
     * @return true if the sign was successfully disabled.
     */
    public boolean onDisable(OmniSign omniSign);

    /**
     * Determines what to do while interacting with a disabled sign.
     * Don't be concerned about permissions, it's done within listeners.
     * Offered to some special signs, like quiz, to modify the contents when disabled.
     *
     * @return true if the disabled sign successfully acted.
     */
    public boolean interact(OmniSign omniSign);

    public boolean isEnabled(OmniSign omniSign);

    public SignType getType();

    public String toString(int line);
}
