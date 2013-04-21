/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.util.Locale;

/**
 *
 * @author RickSpijker
 */
public class Sessie {

	private int lidnr;
	private String email;
	private String wachtwoord;
	private String vnaam;
	private String anaam;
        private Locale locale;
	public boolean valid;

	public int getLidnr() {
		return lidnr;
	}

	public void setLidnr(int lidnr) {
		this.lidnr = lidnr;
	}

	public String getVnaam() {
		return vnaam;
	}

	public void setVnaam(String newVnaam) {
		vnaam = newVnaam;
	}

	public String getAnaam() {
		return anaam;
	}

	public void setAnaam(String newAnaam) {
		anaam = newAnaam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String newWachtwoord) {
		wachtwoord = newWachtwoord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String newEmail) {
		email = newEmail;
	}

        public Locale getLocale() {
            return locale;
        }
        public String getLocaleStr(){
            
            return locale.toString();
        }

        public void setLocale(Locale locale) {
            this.locale = locale;
        }

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		valid = newValid;

	}

    void setLocale(String locale) {
        this.locale = new Locale(locale);
    }

}
