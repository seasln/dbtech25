package de.htwberlin.dbtech.bsp.auktion;

import de.htwberlin.dbtech.exceptions.ServiceException;

import java.sql.Connection;

public class GebotFinder {
    private Connection connection = null;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection useConnection() {
        if (connection == null) {
            throw new ServiceException("Service hat keine Connection");
        }
        return connection;
    }

    public Gebot findById(Integer gid) {
        Gebot g = new Gebot();
        g.setConnection(useConnection());
        // Sql-Code zum Befuellen des Gebotsobjekts
        return g;
    }

}
