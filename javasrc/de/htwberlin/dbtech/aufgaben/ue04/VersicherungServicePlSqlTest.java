package de.htwberlin.dbtech.aufgaben.ue04;

import de.htwberlin.dbtech.exceptions.*;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VersicherungServicePlSqlTest {
    private static final Logger L = LoggerFactory.getLogger(VersicherungServicePlSqlTest.class);
    private static IDatabaseConnection dbTesterCon = null;

    private static final IVersicherungService vService = new VersicherungServicePlSql();

    @BeforeClass
    public static void setUp() {
        L.debug("setUp: start");
        try {
            IDatabaseTester dbTester = new JdbcDatabaseTester(DbCred.driverClass, DbCred.url, DbCred.user, DbCred.password,
                    DbCred.schema);
            dbTesterCon = dbTester.getConnection();
            IDataSet datadir = new CsvDataSet(new File("test-data/ue03-04"));
            dbTester.setDataSet(datadir);
            DatabaseOperation.CLEAN_INSERT.execute(dbTesterCon, datadir);
            vService.setConnection(dbTesterCon.getConnection());
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
     * Vertrag existiert nicht.
     */
    @org.junit.Test(expected = VertragExistiertNichtException.class)
    public void createDeckung01() {
        vService.createDeckung(99, 1, BigDecimal.valueOf(0));
    }

    /**
     * Deckungsart existiert nicht.
     */
    @org.junit.Test(expected = DeckungsartExistiertNichtException.class)
    public void createDeckung02() {
        vService.createDeckung(5, 99, BigDecimal.valueOf(0));
    }

    /**
     * Deckungsart passt nicht zu Produkt. Deckungsart 1 (Haftung) passt zu KFZV,
     * der Vertrag 5 ist aber fuer HRV.
     */
    @org.junit.Test(expected = DeckungsartPasstNichtZuProduktException.class)
    public void createDeckung03() {
        vService.createDeckung(5, 1, BigDecimal.valueOf(0));
    }

    /**
     * Deckungsbetrag ist ungueltig für Deckungsart 6 (Fahrraddiebstahl), da kein
     * Deckungsbetragsdatensatz fuer die Deckungsart vorliegt. Fuer Deckungsart 6
     * gibt es keinen Datenssatz in der Tabelle Deckungsbetrag.
     */
    @org.junit.Test(expected = UngueltigerDeckungsbetragException.class)
    public void createDeckung04() {
        vService.createDeckung(5, 6, BigDecimal.valueOf(0));
    }

    /**
     * Deckungsbetrag 2000 ist ungueltig für Deckungsart 5 (Glasbruch), da nur ein
     * Deckungsbetrag von 1500 fuer die Deckungsart vorliegt.
     */
    @org.junit.Test(expected = UngueltigerDeckungsbetragException.class)
    public void createDeckung05() {
        vService.createDeckung(5, 5, BigDecimal.valueOf(2000));
    }

    /**
     * Deckungsbetrag 1500 ist zwar gueltig fuer Deckungsart 5 (Glasbruch), es gibt
     * aber keinen Deckungspreis dafuer.
     */
    @org.junit.Test(expected = DeckungspreisNichtVorhandenException.class)
    public void createDeckung06() {
        vService.createDeckung(5, 5, BigDecimal.valueOf(1500));
    }

    /**
     * Deckungsbetrag 150000 ist gueltig fuer Deckungsart 4 (Brandschaden), da ein
     * Deckungsbetrag von 150000 existiert. Einen Deckungspreis fuer diesen
     * Deckungsbetrag gibt es auch. Allerdings wird dieser Preis nur bis Ende 2018
     * angeboten. Der Versicherungsbeginn fuer Vertrag 5 liegt aber in 2019.
     */
    @org.junit.Test(expected = DeckungspreisNichtVorhandenException.class)
    public void createDeckung07() {
        vService.createDeckung(5, 4, BigDecimal.valueOf(150000));
    }

    /**
     * Vertrag 6 (KFZV) hat einen Kunden, der noch nicht 18 Jahre alt ist. Deckungsart 1
     * (Haftung) passt zu KFZV und Deckungsbetrag 100 Mio ebenfalls. Haftung wird
     * aber fuer Kunden unter 18 nicht angeboten.
     * Achtung: Alter bezieht sich auf Versicherungszeitraum
     */
    @org.junit.Test(expected = DeckungsartNichtRegelkonformException.class)
    public void createDeckung08() {
        vService.createDeckung(6, 1, BigDecimal.valueOf(100000000));
    }

    /**
     * Vertrag 7 (LBV) hat einen Kunden, der der aelter als 90 Jahre ist.
     * Deckungsart 3 (Tod) passt zu LBV und Deckungsbetrag 100 Tsd ebenfalls. Tod
     * wird aber fuer Kunden ueber 90 nicht angeboten.
     * Achtung: Alter bezieht sich auf Versicherungszeitraum
     */
    @org.junit.Test(expected = DeckungsartNichtRegelkonformException.class)
    public void createDeckung09() {
        vService.createDeckung(7, 3, BigDecimal.valueOf(100000));
    }

    /**
     * Vertrag 8 (LBV) hat einen Kunden, der der aelter als 70 Jahre ist.
     * Deckungsart 3 (Tod) passt zu LBV und Deckungsbetrag 200 Tsd ebenfalls. Tod
     * mit einem Betrag von 200 Tsd wird aber fuer Kunden ueber 70 nicht angeboten.
     * Achtung: Alter bezieht sich auf Versicherungszeitraum
     */
    @org.junit.Test(expected = DeckungsartNichtRegelkonformException.class)
    public void createDeckung10() {
        vService.createDeckung(8, 3, BigDecimal.valueOf(200000));
    }

    /**
     * Vertrag 9 (LBV) hat einen Kunden, der der aelter als 60 Jahre ist.
     * Deckungsart 3 (Tod) passt zu LBV und Deckungsbetrag 300 Tsd ebenfalls. Tod
     * mit einem Betrag von 300 Tsd wird aber fuer Kunden ueber 60 nicht angeboten.
     * Achtung: Alter bezieht sich auf Versicherungszeitraum
     */
    @org.junit.Test(expected = DeckungsartNichtRegelkonformException.class)
    public void createDeckung11() {
        vService.createDeckung(9, 3, BigDecimal.valueOf(300000));
    }

    /**
     * Die folgenden Deckungserzeugungen sind ok und muessen in der Datebank
     * eingetragen werden.
     * Achtung: Alter bezieht sich auf Versicherungszeitraum
     */
    @org.junit.Test
    public void createDeckung12() throws Exception {
        Integer[] vertragsIds = new Integer[]{5, 8, 9};
        Integer[] deckungsartIds = new Integer[]{4, 3, 3};
        BigDecimal[] deckungsbetraege = new BigDecimal[]{BigDecimal.valueOf(50000), BigDecimal.valueOf(100000),
                BigDecimal.valueOf(200000)};
        for (int i = 0; i < 3; i++) {
            vService.createDeckung(vertragsIds[i], deckungsartIds[i], deckungsbetraege[i]);
        }

        // Hole Deckungsdatensaetze aus der Datenbank
        QueryDataSet databaseDataSet = new QueryDataSet(dbTesterCon);
        String sql = "select * from Deckung where Vertrag_FK in (5, 8, 9) order by Vertrag_FK, Deckungsart_FK";
        databaseDataSet.addTable("Deckung", sql);
        ITable tblDeckung = databaseDataSet.getTable("Deckung");

        // Wurde die richtige Anzahl an Datensaetzen in die Datenbank eingetragen?
        Assert.assertEquals("Falsche Anzahl Zeilen", 3, tblDeckung.getRowCount());

        // Wurden die richtigen Werte eingetragen?
        for (int i = 0; i < 3; i++) {
            Integer vertragsId = ((BigDecimal) tblDeckung.getValue(i, "Vertrag_FK")).intValue();
            Integer deckungsartId = ((BigDecimal) tblDeckung.getValue(i, "Deckungsart_FK")).intValue();
            BigDecimal deckungsbetrag = (BigDecimal) tblDeckung.getValue(i, "Deckungsbetrag");
            Assert.assertEquals("Falsche vertragsId", vertragsIds[i], vertragsId);
            Assert.assertEquals("Falsche deckungsartId", deckungsartIds[i], deckungsartId);
            Assert.assertEquals("Falscher deckungsbetrag", deckungsbetraege[i], deckungsbetrag);
        }

    }

}
