package Dryves.Model;

import Dryves.ConnectionManager;
import Dryves.DatumConverter;
import Dryves.DayOfWeekIterator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author jeroen
 */
public class RitDao {

	static Connection currentCon;
	static ResultSet rs;
	int ritnr;
	int lidnr;
	String startpunt;
	String eindpunt;
	String waypoints;
	Double afstand;
	Double prijs;
	int gekocht;
	Timestamp datum;
	DateTime begindatum;
	DateTime einddatum;
	int zitplaatsen;
	int aangeboden;
	String brandstof;
	Boolean success;
	int ma = 0;
	int di = 0;
	int wo = 0;
	int don = 0;
	int vr = 0;
	int za = 0;
	int zo = 0;
	int meerdere = 0;
	String url;

	/**
	 * Ophalen van alle gegevens uit de servlet voor de rit_plannen.jsp
	 *
	 * @param bean
	 * @return bean (rit object)
	 */
	public Rit vulRitDao(Rit bean) {

		lidnr = bean.getLidnr();
		startpunt = bean.getStartpunt();
		eindpunt = bean.getEindpunt();
		waypoints = bean.getWaypoint();
		afstand = bean.getAfstand();
		prijs = bean.getPrijs();
		gekocht = bean.getGekocht();
		datum = bean.getDatum();
		zitplaatsen = bean.getZitplaatsen();
		aangeboden = bean.getAangeboden();
		brandstof = bean.getBrandstof();
		return bean;

	}

