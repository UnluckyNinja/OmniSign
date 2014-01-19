/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.unluckyninja.omnisign;

import org.bukkit.metadata.MetadataStoreBase;

/**
 *
 * @author Administrator
 */
public class SignMetadataStoreBase<T> extends MetadataStoreBase<T> {

    @Override
    protected String disambiguate(T subject, String metadataKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
