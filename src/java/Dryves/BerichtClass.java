/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class BerichtClass {

    private String onderwerp;
    private String datum;
    private String inhoud;
    private int gelezen;
    private int idbericht;

    public int getIdbericht() {
        return idbericht;
    }

    public void setIdbericht(int idbericht) {
        this.idbericht = idbericht;
    }

    public int getGelezen() {
        return gelezen;
    }

    public void setGelezen(int gelezen) {
        this.gelezen = gelezen;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
    
    //Met deze functie halen we de rit gegevens op via ritid zodat we weten wie de eigenaar
    //public void 
    
    
    
    

    // Hiermee halen we lidnummers om een bericht te versturen naar een lid die aankopen heeft gedaan
    public int lidnrNAAR(int lidnr) {

        Connection con = Dryves.ConnectionManager.getConnection();
        try {


            ResultSet rs;
            PreparedStatement pstmt = con.prepareStatement("SELECT lidnr FROM aankoop WHERE IDBERICHT =?");
            // Create a PreparedStatement object    1 
            pstmt.setInt(1, lidnr);      // Assign value to input parameter      2 
//hier 
            rs = pstmt.executeQuery();
            idbericht = lidnr;
            while (rs.next()) {



                inhoud = rs.getString(2);

            }
            rs.close();                       // Close the ResultSet                  5 
            pstmt.close();


        } catch (SQLException e) {


            System.out.println(e);
        }

        return 50;


    }

    public boolean verstuurbericht(int naar, String onderwerp, String inhoud, String datum, int afzender) {
        ResultSet rs;
        boolean more;
        Connection con = Dryves.ConnectionManager.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(" insert into berichten values(?,?,?,?,?,?,1,'mijnmail.com','test') ");

            pstmt.setInt(1, naar);
            pstmt.setString(2, onderwerp);
            pstmt.setString(3, inhoud);
            pstmt.setString(4, datum);
            pstmt.setInt(5, afzender);
            more = true;

            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
            more = false;
        } finally {
// hier laten closen
        }

        return more;




    }

    public void gelezenofniet(double lees) {

        // hier halen 
        int vergelijk = (int) lees;


        Connection con = Dryves.ConnectionManager.getConnection();
        BerichtClass b = new BerichtClass();
        try {
            ResultSet rs;
            PreparedStatement pstmt = null;
            pstmt = con.prepareStatement("SELECT  gelezen FROM berichten WHERE IDBERICHT =?");
            pstmt.setInt(1, vergelijk);      // Assign value to input parameter      2 
            rs = pstmt.executeQuery();        // Get the result table from the query  3 

            while (rs.next()) {



                vergelijk = rs.getInt(1);
            }

            if (vergelijk == 1) {


                try {
                    ResultSet rs2;
                    PreparedStatement pstmt2 = con.prepareStatement(" UPDATE berichten SET gelezen=0 where idbericht=? ");
                    pstmt2.setInt(1, vergelijk);
                    rs = pstmt2.executeQuery();
                } catch (SQLException e) {
                    System.out.println(e);
                }

                rs.close();                       // Close the ResultSet                  5 
                pstmt.close();


            }

            //hier begint het echte werk 

            /////


        } catch (SQLException e) {
        }
    }

    // functie fu=haalinbox 
    public BerichtClass Haalinbox(double INX) {

        int berichtid = (int) INX;

        Connection con = Dryves.ConnectionManager.getConnection();
        BerichtClass b = new BerichtClass();

        try {
            ResultSet rs;
            PreparedStatement pstmt = con.prepareStatement("SELECT  IDBERICHT, INHOUDBERICHT, DATUM, ONDERWERP, lidnr FROM berichten WHERE IDBERICHT =?");
            // Create a PreparedStatement object    1 
            pstmt.setInt(1, 1);      // Assign value to input parameter      2 


            rs = pstmt.executeQuery();        // Get the result table from the query  3 

            while (rs.next()) {



                b.inhoud = rs.getString(2);
                // Retrieve the first column value
                b.datum = rs.getString(3);
                b.onderwerp = rs.getString(4);

            }
            rs.close();                       // Close the ResultSet                  5 
            pstmt.close();


        } catch (SQLException e) {


            System.out.println(e);
        }

        return b;

    }

    public int opnieuw(double INX) {



        int ddd = (int) INX;
        Connection con = Dryves.ConnectionManager.getConnection();
        BerichtClass b = new BerichtClass();




        try {
            ResultSet rs;
            PreparedStatement pstmt = con.prepareStatement("SELECT  * FROM berichten WHERE IDBERICHT =?");
            // Create a PreparedStatement object    1 
            pstmt.setInt(1, ddd);      // Assign value to input parameter      2 
//hier 

            rs = pstmt.executeQuery();        // Get the result table from the query  3 

            while (rs.next()) {



                b.gelezen = rs.getInt("gelezen");
                // Retrieve the first column value

            }
            rs.close();                       // Close the ResultSet                  5 
            pstmt.close();


        } catch (SQLException e) {


            System.out.println(e);
        }

        return b.getGelezen();
    }

    //Indien bericht gelezen dan -1
    public void checkgelezen(double doubleidberivht) {



        int idbericht = (int) doubleidberivht;

        Connection con = Dryves.ConnectionManager.getConnection();
        BerichtClass b = new BerichtClass();


        try {
            ResultSet rs;
            PreparedStatement pstmt = con.prepareStatement("SELECT  gelezen FROM berichten WHERE IDBERICHT =?");

            pstmt.setInt(1, idbericht);
            rs = pstmt.executeQuery();

            while (rs.next()) {


                b.gelezen = rs.getInt(1);

            }
            rs.close();
            pstmt.close();


        } catch (SQLException e) {


            System.out.println(e);
        }

        Connection con2 = Dryves.ConnectionManager.getConnection();
        PreparedStatement preparedStatement2 = null;


        if (b.gelezen == 1) {


            try {

                preparedStatement2 = con.prepareStatement("UPDATE berichten SET gelezen=0 where idbericht= ?");
                preparedStatement2.setInt(1, idbericht);

                // execute update SQL stetement
                preparedStatement2.executeUpdate();

                System.out.println("Record is updated to DBUSER table!");

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }
        }
    }

    // nu de status geeft het aantal ongelezen emails
    public int statusbalk(int lidnummer) {



        Connection con = Dryves.ConnectionManager.getConnection();
        BerichtClass b = new BerichtClass();

        int aantal = 0;


        try {
            ResultSet rs;
            PreparedStatement pstmt = con.prepareStatement("SELECT  gelezen FROM berichten WHERE lidnr =? and gelezen=1");

            pstmt.setInt(1, lidnummer);


            rs = pstmt.executeQuery();
            b.idbericht = lidnummer;


            while (rs.next()) {

                rs.getInt(1);
                aantal++;

            }
            rs.close();                       // Close the ResultSet                  5 
            pstmt.close();


        } catch (SQLException e) {


            System.out.println(e);
        }

        return aantal;





    }
}
