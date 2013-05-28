
package Dryves.Model;

import Dryves.ConnectionManager;
import Dryves.DatumConverter;
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
 * Deze klasse bevat alle functies en waarden voor het bewerken van de aankooptabel
 *
 * @author jeroen
 */
public class AankoopDao {

	static Connection currentCon;
	static ResultSet rs;
	private int aankoopnr;
	private int ritnr;
	private int lidnr;
	private int ontmoetingnr;
	private String betaalwijze;
	private Timestamp datum;
	private int factuurnr;

	/**
	 * Ophalen van alle gegevens uit de servlet voor de AankoopDao klasse
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
	 *
	 * @param aankoopnr aankoopnr in de database
	 * @param lidnr lidnummer in de database
	 * @return geeft true terug wanneer er een aankoop gevonden is, geeft false
	 * terug wanneer er niets gevonden is
	 */
	public Boolean checkBestaanAankoop(int aankoopnr, int lidnr) {
		rs = null;
		currentCon = ConnectionManager.getConnection();
		List<Aankoop> aankooplijst = new ArrayList<Aankoop>();
		List<Rit> aankoopritten = new ArrayList<Rit>();
		Boolean check = false;
		PreparedStatement aankopen = null;
		String queryString = "SELECT aankoopnr, lidnr "
				+ "FROM aankoop AS a "
				+ "WHERE a.aankoopnr = ? AND a.lidnr = ? LIMIT 1;";


		try {
			aankopen = currentCon.prepareStatement(queryString);
			aankopen.setInt(1, aankoopnr);
			aankopen.setInt(2, lidnr);
			System.out.println("De query is: " + aankopen);
			rs = aankopen.executeQuery();


			if (rs.next()) {
				System.out.println("resultSet = " + rs.getInt("aankoopnr"));
				check = true;
			}


		} catch (SQLException ex) {
			Logger.getLogger(AankoopDao.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (aankopen != null) {
				try {
					aankopen.close();
				} catch (SQLException ignore) {
				}
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (SQLException ignore) {
				}
			}
		}

		return check;

	}

	/**
	 * Update de waarde beoordeeld uit de tabel aankoop, deze waarde bevat een 1 of een 0.
	 * 1 staat voor beoordeeld, 0 staat voor nog niet beoordeeld.
	 * @param aankoopnr
	 * @return 
	 */
	public Boolean setBeoordeeld(int aankoopnr) {
		rs = null;
		currentCon = ConnectionManager.getConnection();
		String queryString = "UPDATE aankoop SET beoordeeld = 1 WHERE aankoopnr = ? AND beoordeeld < 1;";
		PreparedStatement setBeoordeeld = null;
		
		try {
			setBeoordeeld = currentCon.prepareStatement(queryString);

			setBeoordeeld.setInt(1, aankoopnr);
			System.out.println("setBeoordeeld query = " + setBeoordeeld);
			setBeoordeeld.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(AankoopDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (setBeoordeeld != null) {
				try {
					setBeoordeeld.close();
				} catch (SQLException ignore) {
				}
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (SQLException ignore) {
				}
			}
		}

		return true;
	}

	/**
	 *
	 * Haal een lijst van aankopen per lid op en geef deze terug in een List van het object Aankoop
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<Aankoop> getAlleAankopenPerLid(int lidnr) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Aankoop> aankooplijst = new ArrayList<Aankoop>();
		List<Rit> aankoopritten = new ArrayList<Rit>();
		DatumConverter dc = new DatumConverter();
		currentCon = ConnectionManager.getConnection();
		PreparedStatement aankopen = null;
		String queryString = "SELECT a.* "
				+ "FROM rit AS r, aankoop AS a "
				+ "WHERE r.ritnr = a.ritnr AND a.lidnr = ?;";

		try {

			aankopen = currentCon.prepareStatement(queryString);
			aankopen.setInt(1, lidnr);
			rs = aankopen.executeQuery();

			while (rs.next()) {
				Aankoop aankoop = new Aankoop();
				aankoop.setRitnr(rs.getInt("ritnr"));
				aankoop.setAankoopnr(rs.getInt("aankoopnr"));
				aankoop.setStringDatum(dc.korteDatum(rs.getTimestamp("datum")));
				aankoop.setStringTijd(dc.korteTijd(rs.getTimestamp("datum")));
				aankoop.setDatum(rs.getTimestamp("datum"));
				aankoop.setLidnr(rs.getInt("lidnr"));
				aankoop.setBeoordeeld(rs.getInt("beoordeeld"));

				aankooplijst.add(aankoop);

			}
		} catch (SQLException ex) {
			Logger.getLogger(AankoopDao.class
					.getName()).log(Level.SEVERE, null, ex);
		} finally {
			//Doe dit altijd, als het goed gaat én wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (aankopen != null) {
				//sluit preparedStatement
				try {
					aankopen.close();
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
		}

		return aankooplijst;

	}

	/**
	 * Maak een aankoop aan voor een lid
	 *
	 * @return
	 */
	public Boolean aankoopDoen() {
		RitDao ritDao = new RitDao();
		currentCon = ConnectionManager.getConnection();
		PreparedStatement aankoop = null;

		String queryString = ("INSERT INTO aankoop ("
				+ " ritnr,"
				+ " lidnr,"
				+ " ontmoetingnr,"
				+ " betaalwijze,"
				+ " datum,"
				+ " factuurnr)"
				+ "VALUES(?,?,?,?,?,?);");

		try {
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
			Logger.getLogger(RitDao.class
					.getName()).log(Level.SEVERE, null, ex);
			ritDao.updateZitplaatsOphogen(ritnr,
					1);

			return false;
		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (aankoop != null) {
				//sluit preparedStatement
				try {
					aankoop.close();
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
		}
		return true;
	}
}
