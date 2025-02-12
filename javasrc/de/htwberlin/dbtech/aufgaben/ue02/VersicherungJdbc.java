package de.htwberlin.dbtech.aufgaben.ue02;


/*
  @author Ingo Classen
 */

import de.htwberlin.dbtech.exceptions.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

/**
 * VersicherungJdbc
 */
public class VersicherungJdbc implements IVersicherungJdbc {
    private static final Logger L = LoggerFactory.getLogger(VersicherungJdbc.class);
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
    public List<String> kurzBezProdukte() {
        L.info("start");
        L.info("ende");
        return null;
    }

    @Override
    public Kunde findKundeById(Integer id) {
        L.info("id: " + id);
        L.info("ende");
        return null;
    }

    @Override
    public void createVertrag(Integer id, Integer produktId, Integer kundenId, LocalDate versicherungsbeginn) {
        L.info("id: " + id);
        L.info("produktId: " + produktId);
        L.info("kundenId: " + kundenId);
        L.info("versicherungsbeginn: " + versicherungsbeginn);
        L.info("ende");
    }

    @Override
    public BigDecimal calcMonatsrate(Integer vertragsId) {
        L.info("vertragsId: " + vertragsId);

        L.info("ende");
        return null;
    }

}