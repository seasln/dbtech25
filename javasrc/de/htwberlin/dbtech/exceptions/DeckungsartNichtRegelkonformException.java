package de.htwberlin.dbtech.exceptions;

/**
 * @author Ingo Classen
 */
public class DeckungsartNichtRegelkonformException extends VersicherungException {

    public DeckungsartNichtRegelkonformException(Integer deckungsartId) {
        super("deckungsartId: " + deckungsartId);
    }


}
