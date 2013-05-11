/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import Dryves.ConnectionManager;
import static Dryves.Model.LidDao.currentCon;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroen
 */
public class BeoordeelingDao {
		
private int	 beoordelingnr;
private int  waardering;
private int  stiptheid;
private int  rijstijl;
private int  gezelligheid;
private int  betrouwbaarheid;
private String  commentaar;
private int  lidnr;
private int  aankoopnr;
	
	public Beoordeling vulBeoordeling(Beoordeling bean){
		 beoordelingnr = bean.getBeoordelingnr();
		 waardering = bean.getWaardering();
		 stiptheid = bean.getStiptheid();
		 rijstijl = bean.getRijstijl();
		 gezelligheid = bean.getGezelligheid();
		 betrouwbaarheid = bean.getBetrouwbaarheid();
		 commentaar = bean.getCommentaar();
		 lidnr = bean.getLidnr();
		 aankoopnr = bean.getAankoopnr();		
		
		return bean;
	}
	
	public Boolean beoordelingAanmaken(int waardering, int stiptheid, int rijstijl, int gezelligheid, int betrouwbaarheid, String commentaar, int lidnr, int aankoopnr){
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
					+ " aankoopnr"
					+ " ) "
					+ "VALUES(?,?,?,?,?,?,?,?);";
			
			beoordeelLid = currentCon.prepareStatement(queryString);
			beoordeelLid.setInt(1, waardering);
			beoordeelLid.setInt(2, stiptheid);
			beoordeelLid.setInt(3, rijstijl);
			beoordeelLid.setInt(4, gezelligheid);
			beoordeelLid.setInt(5, betrouwbaarheid);
			beoordeelLid.setString(6, commentaar);
			beoordeelLid.setInt(7, lidnr);
			beoordeelLid.setInt(8, aankoopnr);
			
			System.out.println("+++++++++++++BeoordelingAanmaken+++++++++++++++\n Query = " + queryString + "\n");
			beoordeelLid.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		return true;
	}
	
	
	
}
