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
	  
	  public static  Rit ritplannen(Rit bean){
		  
		  Statement stmt = null;
	
		  
		  int lidNr = bean.getLidnr();
		  String startpunt = bean.getStartpunt();
		  String eindpunt = bean.getStartpunt();
		  String waypoint = bean.getWaypoint();
		  BigDecimal afstand = bean.getAfstand();
		  BigDecimal prijs = bean.getPrijs();
		  int gekocht = bean.getGekocht();
		  Date datum = bean.getDatum();
		  int zitplaatsen = bean.getZitplaatsen();
		  int aangeboden = bean.getAangeboden();
		  String brandstof = bean.getBrandstof();
		  
		  System.out.println("lidnr: " + lidNr);
		  System.out.println("bean " + startpunt);
		  System.out.println("bean " + eindpunt);
		  System.out.println(datum);
		  
		  
		  
		  return bean;
	  }
}
