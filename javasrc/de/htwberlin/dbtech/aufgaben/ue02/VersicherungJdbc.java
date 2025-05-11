package de.htwberlin.dbtech.aufgaben.ue02;


/*
  @author Ingo Classen
 */

import de.htwberlin.dbtech.exceptions.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
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

        List<String> kurzBez = new LinkedList<String>();
        String query = "SELECT kurzbez from produkt order by id asc";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
             ps = useConnection().prepareStatement(query);

             rs = ps.executeQuery();
             while (rs.next()) {
                 kurzBez.add(rs.getString("kurzbez"));
             }
        }
        catch (SQLException e) {
            throw new DataException(e);

        }

        return kurzBez;
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

     /* "Man kann mit Methoden erweitern"
    public boolean isPrimKeyExisting(int id, String tablename){
        boolean isExisting = false;

        //JDBC Zugriff

        return isExisting;
    }

    bis hier nur */

}