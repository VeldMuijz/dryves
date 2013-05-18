/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import Dryves.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author H
 */
public class BerichtenDao {

    static Connection currentCon;
    static ResultSet rs;

    //achterhaal lid nr d.m.v berichtid
    public int haalLidNr(int ritnr) {


        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int lidnr = 0;

        try {

            currentCon = ConnectionManager.getConnection();
            PreparedStatement haallidnr;
            String queryString = "SELECT lidnr FROM rit WHERE ritnr=?;";

            haallidnr = currentCon.prepareStatement(queryString);
            haallidnr.setInt(1, ritnr);
            resultSet = haallidnr.executeQuery();

            while (resultSet.next()) {

                lidnr = resultSet.getInt(1);
                System.out.println("Lidnummer: " + lidnr);
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("output van haallidnr: " + lidnr);
        return lidnr;


    }

    //min 1 bij ongelezen berichten
    public void markeerBericht(int berichtid) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            currentCon = ConnectionManager.getConnection();
            PreparedStatement selecteerbericht;
            String queryString = "UPDATE berichten set gelezen=0 WHERE berichtnr =?;";

            selecteerbericht = currentCon.prepareStatement(queryString);
            selecteerbericht.setInt(1, berichtid);

        } catch (SQLException e) {
            System.out.println(e);
        }



    }
    /////verstuur bericht 

    public boolean BerichtVersturen(Berichten bean) throws SQLException {

        //Met deze functie komen we erachter wie
        //de eigenaar is van de aangeboden rit d.m.v ritnr
        int lidnreigenaar = haalLidNr(bean.getRitnr());

        ResultSet resultSet = null;
        boolean more = false;
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement verstuurbericht;
            String queryString = "INSERT INTO berichten  (inhoudbericht,datum,afzender,lidnr,gelezen,ritnm)"
                    + "VALUES (?,?,?,?,?,?)";
            verstuurbericht = currentCon.prepareStatement(queryString);
            verstuurbericht.setString(1, bean.getInhoud());
            verstuurbericht.setString(2, bean.getDatum());
            verstuurbericht.setInt(3, bean.getLidnr());
            verstuurbericht.setInt(4, lidnreigenaar);
            verstuurbericht.setInt(5, 1);
            verstuurbericht.setInt(6, bean.getRitnr());
            resultSet = verstuurbericht.executeQuery();


            more = true;

        } catch (SQLException e) {


            System.out.println(e);
        }

        return more;
    }

    //beantwoord bericht
    public boolean beantwoordBericht(Berichten bean) throws SQLException {



        ResultSet resultSet = null;
        boolean more = false;
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement verstuurbericht;
            String queryString = "INSERT INTO berichten  (inhoudbericht,datum,afzender,lidnr,gelezen,ritnm)"
                    + "VALUES (?,?,?,?,?,?)";
            verstuurbericht = currentCon.prepareStatement(queryString);
            verstuurbericht.setString(1, bean.getInhoud());
            verstuurbericht.setString(2, bean.getDatum());
            verstuurbericht.setInt(3, bean.getAfzender());
            verstuurbericht.setInt(4, bean.getLidnr());
            verstuurbericht.setInt(5, 1);
            verstuurbericht.setInt(6, bean.getRitnr());
            resultSet = verstuurbericht.executeQuery();


            more = true;

        } catch (SQLException e) {


            System.out.println(e);
        }

        return more;
    }

    //hiermee vullen we de inbox
    public List<Berichten> haalberichten(int lidnummer) throws SQLException {




        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Berichten> berichten = new ArrayList<Berichten>();

        try {
            System.out.println("Lidnummer:" + lidnummer);
            currentCon = ConnectionManager.getConnection();
            PreparedStatement HaalAlleBerichten;
            String queryString = "select berichtnr,  DATUM ,ritnm from BERICHTEN  where lidnr=?;";

            HaalAlleBerichten = currentCon.prepareStatement(queryString);
            HaalAlleBerichten.setInt(1, lidnummer);
            resultSet = HaalAlleBerichten.executeQuery();

            while (resultSet.next()) {
                Berichten bericht = new Berichten();
                bericht.setBerichtid(resultSet.getInt(1));

                bericht.setDatum(resultSet.getString(2));
                bericht.setRitnr(resultSet.getInt(3));


                berichten.add(bericht);
            }
        } finally {
        }
        return berichten;


    }
    private int lidnr;
    private String onderwerp;
    private String datum;
    private String inhoud;
    private int ritnr;

    public Berichten vulBerichtDao(Berichten bean) {

        lidnr = bean.getLidnr();
        //onderwerp=bean.getOnderwerp();
        datum = bean.getDatum();
        inhoud = bean.getInhoud();
        ritnr = bean.getRitnr();


        return bean;

    }

    public List<Berichten> getAlleBerichtenbijId(int berichtid) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Berichten> berichtlijst = new ArrayList<Berichten>();

        try {

            currentCon = ConnectionManager.getConnection();
            PreparedStatement selecteerbericht;
            String queryString = "SELECT  berichtnr, INHOUDBERICHT, DATUM, Ritnm, lidnr, afzender FROM berichten WHERE berichtnr =?;";

            selecteerbericht = currentCon.prepareStatement(queryString);
            selecteerbericht.setInt(1, berichtid);
            resultSet = selecteerbericht.executeQuery();

            while (resultSet.next()) {
                Berichten bericht = new Berichten();
                bericht.setBerichtid(resultSet.getInt(1));
                bericht.setInhoud(resultSet.getString(2));
                bericht.setDatum(resultSet.getString(3));

                bericht.setRitnr(resultSet.getInt(4));
                bericht.setLidnr(resultSet.getInt(5));
                bericht.setAfzender(resultSet.getInt(6));
                berichtlijst.add(bericht);
            }
        } finally {
        }
        return berichtlijst;

    }

    public int statusbalk(int lidnummer) {



        Connection con = Dryves.ConnectionManager.getConnection();

        int aantal = 0;

        try {

            PreparedStatement pstmt = con.prepareStatement("SELECT  gelezen FROM berichten WHERE lidnr =? and gelezen=1");

            pstmt.setInt(1, lidnummer);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                rs.getInt(1);
                aantal++;

            }
            rs.close();
            pstmt.close();


        } catch (SQLException e) {


            System.out.println(e);
        }

        return aantal;



    }
}
