package Dryves.Model;
// Generated 26-mrt-2013 21:03:05 by Hibernate Tools 3.2.1.GA

import java.util.Hashtable;
import java.util.Locale;




/**
 * Lid generated by hbm2java
 */
public class Lid  implements java.io.Serializable {


    private int lidnr;
   private String facebookid;
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
     private double beoordeling;
     private String fotoUrl;
     private String tvoegsel;
     private Hashtable errors;
     private String langnotify;
     private String LocaleStr;
     private String Locale;
     private Locale locale;
     private int rol;

      // Gegevens voor adminpagina
     private String achtergrond;
     private String ritprijs;
     
    public String getFacebookid() {
        return facebookid;
    }

    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }
    

	
    public Lid(int lidnr, String vnaam, String anaam, String geslacht, String straat, String huisnummer, String reknr, String telnr, String postcode, String stad, String email, String wachtwoord, String wachtwoord2, double beoordeling, String fotoUrl, String tvoegsel, String langnotify, int rol) {
        this.lidnr = lidnr;
        this.vnaam = vnaam;
        this.anaam = anaam;
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
        this.tvoegsel = tvoegsel;
        this.langnotify = langnotify;
        this.rol = rol;


    }

    public Lid() {
        
        //Deze constructor is leeg, deze wordt gebruikt door registratie.java
        
        
    }

    public boolean validate() {
        boolean bool = true;
        if (vnaam.equals("")) {
            errors.put("voornaam", "Typ hier uw voornaam");
            vnaam = "";
            bool = false;
        }
        if (anaam.equals("")) {
            errors.put("achternaam", "Typ hier uw achternaam");
            anaam = "";
            bool = false;
                    }
        if (tvoegsel.equals("")) {
            errors.put("tussenvoegsel", "Typ hier uw tussenvoegsel");
            tvoegsel = "";
            bool = false;
        }
                
        if (reknr.equals("") || reknr.length() != 6) {
            errors.put("rekeningnummer", "Typ een geldige rekening nummer");
            reknr = "";
            bool = false;
        } else {
            try {
                int x = Integer.parseInt(reknr);
            } catch (NumberFormatException e) {
                errors.put("rekeningnummer", "Typ een geldige rekening nummer");
                reknr = "";
                bool = false;
            }
        }
        
        if (email.equals("") || (email.indexOf('@') == -1)) {
            errors.put("email", "Typ een geldig e-mail adres");
            email = "";
            bool = false;
        }
        if (wachtwoord.equals("")) {
            errors.put("wachtwoord", "Typ een geldig wachtword");
            wachtwoord = "";
            bool = false;
        }
        if (!wachtwoord2.equals("") && (wachtwoord2.equals("")
                || !wachtwoord.equals(wachtwoord2))) {
            errors.put("wachtwoord", "Bevestig uw wachtwoord");
            wachtwoord2 = "";
            bool = false;
        }
        if (straat.equals("")) {
            errors.put("straat", "Typ hier uw straatnaam");
            straat = "";
            bool = false;
        }
              
        if (huisnummer.equals("")) {
            errors.put("huisnummer", "Typ hier uw huisnummer");
            huisnummer = "";
            bool = false;
            
        }
        if (stad.equals("")) {
            errors.put("stad", "Typ hier de naam van uw woonplaats");
            stad = "";
            bool = false;
            
        }
        if (telnr.equals("")) {
            errors.put("telefoonnummer", "Typ hier uw telefoonnummer");
            telnr = "";
            bool = false;    
        }
        if (postcode.equals("") || postcode.length() != 6) {
            errors.put("postcode", "Typ een geldige postcode");
            postcode = "";
            bool = false;
        } else {
            try {
                int x = Integer.parseInt(postcode);
            } catch (NumberFormatException e) {
                errors.put("postcode", "Typ een geldige postcode");
                postcode = "";
                bool = false;
            }
        }
        return bool;
        }

        
    public String getErrorMsg(String s) {
        String errorMsg = (String) errors.get(s.trim());
        return (errorMsg == null) ? "" : errorMsg;
    }
         
    public String isRbSelected(String s) {
        return (geslacht.equals(s)) ? "checked" : "";   
    }
    public int getLidnr() {
        return this.lidnr;
    }
    
    public void setLidnr(int lidnr) {
        this.lidnr = lidnr;
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

    public void setWachtwoord2(String wachtwoord) {
        this.wachtwoord2 = wachtwoord;
    }
    public double getBeoordeling() {
        return this.beoordeling;
    }
    
    public void setBeoordeling(double beoordeling) {
        this.beoordeling = beoordeling;
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
        return this.langnotify;
    }

    public void setLangnotify(String langnotify) {
        this.langnotify = langnotify.toString();
    }
    
    public void setErrors(String key, String msg) {
        errors.put(key, msg);
        
    }
    
   public String getLocaleStr(){   
            return locale.toString();
        }

    public void setLocaleStr(String LocaleStr) {
        this.LocaleStr = LocaleStr.toString();
    }
    
        public String getLocale() {
        return Locale;
    }

    public void setLocale(String locale) {
        this.locale = new Locale(locale);
    }
    
     private boolean Valid;

    public boolean isValid() {
        return Valid;
    }

    public void setValid(boolean Valid) {
        this.Valid = Valid;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getAchtergrond() {
        return achtergrond;
    }

    public void setAchtergrond(String achtergrond) {
        this.achtergrond = achtergrond;
    }

    public String getRitprijs() {
        return ritprijs;
    }

    public void setRitprijs(String ritprijs) {
        this.ritprijs = ritprijs;
    }
    


}
