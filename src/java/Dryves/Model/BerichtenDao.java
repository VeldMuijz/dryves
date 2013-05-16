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
 
    
    
    
    
    
    /////ver stuurbericht
    
    public void BerichtVersturen(int ritid)throws SQLException {
    
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Berichten> berichten =  new ArrayList<Berichten>();
        int lidnr;
        try {
           System.out.println("Rit id: "+ ritid);
            currentCon = ConnectionManager.getConnection();
            PreparedStatement haalLidnr;
            String queryString = "SELECT lidnr FROM rit WHERE ritnr=?;";
                 
            haalLidnr = currentCon.prepareStatement(queryString);
            haalLidnr.setInt(1, ritid);
            resultSet = haalLidnr.executeQuery();
         
            while (resultSet.next()) {
                              
                lidnr=resultSet.getInt(1);
                                         
            }
        }catch (SQLException e) {


            System.out.println(e);}
        
        
        
      
        
        
            
        
    
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
    
    
    private int lidnr;
    private String onderwerp;
    private String datum;
    private String inhoud;
    private int ritnr;
    
    
    public Berichten vulBerichtDao(Berichten bean){
    
    lidnr=bean.getLidnr();
    onderwerp=bean.getOnderwerp();
    datum=bean.getDatum();
    inhoud=bean.getInhoud();
    ritnr=bean.getRitnr();
        
        
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
            String queryString = "SELECT  IDBERICHT, INHOUDBERICHT, DATUM, ONDERWERP, Ritnm FROM berichten WHERE IDBERICHT =?;";
            
            selecteerbericht = currentCon.prepareStatement(queryString);
            selecteerbericht.setInt(1, berichtid);
            resultSet = selecteerbericht.executeQuery();
           
            while (resultSet.next()) {
                Berichten bericht= new Berichten();
                bericht.setBerichtid(resultSet.getInt(1));
                bericht.setInhoud(resultSet.getString(2));
                bericht.setDatum(resultSet.getString(3));
                bericht.setOnderwerp(resultSet.getString(4));
                bericht.setRitnr(resultSet.getInt(5));
                
                berichtlijst.add(bericht);
            }
        }
            finally {

        }
        return berichtlijst;
        
    }

}


