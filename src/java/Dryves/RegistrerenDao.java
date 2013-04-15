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
     private String fotoUrl;
     private String tvoegsel;
     private String langnotify;
     private Hashtable errors;
     private boolean success;
    

	
	/**
	 * Ophalen van alle gegevens uit de servlet voor de registreren.jsp
	 * @param bean
	 * @return 
	 */
	public Lid RegistrerenDao( Lid bean) {
       
            
                setVnaam(bean.getVnaam());
		setAnaam(bean.getAnaam());
                setTvoegsel(bean.getTvoegsel());
		setStraat(bean.getStraat());
		setHuisnummer(bean.getHuisnummer());
		setPostcode(bean.getPostcode());
		setStad(bean.getStad());
		setTelnr(bean.getTelnr());
		setReknr(bean.getReknr());
		setEmail(bean.getEmail());
		setWachtwoord(bean.getWachtwoord());               
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

		saveRegistratie();

		return bean;
	}

	
	
	/**
	 * Opslaan van registratie in de database
	 */
	private void saveRegistratie() {
		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement insertLid;

			String queryString = (
					"INSERT INTO Rit ("
					+ " vnaam,"
					+ " anaam,"
					+ " tvoegsel,"
                                        + " geslacht,"
					+ " straat,"
					+ " huisnummer,"
					+ " postcode,"
					+ " stad,"
					+ " telnr,"
					+ " reknr,"
					+ " email,"
					+ " wachtwoord,"
					+ " fotoUrl,"
                                        + " langnotify,)"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
					);


			insertLid = currentCon.prepareStatement(queryString);


			insertLid.setString(1, vnaam);
			insertLid.setString(2, anaam);
			insertLid.setString(3, tvoegsel);
                        insertLid.setString(4, geslacht);
                        insertLid.setString(5, straat);
                        insertLid.setString(6, huisnummer);
                        insertLid.setString(7, postcode);
                        insertLid.setString(8, stad);
                        insertLid.setString(9, telnr);
                        insertLid.setString(10, reknr);
                        insertLid.setString(11, email);
                        insertLid.setString(12, wachtwoord);
                        insertLid.setString(13, fotoUrl);
                        insertLid.setString(14, langnotify);
                        


			System.out.println("De query is: " + insertLid);

			insertLid.executeQuery();
			
		} catch (SQLException ex) {
			Logger.getLogger(RegistrerenDao.class.getName()).log(Level.SEVERE, null, ex);
			success = false;
			System.out.println("Var Success = " + success);
		} 
		success = true;
        
	}
        
    public String getErrorMsg(String s) {
        String errorMsg = (String) errors.get(s.trim());
        return (errorMsg == null) ? "" : errorMsg;
    }
         
    public String isRbSelected(String s) {
        return (geslacht.equals(s)) ? "checked" : "";   

    }
    public String getVnaam() {
        return this.vnaam;
    }
    
    public void setVnaam(String vnaam) {
        this.vnaam = vnaam;
    }
    public String getAnaam() {
        return this.anaam;
    }
    
    public void setAnaam(String anaam) {
        this.anaam = anaam;
    }
    public String getGeslacht() {
        return this.geslacht;
    }
    
    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }
    public String getStraat() {
        return this.straat;
    }
    
    public void setStraat(String straat) {
        this.straat = straat;
    }
    public String getHuisnummer() {
        return this.huisnummer;
            }
    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }
    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getStad() {
        return this.stad;
    }
    
    public void setStad(String stad) {
        this.stad = stad;
    }
    public String getTelnr() {
        return this.telnr;
    }
    
    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }
    public String getReknr() {
        return this.reknr;
    }
    
    public void setReknr(String reknr) {
        this.reknr = reknr;
    }
    public String getEmail() {
        return this.email;
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
    public String getFotoUrl() {
        return this.fotoUrl;
    }
    
    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
    public String getTvoegsel() {
        return this.tvoegsel;
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
    
    public void setErrors(String key, String msg) {
        errors.put(key, msg);
        
    } 
    public Boolean getSuccess() {
		return success;
    }

    public void setSuccess(Boolean success) {
		this.success = success;
        
    }
}