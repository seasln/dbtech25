package de.htwberlin.dbtech.exceptions;

import java.time.LocalDate;

/**
 * @author Ingo Classen
 */
public class DatumInVergangenheitException extends VersicherungException {

    public DatumInVergangenheitException(LocalDate versicherungsbeginn) {
        super("versicherungsbeginn: " + versicherungsbeginn);
    }


}
