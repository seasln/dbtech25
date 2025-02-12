package de.htwberlin.dbtech.exceptions;

/**
 * @author Ingo Classen
 */
public class DeckungsartPasstNichtZuProduktException extends VersicherungException {

    public DeckungsartPasstNichtZuProduktException() {
        super();
    }

    public DeckungsartPasstNichtZuProduktException(Integer produktIdAusDeckungart, Integer produktIdAusVertrag) {
        super("produktIdAusDeckungart: " + produktIdAusDeckungart + " <-> produktIdAusVertrag: " + produktIdAusVertrag);
    }

}
