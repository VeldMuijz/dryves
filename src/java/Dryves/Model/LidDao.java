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

	static Connection currentCon;
	static ResultSet rs;
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
	private String facebookid;

	/**
	 * Ophalen van alle gegevens uit de servlet voor de klasse LidDao
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

	/**
	 *
	 * @param bean2
	 * @return
	 */
	public static Lid login(Lid bean2) {
		//Bereid de database connectie voor
		PreparedStatement stmt = null;
		currentCon = ConnectionManager.getConnection();
		rs = null;
		String email = bean2.getEmail();
		String wachtwoord = bean2.getWachtwoord();
		Lid bean = new Lid();
		String queryString = "SELECT lidnr, vnaam, anaam, geslacht, straat, postcode, stad, telnr, reknr, email, beoordeling, fotourl, tvoegsel, wachtwoord, langnotify, rol "
				+ "FROM lid "
				+ "WHERE email = ?";

		// "System.out.println" prints in the console; Normally used to trace the process
		System.out.println("Your user name is " + email);
		System.out.println("Your wachtwoord is " + wachtwoord);

		try {
			stmt = currentCon.prepareStatement(queryString);
			stmt.setString(1, email);
			rs = stmt.executeQuery();

			boolean more = rs.next();

			// if user does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, je bent niet ingelogd. Registreer eerst alstublieft.");
				bean.setValid(false);
			} //if user exists set the isValid variable to true
			else if (more) {
				//Hieronder wordt het wachtwoord van de user opgehaald uit de database 
				String wachtwoordUser = rs.getString(14);

				//Wanneer het wachtwoord overenkomt met het wachtwoord in de database
				//vult deze if methode het lidobject
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

				} else {
					System.out.println("Sorry u bent niet geregistreerd.");
					bean.setValid(false);
				}
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}
		//Doe dit altijd, als het goed gaat of wanneer het fout gaat
		if (rs != null) {
			try {
				//sluit resultset af
				rs.close();
			} catch (SQLException ignore) {
			}
		}
		if (stmt != null) {
			//sluit preparedStatement
			try {
				stmt.close();
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
		//geef het lidobject terug
		return bean;
	}

	//Hier kijken of de gebruiker al bestaat met behyulp van een facebookid
	/**
	 * Deze methode controleert of dit facebookid al bekend is in de database
	 *
	 * @param facebookid
	 * @return True als dit facebookid al bestaat
	 */
	public Boolean checkDuplicateFacebookID(String facebookid) {
		//Bereid de database connectie voor
		PreparedStatement stmt = null;
		currentCon = ConnectionManager.getConnection();
		rs = null;
		String queryString = "SELECT facebookid FROM lid  WHERE facebookid = ?";
		boolean idCheck = false;

		try {
			stmt = currentCon.prepareStatement(queryString);

			stmt.setString(1, facebookid);

			rs = stmt.executeQuery();
			if (rs.next()) {

				idCheck = true;
			}

		} catch (SQLException e) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (stmt != null) {
				//sluit preparedStatement
				try {
					stmt.close();
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


		return idCheck;

	}

	// in deze functie kijken we of de email bestaat
	/**
	 * Deze methode controleert of een emailadres al in de database voorkomt
	 *
	 * @param email string die het emailadres bevat
	 * @return True als het emailadres voorkomt, false wanneer deze niet
	 * voorkomt
	 */
	public Boolean checkDuplicate(String email) {
		currentCon = ConnectionManager.getConnection();
		rs = null;
		Boolean more = null;
		PreparedStatement stmt = null;

		String queryString = "SELECT email "
				+ "FROM Lid "
				+ "WHERE email='" + email + "'";
		try {
			stmt = currentCon.prepareStatement(queryString);
			rs = stmt.executeQuery(queryString);

			if (rs.next()) {
				more = true;
			} else {
				more = false;
			}
			currentCon.close();
		} catch (SQLException e) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, e);

		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (stmt != null) {
				//sluit preparedStatement
				try {
					stmt.close();
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
		return more;
	}

	/**
	 * Opslaan van lidgegevens in de tabel lid.
	 *
	 * @return True als registratie gelukt is, false wanneer dit mislukt
	 */
	public Boolean saveRegistratie() {
		currentCon = ConnectionManager.getConnection();
		PreparedStatement insertLid = null;

		try {

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
		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (insertLid != null) {
				//sluit preparedStatement
				try {
					insertLid.close();
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
	 * Voeg een lid in de database to die inlogt met een Facebook account
	 *
	 * @return True als registratie gelukt is, false wanneer dit mislukt is
	 */
	public Boolean addFacebookLid() {
		currentCon = ConnectionManager.getConnection();
		PreparedStatement addFacebook = null;

		String queryString = ("INSERT INTO lid ( vnaam,"
				+ " anaam,"
				+ "geslacht,"
				+ "straat,"
				+ "postcode,"
				+ "stad,"
				+ "telnr,"
				+ "reknr,"
				+ "email, "
				+ "beoordeling,"
				+ "fotourl,"
				+ "tvoegsel,"
				+ "wachtwoord,"
				+ "langnotify,"
				+ "rol,"
				+ "facebookid)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");


		try {

			addFacebook = currentCon.prepareStatement(queryString);

			addFacebook.setString(1, vnaam);
			addFacebook.setString(2, anaam);
			addFacebook.setString(3, "");
			addFacebook.setString(4, "");
			addFacebook.setString(5, "");
			addFacebook.setString(6, "");
			addFacebook.setInt(7, 0);
			addFacebook.setInt(8, 0);
			addFacebook.setString(9, email);
			addFacebook.setInt(10, 0);
			addFacebook.setString(11, "");
			addFacebook.setString(12, "");
			addFacebook.setString(13, "");
			addFacebook.setString(14, "nl_NL");
			addFacebook.setInt(15, 1);
			addFacebook.setString(16, facebookid);

			System.out.println("De query is: " + addFacebook);

			addFacebook.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;

		} finally {
			//Doe dit altijd, als het goed gaat of wanneer het fout gaat
			if (rs != null) {
				try {
					//sluit resultset af
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (addFacebook != null) {
				//sluit preparedStatement
				try {
					addFacebook.close();
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
	 * Deze methode verzorgt het inloggen als een admin
	 *
	 * @param bean Lid object met admin attributen
	 *
	 */
	public void adminLogin(Lid bean) {

		//preparing some objects for connection 
		Statement stmt = null;

		currentCon = ConnectionManager.getConnection();
		PreparedStatement pstmtadmin = null;
		String queryString = "SELECT * "
				+ "FROM configuratie "
				+ "WHERE confnr = 1;";
		try {

			pstmtadmin = currentCon.prepareStatement(queryString);

			rs = pstmtadmin.executeQuery();

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
			System.out.println("Gegevens ophalen uit de configuratietabel is niet gelukt!");
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
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

	/**
	 * Deze methode haalt een lid met de waardes die meegegeven worden in de
	 * bean
	 *
	 * @param lidnr bevat integer lidnr
	 * @param bean bevat het object lid
	 * @return object lid
	 */
	public Lid enkelLidOphalen(int lidnr, Lid bean) {
		currentCon = ConnectionManager.getConnection();
		PreparedStatement enkelLidOphalen = null;
		String queryString;
		rs = null;

		try {

			queryString = "SELECT * "
					+ "FROM lid "
					+ "WHERE lidnr = ?";

			enkelLidOphalen = currentCon.prepareStatement(queryString);
			enkelLidOphalen.setInt(1, lidnr);

			rs = enkelLidOphalen.executeQuery();

			if (rs.next()) {
				bean.setLidnr(rs.getInt("lidnr"));
				bean.setVnaam(rs.getString("vnaam"));
				bean.setAnaam(rs.getString("anaam"));
				bean.setGeslacht(rs.getString("geslacht"));
				bean.setStraat(rs.getString("straat"));
				bean.setPostcode(rs.getString("postcode"));
				bean.setStad(rs.getString("stad"));
				bean.setTelnr(rs.getString("telnr"));
				bean.setReknr(rs.getString("reknr"));
				bean.setEmail(rs.getString("email"));
				bean.setBeoordeling(rs.getDouble("beoordeling"));
				bean.setFotoUrl(rs.getString("fotourl"));
				bean.setTvoegsel(rs.getString("tvoegsel"));;
				bean.setWachtwoord(rs.getString("wachtwoord"));
				bean.setLangnotify(rs.getString("langnotify"));
				bean.setRol(rs.getInt("rol"));
			}

		} catch (Exception ex) {
			System.out.println("Gegevens ophalen uit de configuratietabel is niet gelukt!");
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
		} //some exception handling
		finally {

			if (enkelLidOphalen != null) {
				try {
					enkelLidOphalen.close();
				} catch (Exception e) {
				}
				enkelLidOphalen = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return bean;
	}

	/**
	 * Deze methode update een lid in de database
	 *
	 * @param bean object lid met alle waardes voor een lid
	 * @return het object lid met de laatste waardes
	 */
	public Lid enkelLidUpdaten(Lid bean) {
		currentCon = ConnectionManager.getConnection();
		PreparedStatement updateLid = null;
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

		try {

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

		} catch (Exception ex) {
			System.out.println("Gegevens ophalen uit de configuratietabel is niet gelukt!");
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
		} //some exception handling
		finally {

			if (updateLid != null) {
				try {
					updateLid.close();
				} catch (Exception e) {
				}
				updateLid = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return bean;
	}

	/**
	 * Deze methode verzorgt het inloggen met een facebookID
	 *
	 * @param facebookid het facebookid voor een user
	 * @return het object lid met alle waardes voor een facebookaccount
	 */
	public Lid loginFacebook(String facebookid) {

		currentCon = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		rs = null;
		String queryString = "SELECT lidnr,vnaam,anaam,geslacht,straat,postcode,"
				+ " stad,telnr,reknr,email,beoordeling,fotourl,tvoegsel,"
				+ "wachtwoord,langnotify "
				+ "FROM lid "
				+ "WHERE facebookid = ?";

		Lid lidfacebook = new Lid();


		try {
			pstmt = currentCon.prepareStatement(queryString);
			pstmt.setString(1, facebookid);


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
			System.out.println("Log In failed: An Exception has occurred! ");
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
		} //some exception handling
		finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				pstmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return lidfacebook;

	}

	/**
	 * Deze methode haalt het wachtwoord van een user op dit wordt bijvoorbeeld
	 * gebruikt om bij een wachtwoord vergeten functie
	 *
	 * @param user het object Lid
	 * @return geef het object Lid terug
	 */
	public Lid verstuurWachtwoord(Lid user) {
		currentCon = ConnectionManager.getConnection();
		PreparedStatement verstuurwachtwoord = null;
		String queryString = "SELECT vnaam, anaam, wachtwoord FROM lid WHERE email =?;";
		rs = null;
		String email = user.getEmail();

		try {

			verstuurwachtwoord = currentCon.prepareStatement(queryString);
			verstuurwachtwoord.setString(1, email);
			rs = verstuurwachtwoord.executeQuery();

			while (rs.next()) {

				user.setVnaam(rs.getString(1));

				System.out.println("Vnaam wijzig wachtwoord: " + user.getVnaam());

				user.setAnaam(rs.getString(2));

				System.out.println("Anaam wijzig wachtwoord: " + user.getAnaam());

				user.setWachtwoord(rs.getString(3));

				System.out.println("Wwoord wijzig wachtwoord: " + user.getWachtwoord());

			}

		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! ");
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
		} //some exception handling
		finally {

			if (verstuurwachtwoord != null) {
				try {
					verstuurwachtwoord.close();
				} catch (Exception e) {
				}
				verstuurwachtwoord = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}


		return user;

	}

	// met deze functie wijzigen we de taal instellingen
	// en= Engels nl= Nederlands/**
	/**
	 * Deze methode wijzig de taal voor een user
	 * Deze methode wordt gebruikt als er op een vlaggetje gedrukt wordt 
	 * om de taal op een pagina en in de database voor een lid aan te passen
	 * @param taal locale voor een user, nl_NL of en_GB
	 * @param lidnr lidnummer voor een lid
	 */
	public void wijzigtaal(String taal, int lidnr) {

		currentCon = ConnectionManager.getConnection();

			PreparedStatement wijzigTaal = null;
			String queryString = ("UPDATE lid "
					+ "SET"
					+ " langnotify = ?"
					+ " WHERE lidnr = ?");

		try {
			
			wijzigTaal = currentCon.prepareStatement(queryString);

			wijzigTaal.setString(1, taal);
			wijzigTaal.setInt(2, lidnr);


			System.out.println("De query is: " + wijzigTaal);

			wijzigTaal.executeUpdate();

		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! ");
			Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
		} //some exception handling
		finally {

			if (wijzigTaal != null) {
				try {
					wijzigTaal.close();
				} catch (Exception e) {
				}
				wijzigTaal = null;
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
