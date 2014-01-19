/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.unluckyninja.omnisign.events;

import com.github.unluckyninja.omnisign.SignType;
import org.bukkit.block.Sign;

public interface SignEvent{
    public Sign getSign();
    public SignType getSignType();
    public boolean isNormalSign();
    public boolean update();
    public void update(boolean bool);
}
