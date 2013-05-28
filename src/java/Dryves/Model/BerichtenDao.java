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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class BerichtenDao {

	List<Lid> afzender = new ArrayList<Lid>();
	static Connection currentCon;
	static ResultSet rs;
	private int lidnr;
	private String onderwerp;
	private Timestamp datum;
	private String inhoud;
	private int ritnr;

	/**
	 * Deze methode wordt gebruikt om de klasse BerichtDao te vullen vanuit de
	 * Berichten Bean
	 *
	 * @param bean
	 * @return
	 */
	public Berichten vulBerichtDao(Berichten bean) {

		lidnr = bean.getLidnr();
		//onderwerp=bean.getOnderwerp();
		datum = bean.getDatum();
		inhoud = bean.getInhoud();
		ritnr = bean.getRitnr();


		return bean;

	}

	/**
	 * Haal lidnr op aan de hand van een berichtID
	 *
	 * @param ritnr
	 * @return
	 */
	public int haalLidNr(int ritnr) {
		int lidnr = 0;
		rs = null;
		currentCon = ConnectionManager.getConnection();
		PreparedStatement haalLidnr = null;
		String queryString = "SELECT lidnr FROM rit WHERE ritnr = ?;";

		try {
			haalLidnr = currentCon.prepareStatement(queryString);
			haalLidnr.setInt(1, ritnr);
			rs = haalLidnr.executeQuery();

			while (rs.next()) {

				lidnr = rs.getInt(1);
				System.out.println("Lidnummer: " + lidnr);
			}
		} catch (Exception e) {
			System.out.println(e);

		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (haalLidnr != null) {
				//sluit preparedStatement
				try {
					haalLidnr.close();
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

		System.out.println("output van haallidnr: " + lidnr);
		return lidnr;


	}

	//min 1 bij ongelezen berichten
	/**
	 * Markeer het berichten ongelezen in de tabel berichten
	 *
	 * @param berichtid
	 */
	public boolean markeerBericht(int berichtid) {
		currentCon = ConnectionManager.getConnection();
		PreparedStatement updateBerichtStatus = null;
		String queryString = "UPDATE berichten "
				+ "SET gelezen = 0 "
				+ "WHERE berichtnr = ?;";
		try {

			updateBerichtStatus = currentCon.prepareStatement(queryString);
			updateBerichtStatus.setInt(1, berichtid);

			updateBerichtStatus.executeUpdate();

		} catch (SQLException e) {
			Logger.getLogger(BerichtenDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (updateBerichtStatus != null) {
				//sluit preparedStatement
				try {
					updateBerichtStatus.close();
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

	/**
	 * Deze methode verstuurd een bericht van een lid naar een ander lid door
	 * een bericht weg te schrijven in de berichten tabel
	 *
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public boolean BerichtVersturen(Berichten bean) throws SQLException {

		//Met deze functie komen we erachter wie
		//de eigenaar is van de aangeboden rit d.m.v ritnr
		int lidnreigenaar = haalLidNr(bean.getRitnr());
		currentCon = ConnectionManager.getConnection();
		PreparedStatement verstuurBericht = null;
		String queryString = "INSERT INTO berichten  (inhoudbericht, datum, afzender, lidnr, gelezen, ritnr)"
				+ "VALUES (?,?,?,?,?,?)";

		try {

			verstuurBericht = currentCon.prepareStatement(queryString);
			verstuurBericht.setString(1, bean.getInhoud());
			verstuurBericht.setTimestamp(2, bean.getDatum());
			verstuurBericht.setInt(3, bean.getLidnr());
			verstuurBericht.setInt(4, lidnreigenaar);
			verstuurBericht.setInt(5, 1);
			verstuurBericht.setInt(6, bean.getRitnr());
			verstuurBericht.executeUpdate();

		} catch (SQLException e) {
			Logger.getLogger(BerichtenDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (verstuurBericht != null) {
				//sluit preparedStatement
				try {
					verstuurBericht.close();
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

	//beantwoord bericht
	/**
	 * Deze methode beantwoord een bericht die een lid ontvangen heeft
	 *
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public boolean beantwoordBericht(Berichten bean) throws SQLException {
		currentCon = ConnectionManager.getConnection();
		PreparedStatement verstuurBericht = null;
		String queryString = "INSERT INTO berichten  (inhoudbericht,datum,afzender,lidnr,gelezen,ritnm)"
				+ "VALUES (?,?,?,?,?,?)";
		try {

			verstuurBericht = currentCon.prepareStatement(queryString);
			verstuurBericht.setString(1, bean.getInhoud());
			verstuurBericht.setTimestamp(2, bean.getDatum());
			verstuurBericht.setInt(3, bean.getAfzender());
			verstuurBericht.setInt(4, bean.getLidnr());
			verstuurBericht.setInt(5, 1);
			verstuurBericht.setInt(6, bean.getRitnr());
			verstuurBericht.executeUpdate();

		} catch (SQLException e) {
			Logger.getLogger(BerichtenDao.class.getName()).log(Level.SEVERE, null, e);
			return false;

		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (verstuurBericht != null) {
				//sluit preparedStatement
				try {
					verstuurBericht.close();
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

	//hiermee vullen we de inbox
	/**
	 * Deze methode laadt de inbox van een gebruiker wanneer deze wordt
	 * opgevraagd
	 *
	 * @param lidnr
	 * @return Lijst van het object Berichten
	 * @throws SQLException
	 */
	public List<Berichten> haalberichten(int lidnr) throws SQLException {
		System.out.println("Lidnummer:" + lidnr);
		currentCon = ConnectionManager.getConnection();
		rs = null;
		PreparedStatement haalAlleBerichten = null;
		String queryString = "SELECT * "
				+ "FROM berichten "
				+ "WHERE lidnr = ? "
				+ "ORDER BY datum DESC;";
		List<Berichten> berichten = new ArrayList<Berichten>();

		DatumConverter dc = new DatumConverter();

		try {
			haalAlleBerichten = currentCon.prepareStatement(queryString);
			haalAlleBerichten.setInt(1, lidnr);
			rs = haalAlleBerichten.executeQuery();

			while (rs.next()) {

				Berichten bericht = new Berichten();
				bericht.setBerichtid(rs.getInt("berichtnr"));
				bericht.setAfzender(rs.getInt("afzender"));
				bericht.setDatum(rs.getTimestamp("datum"));
				bericht.setStringTijd(dc.korteTijd(bericht.getDatum()));
				bericht.setStringDatum(dc.korteDatum(bericht.getDatum()));
				bericht.setRitnr(rs.getInt("ritnm"));
				bericht.setOngelezen(rs.getInt("gelezen"));

				berichten.add(bericht);
			}
		} catch (SQLException e) {
			Logger.getLogger(BerichtenDao.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (haalAlleBerichten != null) {
				//sluit preparedStatement
				try {
					haalAlleBerichten.close();
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
		return berichten;
	}

	/**
	 * Deze methode geeft de afzender van een bericht terug, met voor en
	 * achternaam. Dit kan gebruikt worden om in de lijst van berichten een voor
	 * en achternaam van een lid te zetten.
	 *
	 * @param afzenderLidnr
	 * @return
	 */
	public Lid afzender(int afzenderLidnr) {

		Lid afzenderLid = new Lid();
		LidDao lidDao = new LidDao();

		afzenderLid = lidDao.enkelLidOphalen(afzenderLidnr, afzenderLid);
		System.out.println("afzenderlid na het ophalen van gegevens = " + afzenderLid.getLidnr());
		System.out.println("Afzenderlid na het ophalen van gegevens = " + afzenderLid.getAnaam());

		return afzenderLid;

	}

	/**
	 * Deze methode haalt alle berichten op voor een bepaald berichtnr
	 *
	 * @param berichtid
	 * @return Lijst van object Berichten
	 * @throws SQLException
	 */
	public List<Berichten> getAlleBerichtenbijId(int berichtid) throws SQLException {
		List<Berichten> berichtlijst = new ArrayList<Berichten>();
		DatumConverter dc = new DatumConverter();
		rs = null;
		currentCon = ConnectionManager.getConnection();
		PreparedStatement selecteerBericht = null;
		String queryString = "SELECT  berichtnr, INHOUDBERICHT, DATUM, Ritnr, lidnr, afzender FROM berichten WHERE berichtnr =?;";

		try {

			selecteerBericht = currentCon.prepareStatement(queryString);
			selecteerBericht.setInt(1, berichtid);
			rs = selecteerBericht.executeQuery();

			while (rs.next()) {
				Berichten bericht = new Berichten();
				bericht.setBerichtid(rs.getInt(1));
				bericht.setInhoud(rs.getString(2));
				bericht.setDatum(rs.getTimestamp(3));
				bericht.setStringDatum(dc.korteDatum(bericht.getDatum()));
				bericht.setStringTijd(dc.korteTijd(bericht.getDatum()));

				bericht.setRitnr(rs.getInt(4));
				bericht.setLidnr(rs.getInt(5));
				bericht.setAfzender(rs.getInt(6));
				berichtlijst.add(bericht);
			}
		} catch (SQLException e) {
			Logger.getLogger(BerichtenDao.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (selecteerBericht != null) {
				//sluit preparedStatement
				try {
					selecteerBericht.close();
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
		return berichtlijst;

	}

	/**
	 *	Deze methode wordt gebruikt om het aantal ongelezen berichten voor een lid te tonen
	 * @param lidnummer
	 * @return Aantal berichten
	 */
	public int statusbalk(int lidnummer) {
		rs = null;
		currentCon = ConnectionManager.getConnection();
		int aantal = 0;
		PreparedStatement pstmt = null;
		String queryString = "SELECT gelezen "
				+ "FROM berichten "
				+ "WHERE lidnr =? "
				+ "AND gelezen=1";
		try {
			pstmt = currentCon.prepareStatement(queryString);


			pstmt.setInt(1, lidnummer);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rs.getInt(1);
				aantal++;

			}


		} catch (SQLException e) {
			Logger.getLogger(BerichtenDao.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (pstmt != null) {
				//sluit preparedStatement
				try {
					pstmt.close();
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
}
