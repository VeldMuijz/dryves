/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroen
 */
public class RitDao {

	static Connection currentCon;
	static ResultSet rs;
	int lidnr;
	String startpunt;
	String eindpunt;
	String waypoints;
	Double afstand;
	Double prijs;
	int gekocht;
	Timestamp datum;
	//Date datum;
	int zitplaatsen;
	int aangeboden;
	String brandstof;

	public Rit ritplannen(Rit bean) {

		setLidnr(bean.getLidnr());
		setStartpunt(bean.getStartpunt());
		setEindpunt(bean.getEindpunt());
		setWaypoints(bean.getWaypoint());
		setAfstand(bean.getAfstand());
		setPrijs(bean.getPrijs());
		setGekocht(bean.getGekocht());
		setDatum(bean.getDatum());

		//setDatum(bean.getDatum());
		setZitplaatsen(bean.getZitplaatsen());
		setAangeboden(bean.getAangeboden());
		setBrandstof(bean.getBrandstof());

		System.out.println("RIT DAO GEGEVENS:");
		System.out.println("*******************************");
		System.out.println("lidnr" + lidnr);
		System.out.println("startpunt: " + startpunt);
		System.out.println("eindpunt: " + eindpunt);
		System.out.println("waypoint: " + waypoints);
		System.out.println("afstand: " + afstand);
		System.out.println("prijs: " + prijs);
		System.out.println("gekocht: " + gekocht);
		System.out.println("datum: " + datum);
		System.out.println("zitplaatsen: " + zitplaatsen);
		System.out.println("aangeboden: " + aangeboden);
		System.out.println("brandstof: " + brandstof);
		System.out.println("*******************************");
		System.out.println("EIND DAO RIT GEGEVENS");

		saveRit();

		return bean;
	}

	public void saveRit() {
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
			insertRit.setString(4, waypoints);
			insertRit.setDouble(5, afstand);
			insertRit.setDouble(6, prijs);
			insertRit.setInt(7, gekocht);
			insertRit.setTimestamp(8, datum);
			insertRit.setInt(9, zitplaatsen);
			insertRit.setString(10, brandstof);
			insertRit.setInt(11, aangeboden);



			System.out.println("De query is: " + insertRit);

			insertRit.executeQuery();

		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);

		} 
		
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

//	public Date getDatum() {
//		return datum;
//	}
//
//	public void setDatum(Date datum) {
//		this.datum = datum;
//	}
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
}
