/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import static Dryves.RitDao.currentCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent
 */
public class MijnRittenDao {
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public  List<Rit> getAlleRittenPerLid() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Rit> ritten = new ArrayList<Rit>();
        System.out.println("DIT IS EEN TESET************************");
        try {
           
            currentCon = ConnectionManager.getConnection();
            PreparedStatement zoekritten;
            String queryString = "SELECT * FROM Rit WHERE lidnr = ;";
            
            zoekritten = currentCon.prepareStatement(queryString);
            resultSet = zoekritten.executeQuery();

             //System.out.println("Uitvoeren van query: \n\n\n " + queryString);
            
            while (resultSet.next()) {
                Rit rit = new Rit();
                rit.setRitnr(resultSet.getInt("ritnr"));
                rit.setStartpunt(resultSet.getString("startpunt"));
                rit.setEindpunt(resultSet.getString("eindpunt"));
                rit.setPrijs(resultSet.getDouble("prijs"));
                ritten.add(rit);
            }
//            System.out.println(resultSet.getInt("ritnr"));
        }
            finally {
//            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
//            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
//            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
//        }
        }
        return ritten;
        
    }

    
}
