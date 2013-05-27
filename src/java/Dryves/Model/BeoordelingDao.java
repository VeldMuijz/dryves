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
		ResultSet resultSet = null;
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
			resultSet = getBeoordelingen.executeQuery();

			while (resultSet.next()) {
				Beoordeling beoordeling = new Beoordeling();
				beoordeling.setBetrouwbaarheid(resultSet.getInt("betrouwbaarheid"));
				beoordeling.setCommentaar(resultSet.getString("commentaar"));
				beoordeling.setGezelligheid(resultSet.getInt("gezelligheid"));
				beoordeling.setLidnr(resultSet.getInt("lidnr"));
				beoordeling.setRijstijl(resultSet.getInt("rijstijl"));
				beoordeling.setStiptheid(resultSet.getInt("stiptheid"));
				beoordeling.setWaardering(resultSet.getInt("waardering"));
				beoordeling.setDatum(resultSet.getTimestamp("datum"));
				System.out.println("DATUM: " + beoordeling.getDatum());
				beoordeling.setKorteDatum(dc.korteDatum(beoordeling.getDatum()));
				beoordeling.setKorteTijd(dc.korteTijd(beoordeling.getDatum()));
				//zet alles in de beoordelingen array
				beoordelingen.add(beoordeling);
			}
		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
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
	 *
	 * @param waardering
	 * @param stiptheid
	 * @param rijstijl
	 * @param gezelligheid
	 * @param betrouwbaarheid
	 * @param commentaar
	 * @param lidnr
	 * @param aankoopnr
	 * @return
	 */
	public Boolean beoordelingAanmaken(int waardering, int stiptheid, int rijstijl, int gezelligheid, int betrouwbaarheid, String commentaar, int lidnr, int aankoopnr) {
		Date datum = new Date();
		Timestamp timestamp = new Timestamp(datum.getTime());
		
		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement beoordeelLid;
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

			beoordeelLid = currentCon.prepareStatement(queryString);
			beoordeelLid.setInt(1, waardering);
			beoordeelLid.setInt(2, stiptheid);
			beoordeelLid.setInt(3, rijstijl);
			beoordeelLid.setInt(4, gezelligheid);
			beoordeelLid.setInt(5, betrouwbaarheid);
			beoordeelLid.setString(6, commentaar);
			beoordeelLid.setInt(7, lidnr);
			beoordeelLid.setInt(8, aankoopnr);
			System.out.println("timestamp = " + timestamp);
			beoordeelLid.setTimestamp(9, timestamp);

			System.out.println("+++++++++++++BeoordelingAanmaken+++++++++++++++\n  Query = " + beoordeelLid + "\n");
			beoordeelLid.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		finally {
			
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return true;
	}
}
