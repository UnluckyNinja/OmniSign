/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.unluckyninja.omnisign.event;

import com.github.unluckyninja.omnisign.SignType;
import com.github.unluckyninja.omnisign.sign.OmniSign;

public interface SignEvent{
    public OmniSign getOmniSign();
    public SignType getSignType();

    public boolean update();

    public void update(boolean bool);
}
