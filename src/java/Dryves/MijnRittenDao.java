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

        try {
            currentCon = ConnectionManager.getConnection();
            statement = connection.prepareStatement("SELECT * FROM rit WHERE lidnr = lidnr");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Rit rit = new Rit();
                rit.setRitnr(resultSet.getLong("ritnr"));
                rit.setStartpunt(resultSet.getString("startpunt"));
                rit.setEindpunt(resultSet.getString("eindpunt"));
                rit.setPrijs(resultSet.getDouble("prijs"));
                ritten.add(rit);
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return ritten;
    }

    
}
