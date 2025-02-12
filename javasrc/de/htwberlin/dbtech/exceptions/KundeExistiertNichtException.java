package de.htwberlin.dbtech.exceptions;

/**
 * @author Ingo Classen
 */
public class KundeExistiertNichtException extends VersicherungException {

    public KundeExistiertNichtException(Integer kundenId) {
        super("kundenId: " + kundenId);
    }


}
