package de.htwberlin.dbtech.exceptions;

/**
 * @author Ingo Classen
 */
public class ProduktExistiertNichtException extends VersicherungException {

    public ProduktExistiertNichtException(Integer produktId) {
        super("produktId: " + produktId);
    }


}
