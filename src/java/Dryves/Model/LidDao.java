/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

/**
 *
 * @author RickSpijker
 */
import Dryves.ConnectionManager;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RickSpijker
 */
public class LidDao {

	private String vnaam;
	private String anaam;
	private String geslacht;
	private String straat;
	private String huisnummer;
	private String postcode;
	private String stad;
	private String telnr;
	private String reknr;
	private String email;
	private String wachtwoord;
	private String wachtwoord2;
	private String fotoUrl;
	private String tvoegsel;
	private String langnotify;
	private Hashtable errors;
	private boolean success;
	static Connection currentCon;
	static ResultSet rs;
	private String facebookid;

	/**
	 * Ophalen van alle gegevens uit de servlet voor de registreren.jsp
	 *
	 * @param bean
	 * @return
	 */
	public Lid vulLidDao(Lid bean) {

		vnaam = bean.getVnaam();
		anaam = bean.getAnaam();
		tvoegsel = bean.getTvoegsel();
		straat = bean.getStraat();
		huisnummer = bean.getHuisnummer();
		geslacht = bean.getGeslacht();
		postcode = bean.getPostcode();
		stad = bean.getStad();
		telnr = bean.getTelnr();
		reknr = bean.getReknr();
		email = bean.getEmail();
		wachtwoord = bean.getWachtwoord();
		fotoUrl = bean.getFotoUrl();
		langnotify = bean.getLangnotify();
		facebookid = bean.getFacebookid();


		System.out.println("Lid DAO GEGEVENS:");
		System.out.println("*******************************");
		System.out.println("voornaam: " + vnaam);
		System.out.println("achternaam: " + anaam);
		System.out.println("tussenvoegsel: " + tvoegsel);
		System.out.println("geslacht: " + geslacht);
		System.out.println("straat: " + straat);
		System.out.println("husinummer: " + huisnummer);
		System.out.println("postcode: " + postcode);
		System.out.println("stad: " + stad);
		System.out.println("telefoonnummer: " + telnr);
		System.out.println("reneningnummer: " + reknr);
		System.out.println("email: " + email);
		System.out.println("wachtwoord: " + wachtwoord);
		System.out.println("wachtwoord2: " + wachtwoord2);
		System.out.println("fotourl: " + fotoUrl);
		System.out.println("locale: " + langnotify);
		System.out.println("*******************************");
		System.out.println("EIND DAO Lid GEGEVENS");

		return bean;
	}

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
			PreparedStatement pstmt = con.prepareStatement("SELECT lidnr ,vnaam,anaam,geslacht,straat,postcode, stad,telnr,reknr,email,beoordeling,fotourl,tvoegsel,wachtwoord,langnotify,rol FROM lid WHERE email = ?");
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
					bean.setBeoordeling(rs.getDouble(11));
					bean.setFotoUrl(rs.getString(12));
					bean.setTvoegsel(rs.getString(13));;
					bean.setWachtwoord(rs.getString(14));
					bean.setLangnotify(rs.getString(15));
					bean.setLocale(rs.getString(15));
					bean.setWachtwoord2(wachtwoord);
					bean.setRol(rs.getInt(16));
					bean.setValid(true);


					//Hieronder wordt de locale vanuit de getter geprint naar de console
					System.out.println("Dit is de locale vanuit de getter van sessie :" + bean.getLocaleStr());


					System.out.println("Dit is de rol van de gebruiker: " + bean.getRol());

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

	/**
	 * Beoordeling voor een lid updaten
	 *
	 * @param beoordeelde
	 * @param beoordeling
	 * @return
	 */
	public Boolean updateBeoordelingLid(int beoordeelde, int beoordeling) {

		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement beoordeelLid;
			String queryString =
					"UPDATE lid "
					+ "SET beoordeling = ("
					+ "("
					+ "		(SELECT beoordeling FROM lid WHERE lidnr = ?) + ? )/ 2"
					+ ");";

			beoordeelLid = currentCon.prepareStatement(queryString);
			beoordeelLid.setInt(1, beoordeelde);
			beoordeelLid.setInt(2, beoordeling);

			System.out.println("+++++++++++++BeoordeelLid+++++++++++++++\n Query = " + queryString + "\n");
			beoordeelLid.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return true;
	}

