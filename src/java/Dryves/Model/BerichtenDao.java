/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import Dryves.ConnectionManager;
import Dryves.DatumConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author H
 */
public class BerichtenDao {

    List<Lid> afzender = new ArrayList<Lid>();
    static Connection currentCon;
    static ResultSet resultSet;
    static PreparedStatement statement;
    private int lidnr;
    private String onderwerp;
    private Timestamp datum;
    private String inhoud;
    private int ritnr;

    //achterhaal lid nr d.m.v berichtid
    public int haalLidNr(int ritnr) throws SQLException {





        int lidnr = 0;

        try {

            currentCon = ConnectionManager.getConnection();

            String queryString = "SELECT lidnr FROM rit WHERE ritnr=?;";

            statement = currentCon.prepareStatement(queryString);
            statement.setInt(1, ritnr);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                lidnr = resultSet.getInt(1);
                System.out.println("Lidnummer: " + lidnr);
            }
        } catch (Exception e) {
            System.out.println(e);

        } finally {
            currentCon.close();
            statement.close();
            resultSet.close();
        }
        System.out.println("output van haallidnr: " + lidnr);
        return lidnr;


    }

    //min 1 bij ongelezen berichten
    public void markeerBericht(int berichtid) throws SQLException {


        try {
            currentCon = ConnectionManager.getConnection();
            String queryString = "UPDATE berichten set gelezen=0 WHERE berichtnr =?;";

            statement = currentCon.prepareStatement(queryString);
            statement.setInt(1, berichtid);

            resultSet = statement.executeQuery();


        } catch (SQLException e) {


            System.out.println(e);
        } finally {

            currentCon.close();
            statement.close();
            resultSet.close();
        }

    }

    /////verstuur bericht 
    public boolean BerichtVersturen(Berichten bean) throws SQLException {

        //Met deze functie komen we erachter wie
        //de eigenaar is van de aangeboden rit d.m.v ritnr
        int lidnreigenaar = haalLidNr(bean.getRitnr());


        boolean more = false;
        try {
            currentCon = ConnectionManager.getConnection();

            String queryString = "INSERT INTO berichten  (inhoudbericht,datum,afzender,lidnr,gelezen,ritnr)"
                    + "VALUES (?,?,?,?,?,?)";
            statement = currentCon.prepareStatement(queryString);
            statement.setString(1, bean.getInhoud());
            statement.setTimestamp(2, bean.getDatum());
            statement.setInt(3, bean.getLidnr());
            statement.setInt(4, lidnreigenaar);
            statement.setInt(5, 1);
            statement.setInt(6, bean.getRitnr());
            statement.executeUpdate();



            more = true;

        } catch (SQLException e) {


            System.out.println(e);
        } finally {

            currentCon.close();
            statement.close();
            resultSet.close();
        }

        return more;
    }

    //beantwoord bericht
    public boolean beantwoordBericht(Berichten bean) throws SQLException {




        boolean more = false;
        try {
            currentCon = ConnectionManager.getConnection();

            String queryString = "INSERT INTO berichten  (inhoudbericht,datum,afzender,lidnr,gelezen,ritnr)"
                    + "VALUES (?,?,?,?,?,?)";
            statement = currentCon.prepareStatement(queryString);
            statement.setString(1, bean.getInhoud());
            statement.setTimestamp(2, bean.getDatum());
            statement.setInt(3, bean.getAfzender());
            statement.setInt(4, bean.getLidnr());
            statement.setInt(5, 1);
            statement.setInt(6, bean.getRitnr());
            resultSet = statement.executeQuery();


            more = true;

        } catch (SQLException e) {


            System.out.println(e);
        } finally {

            currentCon.close();
            statement.close();
            resultSet.close();
        }

        return more;
    }

    //hiermee vullen we de inbox
    public List<Berichten> haalberichten(int lidnummer, int offset) throws SQLException {



        List<Berichten> berichten = new ArrayList<Berichten>();

        DatumConverter dc = new DatumConverter();

        try {
            System.out.println("Lidnummer:" + lidnummer);
            currentCon = ConnectionManager.getConnection();

            String queryString = "SELECT * FROM berichten WHERE lidnr=? ORDER BY datum DESC LIMIT 5 OFFSET ?;";

            //String queryString = "select berichtnr, DATUM ,ritnm,gelezen from BERICHTEN  where lidnr=?;";

            statement = currentCon.prepareStatement(queryString);
            statement.setInt(1, lidnummer);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Berichten bericht = new Berichten();
                bericht.setBerichtid(resultSet.getInt("berichtnr"));
                bericht.setAfzender(resultSet.getInt("afzender"));

                bericht.setDatum(resultSet.getTimestamp("datum"));
                bericht.setStringTijd(dc.korteTijd(bericht.getDatum()));
                bericht.setStringDatum(dc.korteDatum(bericht.getDatum()));
                bericht.setRitnr(resultSet.getInt("ritnr"));
                bericht.setOngelezen(resultSet.getInt("gelezen"));


                berichten.add(bericht);
            }

        } catch (SQLException e) {
            System.out.println("Functie haalberichten() en foutmelding: " + e);

        } finally {
            currentCon.close();
            statement.close();
            resultSet.close();

        }

        return berichten;
    }

    public Lid afzender(int afzenderLidnr) {

        Lid afzenderLid = new Lid();
        LidDao lidDao = new LidDao();

        afzenderLid = lidDao.enkelLidOphalen(afzenderLidnr, afzenderLid);
        System.out.println("afzenderlid na het ophalen van gegevens = " + afzenderLid.getLidnr());
        System.out.println("Afzenderlid na het ophalen van gegevens = " + afzenderLid.getAnaam());

        return afzenderLid;

    }

    public Berichten vulBerichtDao(Berichten bean) {

        lidnr = bean.getLidnr();
        //onderwerp=bean.getOnderwerp();
        datum = bean.getDatum();
        inhoud = bean.getInhoud();
        ritnr = bean.getRitnr();


        return bean;

    }

    public List<Berichten> getAlleBerichtenbijId(int berichtid) throws SQLException {



        List<Berichten> berichtlijst = new ArrayList<Berichten>();
        DatumConverter dc = new DatumConverter();
        try {

            currentCon = ConnectionManager.getConnection();

            String queryString = "SELECT  berichtnr, INHOUDBERICHT, DATUM, Ritnr, lidnr, afzender FROM berichten WHERE berichtnr =?;";

            statement = currentCon.prepareStatement(queryString);
            statement.setInt(1, berichtid);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Berichten bericht = new Berichten();
                bericht.setBerichtid(resultSet.getInt(1));
                bericht.setInhoud(resultSet.getString(2));
                bericht.setDatum(resultSet.getTimestamp(3));
                bericht.setStringDatum(dc.korteDatum(bericht.getDatum()));
                bericht.setStringTijd(dc.korteTijd(bericht.getDatum()));

                bericht.setRitnr(resultSet.getInt(4));
                bericht.setLidnr(resultSet.getInt(5));
                bericht.setAfzender(resultSet.getInt(6));
                berichtlijst.add(bericht);
            }
        } finally {
            currentCon.close();
            statement.close();
            resultSet.close();
        }
        return berichtlijst;

    }

    public int statusbalk(int lidnummer) throws SQLException {




        currentCon = ConnectionManager.getConnection();
        int aantal = 0;

        try {

            statement = currentCon.prepareStatement("SELECT  gelezen FROM berichten WHERE lidnr =? and gelezen=1");

            statement.setInt(1, lidnummer);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                resultSet.getInt(1);
                aantal++;

            }




        } catch (SQLException e) {


            System.out.println("Error in Functie statusbalk: " + e);

        } finally {
            currentCon.close();
            statement.close();
            resultSet.close();
        }

        return aantal;



    }
}
