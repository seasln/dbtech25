package de.htwberlin.dbtech.exceptions;

/**
 * @author Ingo Classen
 */
public class VertragExistiertNichtException extends VersicherungException {

    public VertragExistiertNichtException(Integer vertragsId) {
        super("vertragsId: " + vertragsId);
    }


}
