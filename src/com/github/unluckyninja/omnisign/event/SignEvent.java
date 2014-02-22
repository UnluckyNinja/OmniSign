/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.unluckyninja.omnisign.event;

import com.github.unluckyninja.omnisign.sign.OmniSignState;
import com.github.unluckyninja.omnisign.sign.SignType;
import org.bukkit.block.Sign;

public interface SignEvent{
    public Sign getSign();

    public OmniSignState getOmniSignState();
    public SignType getSignType();
}
