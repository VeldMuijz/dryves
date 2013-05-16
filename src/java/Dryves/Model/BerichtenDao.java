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
 
    
    
    
    
    
    /////
    
     //hiermee vullen we de inbox
    public List<Berichten> haalHetGeheleBericht2(int berichtid)throws SQLException {
    
    
                  
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Berichten> berichten =  new ArrayList<Berichten>();
        
        try {
           System.out.println("Berichtid:"+berichtid);
            currentCon = ConnectionManager.getConnection();
            PreparedStatement HaalAlleBerichten;
            String queryString = "SELECT  IDBERICHT, INHOUDBERICHT, DATUM, ONDERWERP,inhoudbericht lidnr FROM berichten WHERE IDBERICHT =?;";
                 
            HaalAlleBerichten = currentCon.prepareStatement(queryString);
            HaalAlleBerichten.setInt(1, berichtid);
            resultSet = HaalAlleBerichten.executeQuery();
           
            while (resultSet.next()) {
                Berichten bericht= new Berichten();
                bericht.setBerichtid(resultSet.getInt(1));
                bericht.setOnderwerp(resultSet.getString(2));
                bericht.setDatum(resultSet.getString(3));
                bericht.setInhoud(resultSet.getString(4));
                
 
                berichten.add(bericht);
            }
        }
            finally {
            
            System.out.println("aantal waarden in lijst berichten"+berichten.size());

        }
        return berichten;
        
    
    }
    
    
    
    
    /////
    
    
    // met deze functie halen we een enkel bericht op
    public List<Berichten> haalHetGeheleBericht(int berichtid) {
       
        Berichten berichtclas= new Berichten();
        currentCon  = Dryves.ConnectionManager.getConnection();
          List<Berichten> berichten =  new ArrayList<Berichten>();

        try {
            
            PreparedStatement pstmt = currentCon.prepareStatement("SELECT  IDBERICHT, INHOUDBERICHT, DATUM, ONDERWERP, lidnr FROM berichten WHERE IDBERICHT =?");
           
            pstmt.setInt(1, berichtid);      

            rs = pstmt.executeQuery();

            while (rs.next()) {


                berichtclas.setBerichtid(rs.getInt("IDBERICHT"));
                berichtclas.setInhoud(rs.getString("INHOUDBERICHT"));
                berichtclas.setDatum(rs.getString("DATUM"));
                berichtclas.setOnderwerp(rs.getString("ONDERWERP"));
                berichten.add(berichtclas);
                System.out.println("ID BERICHT :"+rs.getString("IDBERICHT"));
                System.out.println("Inhoud bericht:"+rs.getString("INHOUDBERICHT"));
                System.out.println("Datum bericht:"+rs.getString("DATUM"));
                System.out.println("Onderwerp bericht:"+rs.getString("ONDERWERP"));

            }
            rs.close();                      
            pstmt.close();


        } catch (SQLException e) {


            System.out.println(e);
        }

        return berichten;

    }
    
    
    
   
    //hiermee vullen we de inbox
    public List<Berichten> haalberichten(int lidnummer)throws SQLException {
    
    
                  
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Berichten> berichten =  new ArrayList<Berichten>();
        
        try {
           System.out.println("Lidnummer:"+lidnummer);
            currentCon = ConnectionManager.getConnection();
            PreparedStatement HaalAlleBerichten;
            String queryString = "select IDBERICHT, ONDERWERP, DATUM  from BERICHTEN  where lidnr=?;";
                 
            HaalAlleBerichten = currentCon.prepareStatement(queryString);
            HaalAlleBerichten.setInt(1, lidnummer);
            resultSet = HaalAlleBerichten.executeQuery();
           
            while (resultSet.next()) {
                Berichten bericht= new Berichten();
                bericht.setBerichtid(resultSet.getInt(1));
                bericht.setOnderwerp(resultSet.getString(2));
                bericht.setDatum(resultSet.getString(3));
                
 
                berichten.add(bericht);
            }
        }
            finally {

        }
        return berichten;
        
    
    }
    
}
