package Dryves;
// Generated 26-mrt-2013 21:03:05 by Hibernate Tools 3.2.1.GA



/**
 * Lid generated by hbm2java
 */
public class Lid  implements java.io.Serializable {


     private int lidnr;
     private String vnaam;
     private String anaam;
     private char geslacht;
     private String adres;
     private String postcode;
     private String stad;
     private String telnr;
     private Integer reknr;
     private String email;
     private String wachtwoord;
     private int beoordeling;
     private String fotoUrl;
     private String tvoegsel;

    public Lid() {
    }

	
    public Lid(int lidnr, String vnaam, String anaam, char geslacht, String adres, String postcode, String stad, String email, String wachtwoord, int beoordeling, String fotoUrl) {
        this.lidnr = lidnr;
        this.vnaam = vnaam;
        this.anaam = anaam;
        this.geslacht = geslacht;
        this.adres = adres;
        this.postcode = postcode;
        this.stad = stad;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.beoordeling = beoordeling;
        this.fotoUrl = fotoUrl;
    }
    public Lid(int lidnr, String vnaam, String anaam, char geslacht, String adres, String postcode, String stad, String telnr, Integer reknr, String email, String wachtwoord, int beoordeling, String fotoUrl, String tvoegsel) {
       this.lidnr = lidnr;
       this.vnaam = vnaam;
       this.anaam = anaam;
       this.geslacht = geslacht;
       this.adres = adres;
       this.postcode = postcode;
       this.stad = stad;
       this.telnr = telnr;
       this.reknr = reknr;
       this.email = email;
       this.wachtwoord = wachtwoord;
       this.beoordeling = beoordeling;
       this.fotoUrl = fotoUrl;
       this.tvoegsel = tvoegsel;
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
    public char getGeslacht() {
        return this.geslacht;
    }
    
    public void setGeslacht(char geslacht) {
        this.geslacht = geslacht;
    }
    public String getAdres() {
        return this.adres;
    }
    
    public void setAdres(String adres) {
        this.adres = adres;
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
    public Integer getReknr() {
        return this.reknr;
    }
    
    public void setReknr(Integer reknr) {
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
    
    public int getBeoordeling() {
        return this.beoordeling;
    }
    
    public void setBeoordeling(int beoordeling) {
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




}


