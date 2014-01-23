package com.github.unluckyninja.omnisign.sign;

/**
 * Created by Administrator on 14-1-22.
 */
public class SignException extends Exception {

    public SignException(String message) {
        super(message);
    }

    public SignException(String message, Throwable cause) {
        super(message, cause);
    }
}
