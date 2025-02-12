package de.htwberlin.dbtech.exceptions;

import java.math.BigDecimal;

/**
 * @author Ingo Classen
 */
public class DeckungspreisNichtVorhandenException extends VersicherungException {

    public DeckungspreisNichtVorhandenException(BigDecimal deckungsbetrag) {
        super("deckungsbetrag: " + deckungsbetrag);
    }


}
