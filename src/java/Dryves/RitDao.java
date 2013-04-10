/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeroen
 */
public class RitDao {

	static Connection currentCon = null;
	static ResultSet rs = null;

	public static Rit ritplannen(Rit bean) {

		Statement stmt = null;



		int lidnr = bean.getLidnr();
		String startpunt = bean.getStartpunt();
		String eindpunt = bean.getStartpunt();
		String waypoints = bean.getWaypoint();
		Double afstand = bean.getAfstand();
		Double prijs = bean.getPrijs();
		int gekocht = bean.getGekocht();
		Date datum = bean.getDatum();
		int zitplaatsen = bean.getZitplaatsen();
		int aangeboden = bean.getAangeboden();
		String brandstof = bean.getBrandstof();

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


		return bean;
	}
}
