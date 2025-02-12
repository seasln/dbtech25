package de.htwberlin.dbtech.exceptions;

import java.math.BigDecimal;

/**
 * @author Ingo Classen
 */
public class UngueltigerDeckungsbetragException extends VersicherungException {

    public UngueltigerDeckungsbetragException(BigDecimal deckungsbetrag) {
        super("deckungsbetrag: " + deckungsbetrag);
    }


}