	//Hier kijken of de gebruiker al bestaat met behyulp van een facebookid
	public Boolean checkDuplicateFacebookID(String facebookid) {

		boolean more = false;

		try {


			Connection con = Dryves.ConnectionManager.getConnection();

			ResultSet rs;
			PreparedStatement pstmt = con.prepareStatement("SELECT facebookid FROM lid  WHERE facebookid=?");

			pstmt.setString(1, facebookid);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				more = true;
			}



		} catch (SQLException e) {
			System.out.println();
		}


		return more;

	}

	// in deze functie kijken we of de email bestaat
	public Boolean checkDuplicate(String email) {
		currentCon = ConnectionManager.getConnection();
		Boolean more = null;
		Statement stmt = null;

		try {
			stmt = currentCon.createStatement();
			String query = "SELECT email FROM Lid WHERE email='" + email + "'";
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				more = true;
			} else {
				more = false;
			}
			currentCon.close();
		} catch (SQLException s) {
			System.out.println("Er kan geen vebinding worden gemaakt met de database." + s);
		}
		return more;
	}

	/**
	 * Opslaan van registratie in de database
	 */
	public Boolean saveRegistratie() {
		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement insertLid;

			String queryString = ("INSERT INTO Lid ("
					+ " vnaam,"
					+ " anaam,"
					+ " tvoegsel,"
					+ " geslacht,"
					+ " straat,"
					+ " postcode,"
					+ " stad,"
					+ " telnr,"
					+ " reknr,"
					+ " email,"
					+ " wachtwoord,"
					+ " fotoUrl,"
					+ " langnotify)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			insertLid = currentCon.prepareStatement(queryString);

			insertLid.setString(1, vnaam);
			insertLid.setString(2, anaam);
			insertLid.setString(3, tvoegsel);
			insertLid.setString(4, geslacht);
			insertLid.setString(5, straat);
			insertLid.setString(6, postcode);
			insertLid.setString(7, stad);
			insertLid.setString(8, telnr);
			insertLid.setInt(9, Integer.parseInt(reknr));
			insertLid.setString(10, email);
			insertLid.setString(11, wachtwoord);
			insertLid.setString(12, fotoUrl);
			insertLid.setString(13, langnotify);

			System.out.println("De query is: " + insertLid);

			insertLid.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;

		}
		return true;

	}

	public Boolean addFacebookLid() {

		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement addfacebook;

			String queryString = ("INSERT INTO lid ( vnaam, anaam,geslacht,straat,postcode,stad,telnr,reknr,email, beoordeling,fotourl,tvoegsel,wachtwoord,langnotify,rol,facebookid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			addfacebook = currentCon.prepareStatement(queryString);

			addfacebook.setString(1, vnaam);
			addfacebook.setString(2, anaam);
			addfacebook.setString(3, "");
			addfacebook.setString(4, "");
			addfacebook.setString(5, "");
			addfacebook.setString(6, "");
			addfacebook.setInt(7, 0);
			addfacebook.setInt(8, 0);
			addfacebook.setString(9, email);
			addfacebook.setInt(10, 0);
			addfacebook.setString(11, "");
			addfacebook.setString(12, "");
			addfacebook.setString(13, "");
			addfacebook.setString(14, "nl_NL");
			addfacebook.setInt(15, 1);
			addfacebook.setString(16, facebookid);

			System.out.println("De query is: " + addfacebook);

			addfacebook.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param beanadmin
	 * @return
	 */
	public void adminLogin(Lid bean) {

		//preparing some objects for connection 
		Statement stmt = null;

		Connection currentCon = Dryves.ConnectionManager.getConnection();

		try {

			PreparedStatement pstmtadmin = currentCon.prepareStatement("SELECT * FROM configuratie WHERE confnr = 1");

			//connect to DB 
			currentCon = ConnectionManager.getConnection();


			stmt = currentCon.createStatement();

			rs = pstmtadmin.executeQuery();

			//rs = stmt.executeQuery(zoekQuery);           
			boolean more = rs.next();

			// if user does not exist set the isValid variable to false
			if (!more) {

				System.out.println("Er zijn geen waardes gevonden in de configuratie tabel");

			} //if user exists set the isValid variable to true
			else if (more) {

				//Hieronder wordt de adminbean gevuld met de waarden uit de configuratietabel
				bean.setAchtergrond(rs.getString(2));
				bean.setRitprijs(rs.getString(3));

				System.out.println("Dit is de waarde uit achtergrond: " + bean.getAchtergrond());
				System.out.println("Dit is de waarde uit ritprijs: " + bean.getRitprijs());

			} else {
				System.out.println("Er zijn geen gegevens gevonden in de configuratietabel");

			}
		} catch (Exception ex) {
			System.out.println("Gegevens ophalen uit de configuratietabel is niet gelukt!" + ex);
		} //some exception handling
		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}


	}

	public Lid enkelLidOphalen(int lidnr, Lid bean) {
		currentCon = ConnectionManager.getConnection();

		PreparedStatement enkelLidOphalen = null;
		String queryString;
		ResultSet resultSet = null;
	

		try {

			queryString = "SELECT * FROM lid WHERE lidnr = ?";

			enkelLidOphalen = currentCon.prepareStatement(queryString);
			enkelLidOphalen.setInt(1, lidnr);

			resultSet = enkelLidOphalen.executeQuery();

		
			if(resultSet.next()){
				bean.setLidnr(resultSet.getInt("lidnr"));
				bean.setVnaam(resultSet.getString("vnaam"));
				bean.setAnaam(resultSet.getString("anaam"));
				bean.setGeslacht(resultSet.getString("geslacht"));
				bean.setStraat(resultSet.getString("straat"));
				bean.setPostcode(resultSet.getString("postcode"));
				bean.setStad(resultSet.getString("stad"));
				bean.setTelnr(resultSet.getString("telnr"));
				bean.setReknr(resultSet.getString("reknr"));
				bean.setEmail(resultSet.getString("email"));
				bean.setBeoordeling(resultSet.getInt("beoordeling"));
				bean.setFotoUrl(resultSet.getString("fotourl"));
				bean.setTvoegsel(resultSet.getString("tvoegsel"));;
				bean.setWachtwoord(resultSet.getString("wachtwoord"));
				bean.setLangnotify(resultSet.getString("langnotify"));
				bean.setRol(resultSet.getInt("rol"));
			}



		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return bean;
	}

	public Lid enkelLidUpdaten(Lid bean) {
		try {
			currentCon = ConnectionManager.getConnection();

			PreparedStatement updateLid;
			String queryString = ("UPDATE lid "
					+ "SET"
					+ " vnaam = ?,"
					+ " anaam = ?,"
					+ " tvoegsel =?,"
					+ " straat = ?,"
					+ " postcode = ?,"
					+ " reknr =?,"
					+ " telnr =?,"
					+ " wachtwoord =?,"
					+ " email =?,"
					+ " stad = ?,"
					+ " geslacht =?,"
					+ " langnotify = ?"
					+ " WHERE lidnr = ?");

			updateLid = currentCon.prepareStatement(queryString);

			updateLid.setString(1, bean.getVnaam());
			updateLid.setString(2, bean.getAnaam());
			updateLid.setString(3, bean.getTvoegsel());
			updateLid.setString(4, bean.getStraat());
			updateLid.setString(5, bean.getPostcode());
			updateLid.setInt(6, Integer.parseInt(bean.getReknr()));
			updateLid.setString(7, bean.getTelnr());
			updateLid.setString(8, bean.getWachtwoord());
			updateLid.setString(9, bean.getEmail());
			updateLid.setString(10, bean.getStad());
			updateLid.setString(11, bean.getGeslacht());
			updateLid.setString(12, bean.getLangnotify());
			updateLid.setInt(13, bean.getLidnr());

			System.out.println("De query is: " + updateLid);

			updateLid.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);

		}

		return bean;
	}

        
      
        
        
        
        
        
	public Lid loginFacebook(String facebookid) {

		Connection con = Dryves.ConnectionManager.getConnection();
		Statement stmt = null;
		ResultSet rs;

		Lid lidfacebook = new Lid();


		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT lidnr ,vnaam,anaam,geslacht,straat,postcode, stad,telnr,reknr,email,beoordeling,fotourl,tvoegsel,wachtwoord,langnotify FROM lid WHERE facebookid = ?");
			pstmt.setString(1, facebookid);
			//connect to DB 
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			rs = pstmt.executeQuery();


			while (rs.next()) {

				//de nieuwe
				lidfacebook.setLidnr(rs.getInt(1));
				lidfacebook.setVnaam(rs.getString(2).toString());
				lidfacebook.setAnaam(rs.getString(3));
				lidfacebook.setGeslacht(rs.getString(4));
				lidfacebook.setStraat(rs.getString(5));
				lidfacebook.setPostcode(rs.getString(6));
				lidfacebook.setStad(rs.getString(7));
				lidfacebook.setTelnr(rs.getString(8));
				lidfacebook.setReknr(rs.getString(9));
				lidfacebook.setEmail(rs.getString(10));
				lidfacebook.setBeoordeling(rs.getInt(11));
				lidfacebook.setFotoUrl(rs.getString(12));
				lidfacebook.setTvoegsel(rs.getString(13));;
				lidfacebook.setWachtwoord(rs.getString(14));
				lidfacebook.setLangnotify(rs.getString(15));
				lidfacebook.setLocale(rs.getString(15));//
				lidfacebook.setWachtwoord2(rs.getString(14));
				lidfacebook.setValid(true);


				//Hieronder wordt de locale vanuit de getter geprint naar de console
				System.out.println("Dit is de locale vanuit de getter van sessie :" + lidfacebook.getLocale());

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

		return lidfacebook;

	}
        
        
    public Lid verstuurWachtwoord(Lid user) {

        Statement stmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String email = user.getEmail();
        
        System.out.println("Dit is het email adres uit de lidDao: " + email);

        try {
            
            currentCon = ConnectionManager.getConnection();
            PreparedStatement verstuurwachtwoord;
            String queryString = "SELECT vnaam, anaam, wachtwoord FROM lid WHERE email =?;";
            
            verstuurwachtwoord = currentCon.prepareStatement(queryString);
            verstuurwachtwoord.setString(1, email);
            resultSet = verstuurwachtwoord.executeQuery();
            
            while (resultSet.next()) {
                
                user.setVnaam(resultSet.getString(1));
                
                System.out.println("Vnaam wijzig wachtwoord: " + user.getVnaam());
                
                user.setAnaam(resultSet.getString(2));
                
                System.out.println("Anaam wijzig wachtwoord: " + user.getAnaam());
                
                user.setWachtwoord(resultSet.getString(3));
                
                System.out.println("Wwoord wijzig wachtwoord: " + user.getWachtwoord());
            
            }

        } catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Foutje, oeps!");
            
        }



        return user;

    }
       
        
        // met deze functie wijzigen we de taal instellingen
        // en= Engels nl= Nederlands
        public void wijzigtaal(String taal, int lidnr){
        
            try {
			currentCon = ConnectionManager.getConnection();

			PreparedStatement updateLid;
			String queryString = ("UPDATE lid "
					+ "SET"
					+ " langnotify = ?"
					
					+ " WHERE lidnr = ?");

			updateLid = currentCon.prepareStatement(queryString);

			updateLid.setString(1, taal);
                        updateLid.setInt(2, lidnr);
			

			System.out.println("De query is: " + updateLid);

			updateLid.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);

		}

		
            
        
        
        }

	private static Locale toString(String locale) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String getVnaam() {
		return vnaam;
	}

	public void setVnaam(String vnaam) {
		this.vnaam = vnaam;
	}

	public String getAnaam() {
		return anaam;
	}

	public void setAnaam(String anaam) {
		this.anaam = anaam;
	}

	public String getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStad() {
		return stad;
	}

	public void setStad(String stad) {
		this.stad = stad;
	}

	public String getTelnr() {
		return telnr;
	}

	public void setTelnr(String telnr) {
		this.telnr = telnr;
	}

	public String getReknr() {
		return reknr;
	}

	public void setReknr(String reknr) {
		this.reknr = reknr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getWachtwoord2() {
		return wachtwoord2;
	}

	public void setWachtwoord2(String wachtwoord2) {
		this.wachtwoord2 = wachtwoord2;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public String getTvoegsel() {
		return tvoegsel;
	}

	public void setTvoegsel(String tvoegsel) {
		this.tvoegsel = tvoegsel;
	}

	public String getLangnotify() {
		return langnotify;
	}

	public void setLangnotify(String langnotify) {
		this.langnotify = langnotify;
	}

	public Hashtable getErrors() {
		return errors;
	}

	public void setErrors(Hashtable errors) {
		this.errors = errors;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public static Connection getCurrentCon() {
		return currentCon;
	}

	public static void setCurrentCon(Connection currentCon) {
		LidDao.currentCon = currentCon;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		LidDao.rs = rs;
	}
}
