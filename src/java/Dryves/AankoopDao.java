/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import static Dryves.RitDao.currentCon;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroen
 */
public class AankoopDao {

	private int ritnr;
	private int lidnr;
	private int ontmoetingnr;
	private String betaalwijze;
	private Timestamp datum;
	private int factuurnr;

	/**
	 * Ophalen van alle gegevens uit de servlet voor de rit_plannen.jsp
	 *
	 * @param bean
	 * @return bean (rit object)
	 */
	public Aankoop vulAankoopDao(Aankoop bean) {
		Date date = new Date();
		lidnr = bean.getLidnr();
		ritnr = bean.getRitnr();
		ontmoetingnr = bean.getOntmoetingnr();
		betaalwijze = bean.getBetaalwijze();
		datum = bean.getDatum();
		factuurnr = bean.getFactuurnr();

		return bean;

	}

	public Boolean aankoopDoen() {

		
		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement aankoop;

			String queryString = ("INSERT INTO aankoop ("
					+ " ritnr,"
					+ " lidnr,"
					+ " ontmoetingnr,"
					+ " betaalwijze,"
					+ " datum,"
					+ " factuurnr)"
					+ "VALUES(?,?,?,?,?,?);");
			
			aankoop = currentCon.prepareStatement(queryString);

			aankoop.setInt(1, ritnr);
			aankoop.setInt(2, lidnr);
			aankoop.setInt(3, ontmoetingnr);
			aankoop.setString(4, betaalwijze);
			aankoop.setTimestamp(5, datum);
			aankoop.setInt(6, factuurnr);


			System.out.println("De query is: " + aankoop);

			aankoop.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		return true;

	}
}
