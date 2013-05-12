/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import Dryves.ConnectionManager;
import static Dryves.Model.RitDao.currentCon;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RickSpijker
 */
public class AdmindryvesDao {
    
    private String achtergrond;
    private String ritprijs;
    
    public Lid vulAdd(Lid bean){
        
        achtergrond = bean.getAchtergrond();
        ritprijs = bean.getRitprijs();
    
        
        return bean;
    
    }
    
    /**
     *
     */
    public void AdmindryvesDao(){

        
        System.out.println("Dit is de achtergrond uit de DAO: " + achtergrond);
        
        
        System.out.println("Dit is de ritprijs uit de DAO: " + ritprijs);
        
        try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement updateAdmin;
                        
            String query = "update configuratie set \"Achtergrond\" = ?, \"Ritprijs\" = ? where confnr = 1";
            updateAdmin = currentCon.prepareStatement(query);
            updateAdmin.setString(1, achtergrond);
            updateAdmin.setString(2, ritprijs);
            updateAdmin.executeUpdate();


		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);
			
		}
        
    }
    
}
