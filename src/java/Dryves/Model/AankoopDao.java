/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import Dryves.ConnectionManager;
import Dryves.DatumConverter;
import static Dryves.Model.RitDao.currentCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroen
 */
public class AankoopDao {

	private int aankoopnr;
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
		aankoopnr = bean.getAankoopnr();
		lidnr = bean.getLidnr();
		ritnr = bean.getRitnr();
		ontmoetingnr = bean.getOntmoetingnr();
		betaalwijze = bean.getBetaalwijze();
		datum = bean.getDatum();
		factuurnr = bean.getFactuurnr();

		return bean;

	}

	/**
	 * Checkt of een aankoop voor een lid bestaat
	 * @param aankoopnr aankoopnr in de database
	 * @param lidnr lidnummer in de database
	 * @return geeft true terug wanneer er een aankoop gevonden is, geeft false terug wanneer er niets gevonden is
	 */
	public Boolean checkBestaanAankoop(int aankoopnr, int lidnr) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Aankoop> aankooplijst = new ArrayList<Aankoop>();
		List<Rit> aankoopritten = new ArrayList<Rit>();
		Boolean check = false;


		try {

			currentCon = ConnectionManager.getConnection();
			PreparedStatement aankopen;
			String queryString = "SELECT aankoopnr, lidnr "
					+ "FROM aankoop AS a "
					+ "WHERE a.aankoopnr = ? AND a.lidnr = ? LIMIT 1;";

			aankopen = currentCon.prepareStatement(queryString);
			aankopen.setInt(1, aankoopnr);
			aankopen.setInt(2, lidnr);
			System.out.println("De query is: " + aankopen);
			resultSet = aankopen.executeQuery();
			
			
			if(resultSet.next()){
				System.out.println("resultSet = " + resultSet.getInt("aankoopnr"));
				System.out.println("resultSet = leeg dus gebruiker mag niet beoordelen");
				check = true;
			}
			
			
		} catch (SQLException ex) {
			Logger.getLogger(AankoopDao.class.getName()).log(Level.SEVERE, null, ex);

		}
		
		return check;

	}

	/**
	 *
	 * Haal een lijst van aankopen per lid op
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<Aankoop> getAlleAankopenPerLid(int lidnr) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Aankoop> aankooplijst = new ArrayList<Aankoop>();
		List<Rit> aankoopritten = new ArrayList<Rit>();
		DatumConverter dc = new DatumConverter();


		try {

			currentCon = ConnectionManager.getConnection();
			PreparedStatement aankopen;
			String queryString = "SELECT a.* "
					+ "FROM rit AS r, aankoop AS a "
					+ "WHERE r.ritnr = a.ritnr AND a.lidnr = ?;";

			aankopen = currentCon.prepareStatement(queryString);
			aankopen.setInt(1, lidnr);
			resultSet = aankopen.executeQuery();

			while (resultSet.next()) {
				Aankoop aankoop = new Aankoop();
				aankoop.setRitnr(resultSet.getInt("ritnr"));
				aankoop.setAankoopnr(resultSet.getInt("aankoopnr"));
				aankoop.setStringDatum(dc.korteDatum(resultSet.getTimestamp("datum")));
				aankoop.setStringTijd(dc.korteTijd(resultSet.getTimestamp("datum")));
				aankoop.setDatum(resultSet.getTimestamp("datum"));
				aankoop.setLidnr(resultSet.getInt("lidnr"));

				aankooplijst.add(aankoop);

			}
		} catch (SQLException ex) {
			Logger.getLogger(AankoopDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
// if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
// if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
// if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
// }
		}
		return aankooplijst;

	}

	/**
	 *
	 * @return
	 */
	public Boolean aankoopDoen() {
		RitDao ritDao = new RitDao();

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
			ritDao.updateZitplaatsOphogen(ritnr, 1);
			return false;
		}
		return true;

	}
}