	/**
	 * Opslaan van rit in de database
	 *
	 * @return false of true
	 */
	public Boolean saveMeerdereRitten() {

		ArrayList<Integer> dagenVdWeek = new ArrayList();
		if (ma == 1) {
			dagenVdWeek.add(ma);
		}
		if (di == 2) {
			dagenVdWeek.add(di);
		}
		if (wo == 3) {
			dagenVdWeek.add(wo);
		}
		if (don == 4) {
			dagenVdWeek.add(don);
		}
		if (vr == 5) {
			dagenVdWeek.add(vr);
		}
		if (za == 6) {
			dagenVdWeek.add(za);
		}
		if (zo == 7) {
			dagenVdWeek.add(zo);
		}


		// voor elk geselecteerde dag voer volgende query uit
		for (int i = 0; i < dagenVdWeek.size(); i++) {
			DayOfWeekIterator it = new DayOfWeekIterator(begindatum, einddatum, dagenVdWeek.get(i));

			while (it.hasNext()) {
				System.out.println("dit is it.next();" + it.next());
				datum = new Timestamp(it.next().getMillis());
				System.out.println("Dit is datum na conversie: " + datum);
				try {
					currentCon = ConnectionManager.getConnection();
					PreparedStatement insertRit;

					String queryString = ("INSERT INTO Rit ("
							+ "lidnr,"
							+ " startpunt,"
							+ " eindpunt,"
							+ " waypoint,"
							+ " afstand,"
							+ " prijs,"
							+ " gekocht,"
							+ " datum,"
							+ " zitplaatsen,"
							+ " brandstof,"
							+ " aangeboden)"
							+ " Values"
							+ "(?,?,?,?,?,?,?,?,?,?,?);");

					insertRit = currentCon.prepareStatement(queryString);

					insertRit.setInt(1, lidnr);
					insertRit.setString(2, startpunt);
					insertRit.setString(3, eindpunt);
					if (waypoints.equals("")) {
						insertRit.setString(4, null);
					} else {
						insertRit.setString(4, waypoints);
					}
					insertRit.setDouble(5, afstand);
					insertRit.setDouble(6, prijs);
					insertRit.setInt(7, gekocht);
					insertRit.setTimestamp(8, datum);
					insertRit.setInt(9, zitplaatsen);
					insertRit.setString(10, brandstof);
					insertRit.setInt(11, aangeboden);
					System.out.println(insertRit);
					insertRit.executeUpdate();

				} catch (SQLException ex) {
					Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);
					return false;


				}

			}
		}
		return true;
	}

	/**
	 *
	 */
	public Boolean saveRit() {

		datum = new Timestamp(begindatum.getMillis());
		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement insertRit;

			String queryString = ("INSERT INTO Rit ("
					+ "lidnr,"
					+ " startpunt,"
					+ " eindpunt,"
					+ " waypoint,"
					+ " afstand,"
					+ " prijs,"
					+ " gekocht,"
					+ " datum,"
					+ " zitplaatsen,"
					+ " brandstof,"
					+ " aangeboden)"
					+ " Values"
					+ "(?,?,?,?,?,?,?,?,?,?,?);");
			insertRit = currentCon.prepareStatement(queryString);

			insertRit.setInt(1, lidnr);
			insertRit.setString(2, startpunt);
			insertRit.setString(3, eindpunt);
			if (waypoints.equals("")) {
				insertRit.setString(4, null);
			} else {
				insertRit.setString(4, waypoints);
			}
			insertRit.setDouble(5, afstand);
			insertRit.setDouble(6, prijs);
			insertRit.setInt(7, gekocht);
			insertRit.setTimestamp(8, datum);
			insertRit.setInt(9, zitplaatsen);
			insertRit.setString(10, brandstof);
			insertRit.setInt(11, aangeboden);

			System.out.println("De query is: " + insertRit);

			insertRit.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		return true;

	}

	/**
	 * Haal een lijst van ritten per lid op
	 *	 
* @return
	 * @throws SQLException
	 */
	public List<Rit> getAlleRittenPerLid() {
		ResultSet resultSet = null;
		List<Rit> ritten = new ArrayList<Rit>();
		DatumConverter dc = new DatumConverter();
		currentCon = ConnectionManager.getConnection();
		PreparedStatement zoekritten = null;
		String queryString = "SELECT * FROM Rit WHERE lidnr = ?;";
		try {

			zoekritten = currentCon.prepareStatement(queryString);
			zoekritten.setInt(1, lidnr);
			resultSet = zoekritten.executeQuery();

			while (resultSet.next()) {
				Rit rit = new Rit();
				rit.setRitnr(resultSet.getInt("ritnr"));
				rit.setStartpunt(resultSet.getString("startpunt"));
				rit.setEindpunt(resultSet.getString("eindpunt"));
				rit.setPrijs(resultSet.getDouble("prijs"));
				rit.setDatumkort(dc.korteDatum(resultSet.getTimestamp("datum")));
				rit.setTijd(dc.korteTijd(resultSet.getTimestamp("datum")));
				ritten.add(rit);
			}
		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (zoekritten != null) {
				//sluit preparedStatement
				try {
					zoekritten.close();
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
		return ritten;

	}

	/**
	 * Haal een lijst van gekochte ritten per lid op
	 *	 
* @return
	 * @throws SQLException
	 */
	public List<Rit> getAlleGekochteRittenPerLid() {
		ResultSet resultSet = null;
		List<Rit> ritten = new ArrayList<Rit>();

		currentCon = ConnectionManager.getConnection();
		PreparedStatement gekochteritten = null;
		String queryString = "SELECT r.* "
				+ "FROM rit AS r, aankoop AS a "
				+ "WHERE r.ritnr = a.ritnr AND a.lidnr = ?;";

		try {

			gekochteritten = currentCon.prepareStatement(queryString);
			gekochteritten.setInt(1, lidnr);
			resultSet = gekochteritten.executeQuery();

			while (resultSet.next()) {
				Rit rit = new Rit();
				rit.setRitnr(resultSet.getInt("ritnr"));
				rit.setStartpunt(resultSet.getString("startpunt"));
				rit.setEindpunt(resultSet.getString("eindpunt"));
				rit.setPrijs(resultSet.getDouble("prijs"));
				ritten.add(rit);
			}
		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (gekochteritten != null) {
				//sluit preparedStatement
				try {
					gekochteritten.close();
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
		return ritten;

	}

	public List<Rit> getRittenLijst() {
		currentCon = ConnectionManager.getConnection();
		PreparedStatement selectRitten = null;
		String queryString = "SELECT * FROM rit WHERE lidnr = ?;";

		try {
			selectRitten = currentCon.prepareStatement(queryString);

			selectRitten.setInt(1, lidnr);


		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (selectRitten != null) {
				//sluit preparedStatement
				try {
					selectRitten.close();
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

		return getRittenLijst();
	}

	/**
	 * Checkt of er een zitplaats beschikbaar is, wanneer er een zitplaats
	 * beschikbaar is dan zou deze zitplaats gekocht kunnen worden
	 *
	 * @param ritnr
	 * @return
	 */
	public Boolean checkBeschikbaarheidRit(int ritnr) {
		rs = null;
		currentCon = ConnectionManager.getConnection();
		Boolean beschikbaar = false;
		PreparedStatement zitplaatsCheck = null;
		String queryString = "SELECT zitplaatsen FROM rit WHERE ritnr = ? AND zitplaatsen > 0 LIMIT 1;";

		try {

			zitplaatsCheck = currentCon.prepareStatement(queryString);
			zitplaatsCheck.setInt(1, ritnr);

			rs = zitplaatsCheck.executeQuery();

			if (rs.next()) {

				beschikbaar = true;
			} else {
				beschikbaar = false;
			}

		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (zitplaatsCheck != null) {
				//sluit preparedStatement
				try {
					zitplaatsCheck.close();
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
		System.out.println("Beschikbaar = " + beschikbaar);
		return beschikbaar;

	}

	/**
	 *
	 * @param ritnr
	 * @param updatewaarde
	 * @return
	 */
	public Boolean updateZitplaatsOphogen(int ritnr, int updatewaarde) {
		rs = null;
		Boolean beschikbaar = false;
		PreparedStatement updateZitplaats = null;
		currentCon = ConnectionManager.getConnection();
		String updateQueryString = (""
				+ "UPDATE rit "
				+ "SET zitplaatsen = zitplaatsen + ? "
				+ "WHERE ritnr = ? "
				+ "AND zitplaatsen > 0;");

		try {


			updateZitplaats = currentCon.prepareStatement(updateQueryString);

			updateZitplaats.setInt(1, updatewaarde);
			updateZitplaats.setInt(2, ritnr);
			updateZitplaats.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (updateZitplaats != null) {
				//sluit preparedStatement
				try {
					updateZitplaats.close();
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
	 *
	 * @param ritnr
	 * @param updatewaarde
	 * @return
	 */
	public Boolean updateZitplaatsVerlagen(int ritnr, int updatewaarde) {
		rs = null;
		Boolean beschikbaar = false;
		PreparedStatement updateZitplaats = null;

		currentCon = ConnectionManager.getConnection();
		String updateQueryString = (""
				+ "UPDATE rit "
				+ "SET zitplaatsen = zitplaatsen - ? "
				+ "WHERE ritnr = ? "
				+ "AND zitplaatsen > 0;");
		try {
			updateZitplaats = currentCon.prepareStatement(updateQueryString);

			updateZitplaats.setInt(1, updatewaarde);
			updateZitplaats.setInt(2, ritnr);
			System.out.println("Query = " + updateZitplaats);
			updateZitplaats.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (updateZitplaats != null) {
				//sluit preparedStatement
				try {
					updateZitplaats.close();
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
	 * Haal een lijst van ritten per lid op
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<Rit> getAlleRitten(String startpunt, String eindpunt){
		Connection connection = null;
		//PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Rit> ritten = new ArrayList<Rit>();
		DatumConverter dc = new DatumConverter();
		currentCon = ConnectionManager.getConnection();
		PreparedStatement zoekritten = null;
		try {
			String queryString = "SELECT * "
					+ "FROM Rit "
					+ "WHERE aangeboden = 1 "
					+ "AND zitplaatsen > 0 "
					+ "AND LOWER(startpunt) LIKE LOWER(?) "
					+ "AND LOWER(eindpunt) LIKE LOWER(?) "
					+ "AND datum > DATE(NOW());";

			zoekritten = currentCon.prepareStatement(queryString);

			zoekritten.setString(1, "%" + startpunt + "%");

			zoekritten.setString(2, "%" + eindpunt + "%");

			resultSet = zoekritten.executeQuery();

			while (resultSet.next()) {

				Rit rit = new Rit();
				rit.setRitnr(resultSet.getInt("ritnr"));
				rit.setStartpunt(resultSet.getString("startpunt"));

				String[] arrSplit = rit.getStartpunt().split(", ");

				rit.setStraatnummer(arrSplit[0]);
				rit.setPostcodeplaats(arrSplit[1].substring(5));
				rit.setLand(arrSplit[2]);

				rit.setEindpunt(resultSet.getString("eindpunt"));

				String[] arrSplit2 = rit.getEindpunt().split(", ");

				rit.setStraatnummerEnd(arrSplit2[0]);
				rit.setPostcodeplaatsEnd(arrSplit2[1].substring(5));
				rit.setLandEnd(arrSplit[2]);

				rit.setPrijs(resultSet.getDouble("prijs"));
				rit.setDatum(resultSet.getTimestamp("datum"));
				rit.setZitplaatsen(resultSet.getInt("zitplaatsen"));

				rit.setDatumkort(dc.korteDatum(resultSet.getTimestamp("datum")));
				rit.setTijd(dc.korteTijd(resultSet.getTimestamp("datum")));

				ritten.add(rit);
			}
		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (zoekritten != null) {
				//sluit preparedStatement
				try {
					zoekritten.close();
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
		return ritten;

	}

	/**
	 * Ophalen van een enkele rit
	 *
	 * @param ritnr
	 * @param bean
	 * @return rit object
	 */
	public Rit enkeleRitOphalen(int ritnr, Rit bean) {
		rs = null;
		currentCon = ConnectionManager.getConnection();

			PreparedStatement select1Rit = null;
			String queryString = ("SELECT * FROM Rit WHERE ritnr = ?;");
		try {
			

			select1Rit = currentCon.prepareStatement(queryString);

			select1Rit.setInt(1, ritnr);
			System.out.println("De query is: " + select1Rit);

			rs = select1Rit.executeQuery();

			while (rs.next()) {
				bean.setLidnr(rs.getInt("lidnr"));
				bean.setRitnr(rs.getInt("ritnr"));
				bean.setStartpunt(rs.getString("startpunt"));
				bean.setEindpunt(rs.getString("eindpunt"));
				bean.setPrijs(rs.getDouble("prijs"));
				bean.setWaypoint(rs.getString("waypoint"));
				bean.setAfstand(rs.getDouble("afstand"));
				bean.setDatum(rs.getTimestamp("datum"));
				bean.setZitplaatsen(rs.getInt("zitplaatsen"));
				bean.setBrandstof(rs.getString("brandstof"));
				bean.setAangeboden(rs.getInt("aangeboden"));
			}



		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (select1Rit != null) {
				//sluit preparedStatement
				try {
					select1Rit.close();
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

		System.out.println(bean.getRitnr());
		return bean;

	}

	/**
	 * Updaten van een rit, wijzigingen opslaan
	 *
	 * @param ritnr
	 * @return true of false
	 */
	public Boolean updateRit(int ritnr) {

		datum = new Timestamp(begindatum.getMillis());
		rs = null;
		currentCon = ConnectionManager.getConnection();
			PreparedStatement updateRit = null;
		try {
			

			String queryString = ("Update Rit SET"
					+ " startpunt = ?,"
					+ " eindpunt = ?,"
					+ " waypoint = ?,"
					+ " afstand = ?,"
					+ " prijs = ?,"
					+ " gekocht = ?,"
					+ " datum = ?,"
					+ " zitplaatsen = ?,"
					+ " brandstof = ?,"
					+ " aangeboden = ?"
					+ "WHERE ritnr = ?;");

			updateRit = currentCon.prepareStatement(queryString);


			updateRit.setString(1, startpunt);
			updateRit.setString(2, eindpunt);
			if (!waypoints.isEmpty()) {
				updateRit.setString(3, waypoints);
			} else {
				updateRit.setString(3, null);
			}
			updateRit.setDouble(4, afstand);
			updateRit.setDouble(5, prijs);
			updateRit.setInt(6, gekocht);
			updateRit.setTimestamp(7, datum);
			updateRit.setInt(8, zitplaatsen);
			updateRit.setString(9, brandstof);
			updateRit.setInt(10, aangeboden);
			updateRit.setInt(11, ritnr);

			System.out.println("De query is: " + updateRit);

			updateRit.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class
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
			if (updateRit != null) {
				//sluit preparedStatement
				try {
					updateRit.close();
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

	public static Connection getCurrentCon() {
		return currentCon;
	}

	public static void setCurrentCon(Connection currentCon) {
		RitDao.currentCon = currentCon;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		RitDao.rs = rs;
	}

	public int getRitnr() {
		return ritnr;
	}

	public void setRitnr(int ritnr) {
		this.ritnr = ritnr;
	}

	public int getLidnr() {
		return lidnr;
	}

	public void setLidnr(int lidnr) {
		this.lidnr = lidnr;
	}

	public String getStartpunt() {
		return startpunt;
	}

	public void setStartpunt(String startpunt) {
		this.startpunt = startpunt;
	}

	public String getEindpunt() {
		return eindpunt;
	}

	public void setEindpunt(String eindpunt) {
		this.eindpunt = eindpunt;
	}

	public String getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(String waypoints) {
		this.waypoints = waypoints;
	}

	public Double getAfstand() {
		return afstand;
	}

	public void setAfstand(Double afstand) {
		this.afstand = afstand;
	}

	public Double getPrijs() {
		return prijs;
	}

	public void setPrijs(Double prijs) {
		this.prijs = prijs;
	}

	public int getGekocht() {
		return gekocht;
	}

	public void setGekocht(int gekocht) {
		this.gekocht = gekocht;
	}

	public Timestamp getDatum() {
		return datum;
	}

	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}

	public DateTime getBegindatum() {
		return begindatum;
	}

	public void setBegindatum(DateTime begindatum) {
		this.begindatum = begindatum;
	}

	public DateTime getEinddatum() {
		return einddatum;
	}

	public void setEinddatum(DateTime einddatum) {
		this.einddatum = einddatum;
	}

	public int getZitplaatsen() {
		return zitplaatsen;
	}

	public void setZitplaatsen(int zitplaatsen) {
		this.zitplaatsen = zitplaatsen;
	}

	public int getAangeboden() {
		return aangeboden;
	}

	public void setAangeboden(int aangeboden) {
		this.aangeboden = aangeboden;
	}

	public String getBrandstof() {
		return brandstof;
	}

	public void setBrandstof(String brandstof) {
		this.brandstof = brandstof;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public int getDi() {
		return di;
	}

	public void setDi(int di) {
		this.di = di;
	}

	public int getWo() {
		return wo;
	}

	public void setWo(int wo) {
		this.wo = wo;
	}

	public int getDon() {
		return don;
	}

	public void setDon(int don) {
		this.don = don;
	}

	public int getVr() {
		return vr;
	}

	public void setVr(int vr) {
		this.vr = vr;
	}

	public int getZa() {
		return za;
	}

	public void setZa(int za) {
		this.za = za;
	}

	public int getZo() {
		return zo;
	}

	public void setZo(int zo) {
		this.zo = zo;
	}

	public int getMeerdere() {
		return meerdere;
	}

	public void setMeerdere(int meerdere) {
		this.meerdere = meerdere;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
