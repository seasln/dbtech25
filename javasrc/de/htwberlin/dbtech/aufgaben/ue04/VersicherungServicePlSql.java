package de.htwberlin.dbtech.aufgaben.ue04;

/*
  @author Ingo Classen
 */

import de.htwberlin.dbtech.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * VersicherungJdbc
 */
public class VersicherungServicePlSql implements IVersicherungService {
    private static final Logger L = LoggerFactory.getLogger(VersicherungServicePlSql.class);
    private Connection connection;

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

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
        try (CallableStatement cStmt = useConnection().prepareCall("{call versicherungsservice.create_deckung(?, ?, ?)}")) {
            cStmt.setInt(1, vertragsId);
            cStmt.setInt(2, deckungsartId);
            cStmt.setBigDecimal(3, deckungsbetrag);
            cStmt.executeUpdate();
            L.info("ende");
        } catch (SQLException e) {
            L.info("Error code: " + e.getErrorCode());
            if (e.getErrorCode() == 20002) {
                throw new VertragExistiertNichtException(vertragsId);
            } else if (e.getErrorCode() == 20003) {
                throw new DeckungsartExistiertNichtException(deckungsartId);
            } else if (e.getErrorCode() == 20004) {
                throw new UngueltigerDeckungsbetragException(deckungsbetrag);
            } else if (e.getErrorCode() == 20005) {
                throw new DeckungsartPasstNichtZuProduktException();
            } else if (e.getErrorCode() == 20006) {
                throw new DeckungsartNichtRegelkonformException(deckungsartId);
            } else if (e.getErrorCode() == 20007) {
                throw new DeckungspreisNichtVorhandenException(deckungsbetrag);
            } else {
                throw new DataException(e);
            }
        }
    }

}