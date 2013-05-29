/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import Dryves.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RickSpijker
 */
public class AdmindryvesDao {

	private String achtergrond;
	private String ritprijs;
	static Connection currentCon;

	/**
	 * Vul de gegevens die nodig zijn in de klasse AdmindryvesDao
	 * @param bean
	 * @return 
	 */
	public Lid vulAdd(Lid bean) {

		achtergrond = bean.getAchtergrond();
		ritprijs = bean.getRitprijs();


		return bean;

	}

	/**
	 * Update de instellingen in de configuratie tabel
	 * @return 
	 */
	public Boolean AdmindryvesDao() {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement updateAdmin = null;
			String query = "update configuratie set \"Achtergrond\" = ?, \"Ritprijs\" = ? where confnr = 1";

		try {

			updateAdmin = currentCon.prepareStatement(query);
			updateAdmin.setString(1, achtergrond);
			updateAdmin.setString(2, ritprijs);
			
			System.out.println("+++++++++++++Update configuratie++++++++++++++++\n Query = " + updateAdmin);
			updateAdmin.executeUpdate();


		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			}
			if (updateAdmin != null) {
				//sluit preparedStatement
				try {
					updateAdmin.close();
				} catch (SQLException ignore) {
				}
			}
			if (currentCon != null) {
				//sluit huidige verbinding
				try {
					currentCon.close();
				} catch (SQLException ignore) {
				}
			}

	return true;
	}

}
