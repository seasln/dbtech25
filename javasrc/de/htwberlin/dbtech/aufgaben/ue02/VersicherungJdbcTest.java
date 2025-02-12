package de.htwberlin.dbtech.aufgaben.ue02;

import de.htwberlin.dbtech.exceptions.DatumInVergangenheitException;
import de.htwberlin.dbtech.exceptions.KundeExistiertNichtException;
import de.htwberlin.dbtech.exceptions.ProduktExistiertNichtException;
import de.htwberlin.dbtech.exceptions.VertragExistiertBereitsException;
import de.htwberlin.dbtech.utils.DbCred;
import de.htwberlin.dbtech.utils.DbUnitUtils;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VersicherungJdbcTest {
    private static final Logger L = LoggerFactory.getLogger(VersicherungJdbcTest.class);
    private static IDatabaseConnection dbTesterCon = null;

    private static final IVersicherungJdbc vj = new VersicherungJdbc();

    @BeforeClass
    public static void setUp() {
        L.debug("setup: start");
        try {
            IDatabaseTester dbTester = new JdbcDatabaseTester(DbCred.driverClass, DbCred.url, DbCred.user, DbCred.password,
                    DbCred.schema);
            dbTesterCon = dbTester.getConnection();
            IDataSet pre = new CsvDataSet(new File("test-data/ue02"));
            dbTester.setDataSet(pre);
            DatabaseOperation.CLEAN_INSERT.execute(dbTesterCon, pre);
            vj.setConnection(dbTesterCon.getConnection());
        } catch (Exception e) {
            DbUnitUtils.closeDbUnitConnectionQuietly(dbTesterCon);
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public static void tearDown() {
        L.debug("tearDown: start");
        DbUnitUtils.closeDbUnitConnectionQuietly(dbTesterCon);
    }


    /**
     * Wird die richtige Liste der Produkte zurueckgeliefert?.
     */
    @org.junit.Test
    public void aaakurzBezProdukte() {
        String[] actualKurzBezes = new String[3];
        List<String> kurzBezes = vj.kurzBezProdukte();
        for (int i = 0; i < kurzBezes.size(); i++) {
            actualKurzBezes[i] = kurzBezes.get(i);
        }
        String[] expectedKurzBezes = {"KFZV", "LBV", "HRV"};
        Assert.assertArrayEquals("Liste falsch:", expectedKurzBezes, actualKurzBezes);
    }

    /**
     * Kunde existiert nicht.
     */
    @org.junit.Test(expected = KundeExistiertNichtException.class)
    public void bbbfindKundeById1() {
        vj.findKundeById(999);
    }

    /**
     * Wird richtiger Kunde zurueckgeliefert?
     */
    @org.junit.Test
    public void bbbfindKundeById2() {
        Kunde kunde = vj.findKundeById(1);

        Assert.assertEquals("Name falsch:", "Gaul", kunde.getName());

        LocalDate d = LocalDate.of(1980, 3, 24);
        Assert.assertEquals("Geburtsdatum falsch:", d, kunde.getGeburtsdatum());
    }

    /**
     * Versicherungsbeginn liegt in der Vergangenheit.
     */
    @org.junit.Test(expected = DatumInVergangenheitException.class)
    public void cccCreateVertrag1() {
        Integer vertragsId = 1;
        Integer produktId = 1;
        Integer kundenId = 1;
        LocalDate versicherungsbeginn = LocalDate.now().minusDays(1);

        vj.createVertrag(vertragsId, produktId, kundenId, versicherungsbeginn);
    }

    /**
     * Vertrag existiert bereits.
     */
    @org.junit.Test(expected = VertragExistiertBereitsException.class)
    public void cccCreateVertrag2() {
        Integer vertragsId = 1;
        Integer produktId = 1;
        Integer kundenId = 1;
        LocalDate versicherungsbeginn = LocalDate.now();

        vj.createVertrag(vertragsId, produktId, kundenId, versicherungsbeginn);
    }

    /**
     * Produkt existiert nicht.
     */
    @org.junit.Test(expected = ProduktExistiertNichtException.class)
    public void cccCreateVertrag3() {
        Integer vertragsId = 777;
        Integer produktId = 99;
        Integer kundenId = 1;
        LocalDate versicherungsbeginn = LocalDate.now();

        vj.createVertrag(vertragsId, produktId, kundenId, versicherungsbeginn);
    }

    /**
     * Kunde existiert nicht.
     */
    @org.junit.Test(expected = KundeExistiertNichtException.class)
    public void cccCreateVertrag4() {
        Integer vertragsId = 777;
        Integer produktId = 1;
        Integer kundenId = 999;
        LocalDate versicherungsbeginn = LocalDate.now();

        vj.createVertrag(vertragsId, produktId, kundenId, versicherungsbeginn);
    }

    /**
     * Vertrag wird angelegt. Ist er in der Datenbank gespeichert?
     */
    @org.junit.Test
    public void cccCreateVertrag5() throws Exception {
        Integer vertragsId = 777;
        Integer produktId = 1;
        Integer kundenId = 1;
        LocalDate versicherungsbeginn = LocalDate.now();

        vj.createVertrag(vertragsId, produktId, kundenId, versicherungsbeginn);

        // Hole Vertragsdatensatz fue ID 777 aus der Datenbank
        QueryDataSet databaseDataSet = new QueryDataSet(dbTesterCon);
        String sql = "select * from Vertrag where ID=777";
        databaseDataSet.addTable("Vertrag777", sql);
        ITable vertrag777 = databaseDataSet.getTable("Vertrag777");

        // Ist Datensatz in der Datenbank
        Assert.assertEquals("Falsche Anzahl Zeilen", 1, vertrag777.getRowCount());

        // Wurde das Versicherungsende korrekt berechnet?
        LocalDate versicherungsende = ((java.sql.Timestamp) vertrag777.getValue(0, "Versicherungsende")).toLocalDateTime()
                .toLocalDate();
        Assert.assertEquals("Falsches Versicherungsende", versicherungsbeginn.plusYears(1).minusDays(1), versicherungsende);
    }

    /**
     * Wird die monatliche Rate richtig berechnet?.
     */
    @org.junit.Test
    public void dddcalcMonatsrate() {
        // Versicherungsbeginn von Vertrag 1 liegt in 2017
        BigDecimal monatsrate2017 = vj.calcMonatsrate(1);
        Assert.assertEquals("Falsche Monatsrate", BigDecimal.valueOf(19), monatsrate2017);

        // Versicherungsbeginn von Vertrag 2 liegt in 2018
        BigDecimal monatsrate2018 = vj.calcMonatsrate(2);
        Assert.assertEquals("Falsche Monatsrate", BigDecimal.valueOf(20), monatsrate2018);

        // Versicherungsbeginn von Vertrag 3 liegt in 2019
        BigDecimal monatsrate2019 = vj.calcMonatsrate(3);
        Assert.assertEquals("Falsche Monatsrate", BigDecimal.valueOf(22), monatsrate2019);

        // VVertrag 4 hat keine Deckungen
        BigDecimal monatsrateLeer = vj.calcMonatsrate(4);
        Assert.assertEquals("Falsche Monatsrate", BigDecimal.ZERO, monatsrateLeer);

    }

}
