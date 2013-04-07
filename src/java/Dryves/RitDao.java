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

/**
 *
 * @author jeroen
 */
public class RitDao {
	
	static Connection currentCon = null;
      static ResultSet rs = null;  
	  
	  public static  Rit ritplannen(Rit bean){
		  
		  Statement stmt = null;
		  
		  String startpunt = bean.getStartpunt();
		  String eindpunt = bean.getStartpunt();
		  Date datum = bean.getDatum();
		 	  
		  System.out.println("bean " + startpunt);
		  System.out.println("bean " + eindpunt);
		  System.out.println(datum);
		  
		  
		  
		  return bean;
	  }
}
