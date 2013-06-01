/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author jeroen
 */
public class BeoordelingDao {

	static Connection currentCon;
	static ResultSet rs;
	private int beoordelingnr;
	private int waardering;
	private int stiptheid;
	private int rijstijl;
	private int gezelligheid;
	private int betrouwbaarheid;
	private String commentaar;
	private int lidnr;
	private int aankoopnr;
	private Timestamp datum;

	public Beoordeling vulBeoordeling(Beoordeling bean) {
		beoordelingnr = bean.getBeoordelingnr();
		waardering = bean.getWaardering();
		stiptheid = bean.getStiptheid();
		rijstijl = bean.getRijstijl();
		gezelligheid = bean.getGezelligheid();
		betrouwbaarheid = bean.getBetrouwbaarheid();
		commentaar = bean.getCommentaar();
		lidnr = bean.getLidnr();
		aankoopnr = bean.getAankoopnr();
		datum = bean.getDatum();

		return bean;
	}

	/**
	 * Haal een lijst van beoordelingen per lid op
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<Beoordeling> getAlleBeoordelingenPerLid(int lidnr) throws SQLException {
		rs = null;
		List<Beoordeling> beoordelingen = new ArrayList<Beoordeling>();
		DatumConverter dc = new DatumConverter();
		currentCon = ConnectionManager.getConnection();
		PreparedStatement getBeoordelingen = null;
		String queryString = ""
				+ "SELECT b.*, l.* "
				+ "FROM beoordeling as b, aankoop as a, rit as r, lid as l "
				+ "WHERE b.aankoopnr = a.aankoopnr "
				+ "AND a.ritnr = r.ritnr "
				+ "AND b.lidnr = l.lidnr "
				+ "AND r.lidnr = ?"
				+ "ORDER BY b.datum DESC;";

		try {
			getBeoordelingen = currentCon.prepareStatement(queryString);
			getBeoordelingen.setInt(1, lidnr);
			rs = getBeoordelingen.executeQuery();

			while (rs.next()) {
				Beoordeling beoordeling = new Beoordeling();
				beoordeling.setBetrouwbaarheid(rs.getInt("betrouwbaarheid"));
				beoordeling.setCommentaar(rs.getString("commentaar"));
				beoordeling.setGezelligheid(rs.getInt("gezelligheid"));
				beoordeling.setLidnr(rs.getInt("lidnr"));
				beoordeling.setRijstijl(rs.getInt("rijstijl"));
				beoordeling.setStiptheid(rs.getInt("stiptheid"));
				beoordeling.setWaardering(rs.getInt("waardering"));
				beoordeling.setDatum(rs.getTimestamp("datum"));
				System.out.println("DATUM: " + beoordeling.getDatum());
				beoordeling.setKorteDatum(dc.korteDatum(beoordeling.getDatum()));
				beoordeling.setKorteTijd(dc.korteTijd(beoordeling.getDatum()));
				//zet alles in de beoordelingen array
				beoordelingen.add(beoordeling);
			}
		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (getBeoordelingen != null) {
				try {
					getBeoordelingen.close();
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

		return beoordelingen;
	}

	/**
	 * Deze methode maakt een beoordeling aan die een lid doet op een aankoop.
	 * In deze methode worden 3 SQL statements uitgevoerd: 1. Maak beoordeling
	 * aan in de tabel beoordeling 2. Update de beoordeling voor een lid in de
	 * tabel lid 3. Update de aankoop als beoordeeld, wanneer deze is beoordeeld
	 * kan deze niet meer beoordeeld worden Wanneer een van de statements faalt
	 * zal de gehele transactie worden teruggedraait.
	 *
	 * @param waardering waardering voor een rit
	 * @param stiptheid stiptheid van de aanbieder van de rit
	 * @param rijstijl waardering voor de rijstijl
	 * @param gezelligheid gezelligheid van de aanbieder
	 * @param betrouwbaarheid betrouwbaarheid van de aanbieder
	 * @param commentaar los commentaar dat toegevoegd kan worden
	 * @param lidnr unieke identifier van een lid
	 * @param aankoopnr unieke identifier van een aankoop
	 * @return
	 */
	public Boolean beoordelingAanmaken(
			double waardering,
			int stiptheid,
			int rijstijl,
			int gezelligheid,
			int betrouwbaarheid,
			String commentaar,
			int lidnr,
			int aankoopnr) {

		Date datum = new Date();
		Timestamp timestamp = new Timestamp(datum.getTime());
		currentCon = ConnectionManager.getConnection();
		PreparedStatement beoordeelLid = null;
		PreparedStatement updateBeoordelingLid = null;
		PreparedStatement updateAankoop = null;
		AankoopDao aankoopDao = new AankoopDao();
		LidDao lidDao = new LidDao();
		boolean check1 = false;
		boolean check2 = false;

		String queryString =
				"INSERT INTO beoordeling ("
				+ " waardering,"
				+ " stiptheid,"
				+ " rijstijl,"
				+ " gezelligheid,"
				+ " betrouwbaarheid,"
				+ " commentaar,"
				+ " lidnr,"
				+ " aankoopnr,"
				+ " datum) "
				+ "VALUES(?,?,?,?,?,?,?,?,?);";

		String updateBeoordeling = "Update lid SET beoordeling = (beoordeling + ?) / 2 "
				+ "WHERE lidnr = ("
				+ "SELECT r.lidnr "
				+ "FROM beoordeling as b, aankoop as a, rit as r, lid as l "
				+ "WHERE b.aankoopnr = a.aankoopnr "
				+ "AND a.ritnr = r.ritnr "
				+ "AND b.lidnr = l.lidnr "
				+ "AND a.aankoopnr = ? LIMIT 1);";

		String updateAankoopBeoordeeld = "UPDATE aankoop "
				+ "SET beoordeeld = 1 "
				+ "WHERE aankoopnr = ? "
				+ "AND beoordeeld < 1;";

		try {
			currentCon.setAutoCommit(false);
			beoordeelLid = currentCon.prepareStatement(queryString);
			updateBeoordelingLid = currentCon.prepareStatement(updateBeoordeling);
			updateAankoop = currentCon.prepareStatement(updateAankoopBeoordeeld);

			beoordeelLid.setDouble(1, waardering);
			beoordeelLid.setInt(2, stiptheid);
			beoordeelLid.setInt(3, rijstijl);
			beoordeelLid.setInt(4, gezelligheid);
			beoordeelLid.setInt(5, betrouwbaarheid);
			beoordeelLid.setString(6, commentaar);
			beoordeelLid.setInt(7, lidnr);
			beoordeelLid.setInt(8, aankoopnr);
			beoordeelLid.setTimestamp(9, timestamp);

			updateBeoordelingLid.setDouble(1, waardering);
			updateBeoordelingLid.setInt(2, aankoopnr);

			updateAankoop.setInt(1, aankoopnr);

			System.out.println("+++++++++++++BeoordelingAanmaken+++++++++++++++\n  Query = " + beoordeelLid + "\n");
			System.out.println("+++++++++++++LidBeoordelingUpdaten+++++++++++++++\n  Query = " + updateBeoordelingLid + "\n");
			//Update de gegevens
			beoordeelLid.executeUpdate();
			updateBeoordelingLid.executeUpdate();
			updateAankoop.executeUpdate();

			//Commit de change
			currentCon.commit();
			currentCon.setAutoCommit(false);
		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("Check1 = " + check1);
			System.out.println("Check1 = " + check2);
			if (currentCon != null) {
				System.err.print("Transaction krijgt een rollback, alle veranderingen die deze query teweeg zou brengen worden teruggedraaid.");
				try {
					currentCon.rollback();
				} catch (SQLException ex1) {
					Logger.getLogger(BeoordelingDao.class.getName()).log(Level.SEVERE, null, ex1);
				}
			}
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
			if (beoordeelLid != null) {
				//sluit preparedStatement
				try {
					beoordeelLid.close();
				} catch (SQLException ignore) {
				}
			}
			if (updateBeoordelingLid != null) {
				//sluit preparedStatement
				try {
					updateBeoordelingLid.close();
				} catch (SQLException ignore) {
				}

			}
			if (updateAankoop != null) {
				//sluit preparedStatement
				try {
					updateAankoop.close();
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
