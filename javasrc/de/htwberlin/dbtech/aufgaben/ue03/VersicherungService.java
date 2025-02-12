package de.htwberlin.dbtech.aufgaben.ue03;

/*
  @author Ingo Classen
 */

import de.htwberlin.dbtech.exceptions.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 * VersicherungJdbc
 */
public class VersicherungService implements IVersicherungService {
    private static final Logger L = LoggerFactory.getLogger(VersicherungService.class);
    private Connection connection;

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @SuppressWarnings("unused")
    private Connection useConnection() {
        if (connection == null) {
            throw new DataException("Connection not set");
        }
        return connection;
    }

    @Override
    public void createDeckung(Integer vertragsId, Integer deckungsartId, BigDecimal deckungsbetrag) {
        L.info("vertragsId: " + vertragsId);
        L.info("deckungsartId: " + deckungsartId);
        L.info("deckungsbetrag: " + deckungsbetrag);
        L.info("ende");
    }


}