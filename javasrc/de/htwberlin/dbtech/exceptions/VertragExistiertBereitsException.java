package de.htwberlin.dbtech.exceptions;

/**
 * @author Ingo Classen
 */
public class VertragExistiertBereitsException extends VersicherungException {

    public VertragExistiertBereitsException(Integer vertragsId) {
        super("vertragsId: " + vertragsId);
    }


}
