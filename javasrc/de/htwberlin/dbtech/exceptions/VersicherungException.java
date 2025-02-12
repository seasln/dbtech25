package de.htwberlin.dbtech.exceptions;

/**
 * @author Ingo Classen
 */
public class VersicherungException extends RuntimeException {

    public VersicherungException() {
    }

    public VersicherungException(String msg) {
        super(msg);
    }

    public VersicherungException(Throwable t) {
        super(t);
    }

    public VersicherungException(String msg, Throwable t) {
        super(msg, t);
    }

}
