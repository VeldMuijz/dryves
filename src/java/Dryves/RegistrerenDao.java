/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import static Dryves.RitDao.currentCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heuvenk
 */
public class RegistrerenDao {
    
    	
    
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
     private int beoordeling;
     private String fotoUrl;
     private String tvoegsel;
     private Locale langnotify;
     private Hashtable errors;
     private Locale locale;

	
	/**
	 * Ophalen van alle gegevens uit de servlet voor de rit_plannen.jsp
	 * @param bean
	 * @return 
	 */
	public RegistrerenDao(String vnaam, String anaam, String geslacht, String straat, String huisnummer, String reknr, String telnr, String postcode, String stad, String email, String wachtwoord, String wachtwoord2, String fotoUrl, String tvoegsel, Locale langnotify) {
        
        this.vnaam = vnaam;
        this.anaam = anaam;
        this.tvoegsel = tvoegsel;
        this.geslacht = geslacht;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.reknr = reknr;
        this.telnr = telnr;
        this.postcode = postcode;    
        this.stad = stad;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.wachtwoord2 = wachtwoord2;
        this.beoordeling = beoordeling;
        this.fotoUrl = fotoUrl;       
        this.locale = langnotify;

    }

	
		setVnaam(bean.getVnaam());
		setAnaam(bean.getAnaam());
                setTvoegsel(bean.getTvoegsel());
		setStraat(bean.getStraat());
		setHuisnummer(bean.getHuisnummer());
		setPostcode(bean.setPostcode());
		setStad(bean.getStad());
		getTelnr(bean.setTelnr());
		setReknr(bean.getReknr());
		setEmail(bean.getEmail());
		setWachtwoord(bean.getWachtwoord());
                setWachtwoord2(bean.getWachtwoord2());
                setFotoUrl(bean.getFotoUrl());
                setLangnotify(bean.getLangnotify());
                

		System.out.println("RIT DAO GEGEVENS:");
		System.out.println("*******************************");
		System.out.println("voornaam" + vnaam);
		System.out.println("achternaam: " + anaam);
		System.out.println("tussenvoegsel: " + tvoegsel);
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
		System.out.println("EIND DAO RIT GEGEVENS");

		Registreren();

		return bean;
	}

	
	
	/**
	 * Opslaan van rit in de database
	 */
	private void saveRegistreren() {
		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement insertLid;

			String queryString = (
					"INSERT INTO Rit ("
					+ " vnaam,"
					+ " anaam,"
					+ " tvoegsel,"
					+ " straat,"
					+ " huisnummer,"
					+ " postcode,"
					+ " stad,"
					+ " telnr,"
					+ " reknr,"
					+ " email,"
					+ " wachtwoord)"
					+ " wachtwoord2"
                                        + " fotoUrl"
                                        + " locale"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
					);


			insertRit = currentCon.prepareStatement(queryString);


			insertRit.setString(1, vnaam);
			insertRit.setString(2, anaam);
			insertRit.setString(3, tvoegsel);
                        insertRit.setString(3, straat);
                        insertRit.setString(3, huisnummer);
                        insertRit.setString(3, postcode);
                        insertRit.setString(3, stad);
                        insertRit.setString(3, telnr);
                        insertRit.setString(3, reknr);
                        insertRit.setString(3, email);
                        insertRit.setString(3, wachtwoord);
                        insertRit.setString(3, wachtwoord2);
                        insertRit.setString(3, fotoUrl);
                        insertRit.setString(3, locale);
                        


			System.out.println("De query is: " + insertRit);

			insertRit.executeQuery();
			
		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);
			success = false;
			System.out.println("Var Success = " + success);
		} 
		success = true;
	}
    
}
