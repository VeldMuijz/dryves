/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import Dryves.ConnectionManager;
import Dryves.DatumConverter;
import static Dryves.Model.BerichtenDao.rs;
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
	 * Deze methode wordt gebruikt om het aantal beoordelingen op te halen voor een bepaalde gebruiker door middel van een lid nummer.
	 * @param lidnummer
	 * @return Integer met het aantal beoordelingen.
	 * @throws SQLException
	 */
	public int aantalBeoordelingen(int lidnr){
		int aantal=0;
                rs = null;
		currentCon = ConnectionManager.getConnection();
		PreparedStatement getBeoordelingen = null;
		String queryString = ""
				+ "SELECT b.*, l.* "
				+ "FROM beoordeling as b, aankoop as a, rit as r, lid as l "
				+ "WHERE b.aankoopnr = a.aankoopnr "
				+ "AND a.ritnr = r.ritnr "
				+ "AND b.lidnr = l.lidnr "
				+ "AND r.lidnr = ?;";

		try {
			getBeoordelingen = currentCon.prepareStatement(queryString);
			getBeoordelingen.setInt(1, lidnr);
			rs = getBeoordelingen.executeQuery();

			while (rs.next()) {
				aantal++;
				
			}
		} catch (SQLException ex) {
			Logger.getLogger(BeoordelingDao.class
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
			if (getBeoordelingen != null) {
				//sluit preparedStatement
				try {
					getBeoordelingen.close();
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
		return aantal;
	}
        
        

	/**
	 * Haal een lijst van beoordelingen per lid op
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<Beoordeling> getAlleBeoordelingenPerLid(int lidnr, int offset) {
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
				+ "ORDER BY b.datum DESC LIMIT 5 OFFSET ?;";
                
                	
                

		try {
			getBeoordelingen = currentCon.prepareStatement(queryString);
			getBeoordelingen.setInt(1, lidnr);
                        getBeoordelingen.setInt(2, offset);
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
			Logger.getLogger(BeoordelingDao.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
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
	}

	/**
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
	public Boolean beoordelingAanmaken(double waardering, int stiptheid, int rijstijl, int gezelligheid, int betrouwbaarheid, String commentaar, int lidnr, int aankoopnr) {
		Date datum = new Date();
		Timestamp timestamp = new Timestamp(datum.getTime());
		currentCon = ConnectionManager.getConnection();
		PreparedStatement beoordeelLid, updateBeoordelingLid;
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


		try {
			currentCon.setAutoCommit(false);
			beoordeelLid = currentCon.prepareStatement(queryString);
			updateBeoordelingLid = currentCon.prepareStatement(updateBeoordeling);
			
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

			System.out.println("+++++++++++++BeoordelingAanmaken+++++++++++++++\n  Query = " + beoordeelLid + "\n");
			System.out.println("+++++++++++++LidBeoordelingUpdaten+++++++++++++++\n  Query = " + updateBeoordelingLid + "\n");
			//Update de gegevens
			beoordeelLid.executeUpdate();
			updateBeoordelingLid.executeUpdate();

			//Commit de change
			currentCon.commit();
			currentCon.setAutoCommit(false);
		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
			if (currentCon != null) {
				System.err.print("Transaction is being rolled back");
				try {
					currentCon.rollback();
				} catch (SQLException ex1) {
					Logger.getLogger(BeoordelingDao.class.getName()).log(Level.SEVERE, null, ex1);
				}
			}
			return false;
		} finally {
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
