/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

/**
 *
 * @author RickSpijker
 */
import static Dryves.RitDao.currentCon;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RickSpijker
 */
public class SessieDao {
    static Connection currentCon;
	static ResultSet rs;

	public static Lid login(Lid bean2) {



		//preparing some objects for connection 
		Statement stmt = null;

		String email = bean2.getEmail();
		String wachtwoord = bean2.getWachtwoord();

		ResultSet rs;
		Connection con = Dryves.ConnectionManager.getConnection();
		Lid bean = new Lid();


		// "System.out.println" prints in the console; Normally used to trace the process
		System.out.println("Your user name is " + email);
		System.out.println("Your wachtwoord is " + wachtwoord);
		// System.out.println("Query: " + zoekQuery);

		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT lidnr ,vnaam,anaam,geslacht,straat,postcode, stad,telnr,reknr,email,beoordeling,fotourl,tvoegsel,wachtwoord,langnotify FROM lid WHERE email = ?");
			pstmt.setString(1, email);
			//connect to DB 
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();




			rs = pstmt.executeQuery();

			//rs = stmt.executeQuery(zoekQuery);           
			boolean more = rs.next();

			// if user does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, je bent niet geregistreerd. Registreer eerst alstublieft.");
				bean.setValid(false);
			} //if user exists set the isValid variable to true
			else if (more) {
				//Hieronder wordt het wachtwoord van de user opgehaald uit de database 
				String wachtwoordUser = rs.getString(14);

				//Hieronder worden de wachtwoorden geprint in de console
				System.out.println("Dit is het ww uit de DB: " + wachtwoordUser);

				System.out.println("Dit is het ww van de JSP: " + wachtwoord);

				//Hieronder wordt de locale opgehaald uit de database
				// String locale = rs.getString(5);

				//Hieronder wordt de locale naar de console geprint
				//System.out.println("Dit is de locale van het lid: " + locale);

				if (wachtwoord.equals(wachtwoordUser)) {






					//de nieuwe
					bean.setLidnr(rs.getInt(1));
					bean.setVnaam(rs.getString(2).toString());
					bean.setAnaam(rs.getString(3));
					bean.setGeslacht(rs.getString(4));
					bean.setStraat(rs.getString(5));
					bean.setPostcode(rs.getString(6));
					bean.setStad(rs.getString(7));
					bean.setTelnr(rs.getString(8));
					bean.setReknr(rs.getString(9));
					bean.setEmail(rs.getString(10));
					bean.setBeoordeling(rs.getInt(11));
					bean.setFotoUrl(rs.getString(12));
					bean.setTvoegsel(rs.getString(13));;
					bean.setWachtwoord(rs.getString(14));
					bean.setLangnotify(rs.getString(15));
					bean.setLocale(rs.getString(15));//
					bean.setWachtwoord2(wachtwoord);
					bean.setValid(true);


					//Hieronder wordt de locale vanuit de getter geprint naar de console
					System.out.println("Dit is de locale vanuit de getter van sessie :" + bean.getLocale());

				} else {
					System.out.println("Sorry, you are not a registered user! Please sign up first");
					bean.setValid(false);
				}
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} //some exception handling
		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}

				con = null;
			}
		}

		return bean;

	}
        
        public Lid enkelLidUpdaten(Lid bean){
            try {
			currentCon = ConnectionManager.getConnection();
                        
			PreparedStatement updateLid;
			String queryString = ("UPDATE TABLE lid (lidnr, vnaam, anaam, tvoegsel, straat, postcode, reknr, telnr, wachtwoord, email, stad, geslacht, langnotify)"
                                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");

			updateLid = currentCon.prepareStatement(queryString);

                        updateLid.setString(1, bean.getVnaam());
                        updateLid.setString(2, bean.getAnaam());
                        updateLid.setString(3, bean.getTvoegsel());
                        updateLid.setString(4, bean.getStraat());                       
                        updateLid.setString(5, bean.getPostcode());
                        updateLid.setString(6, bean.getReknr());
                        updateLid.setString(7, bean.getTelnr());
                        updateLid.setString(8, bean.getWachtwoord());
                        updateLid.setString(9, bean.getEmail());
                        updateLid.setString(10, bean.getStad());
                        updateLid.setString(11, bean.getGeslacht());
                        updateLid.setString(12, bean.getLangnotify());
                        updateLid.setString(13, bean.getWachtwoord());
                        
                        
                        
			System.out.println("De query is: " + updateLid);
                        
			updateLid.executeUpdate();
                        
                        
                       
                        
                        
		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);

		}
            
            return bean;
        }

	private static Locale toString(String locale) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
