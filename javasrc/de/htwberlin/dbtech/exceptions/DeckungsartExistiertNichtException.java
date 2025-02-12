package de.htwberlin.dbtech.exceptions;

/**
 * @author Ingo Classen
 */
public class DeckungsartExistiertNichtException extends VersicherungException {

    public DeckungsartExistiertNichtException(Integer deckungsartId) {
        super("deckungsartId: " + deckungsartId);
    }


}
