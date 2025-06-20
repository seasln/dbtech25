package de.htwberlin.dbtech.aufgaben.ue03;

/*
  @author Ingo Classen
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwberlin.dbtech.exceptions.DataException;
import de.htwberlin.dbtech.exceptions.VertragExistiertNichtException;

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
        // Gibt es die Probe in der DB?
        if (!isVertragExisting(vertragsId)) {
            throw new VertragExistiertNichtException(vertragsId);
        }
    }

    /**
     * prueft, ob der Vertrag in der DB existiert
     *
     * @param vertragsId
     *            - der Primaerschluessel des Vertrages
     * @return true - Vertrag existiert | false - Vertrag existiert nicht
     *
     * @author Patrick Dohmeier
     **/
    public boolean isVertragExisting(Integer vertragsId) {

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String sql = "select count(id) as vertragsId from Vertrag where ID = ?";
        try {
            pStmt = useConnection().prepareStatement(sql);
            pStmt.setInt(1, vertragsId);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("vertragsId") > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }


}