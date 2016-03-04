package com.synebo.exception;

/**
 * Created by User on 26.02.2016.
 */
public class SynchronizationException extends Exception {

    public SynchronizationException(String message) {
        super(message);
    }

    public SynchronizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
