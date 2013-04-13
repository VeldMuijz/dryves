package Dryves;
// Generated 26-mrt-2013 21:03:05 by Hibernate Tools 3.2.1.GA


import java.sql.Timestamp;
import java.util.Date;

/**
 * Rit generated by hbm2java
 */
public class Rit  implements java.io.Serializable {


     private long ritnr;
     private int lidnr;
     private String startpunt;
     private String eindpunt;
     private String waypoint;
     private double afstand;
     private double prijs;
     private int gekocht;
	 private Timestamp datum;
     //private Date datum;
	 private int zitplaatsen;
     private String brandstof;
     private int aangeboden;

    public Rit() {
    }

	
    public Rit(long ritnr, int lidnr, String startpunt, String eindpunt, double afstand, double prijs, int gekocht, Timestamp datum, int zitplaatsen, int aangeboden) {
        this.ritnr = ritnr;
        this.lidnr = lidnr;
        this.startpunt = startpunt;
        this.eindpunt = eindpunt;
        this.afstand = afstand;
        this.prijs = prijs;
        this.gekocht = gekocht;
        this.datum = datum;
        this.zitplaatsen = zitplaatsen;
        this.aangeboden = aangeboden;
    }
    public Rit(long ritnr, int lidnr, String startpunt, String eindpunt, String waypoint, double afstand, double prijs, int gekocht, Timestamp datum, int zitplaatsen, String brandstof, int aangeboden) {
       this.ritnr = ritnr;
       this.lidnr = lidnr;
       this.startpunt = startpunt;
       this.eindpunt = eindpunt;
       this.waypoint = waypoint;
       this.afstand = afstand;
       this.prijs = prijs;
       this.gekocht = gekocht;
       this.datum = datum;
       this.zitplaatsen = zitplaatsen;
       this.brandstof = brandstof;
       this.aangeboden = aangeboden;
    }
   
    public long getRitnr() {
        return this.ritnr;
    }
    
    public void setRitnr(long ritnr) {
        this.ritnr = ritnr;
    }
    public int getLidnr() {
        return this.lidnr;
    }
    
    public void setLidnr(int lidnr) {
        this.lidnr = lidnr;
    }
    public String getStartpunt() {
        return this.startpunt;
    }
    
    public void setStartpunt(String startpunt) {
        this.startpunt = startpunt;
    }
    public String getEindpunt() {
        return this.eindpunt;
    }
    
    public void setEindpunt(String eindpunt) {
        this.eindpunt = eindpunt;
    }
    public String getWaypoint() {
        return this.waypoint;
    }
    
    public void setWaypoint(String waypoint) {
        this.waypoint = waypoint;
    }
    public double getAfstand() {
        return this.afstand;
    }
    
    public void setAfstand(double afstand) {
        this.afstand = afstand;
    }
    public double getPrijs() {
        return this.prijs;
    }
    
    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
    public int getGekocht() {
        return this.gekocht;
    }
    
    public void setGekocht(int gekocht) {
        this.gekocht = gekocht;
    }
    public Timestamp getDatum() {
        return this.datum;
    }
    
    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }
    public int getZitplaatsen() {
        return this.zitplaatsen;
    }
    
    public void setZitplaatsen(int zitplaatsen) {
        this.zitplaatsen = zitplaatsen;
    }
    public String getBrandstof() {
        return this.brandstof;
    }
    
    public void setBrandstof(String brandstof) {
        this.brandstof = brandstof;
    }
    public int getAangeboden() {
        return this.aangeboden;
    }
    
    public void setAangeboden(int aangeboden) {
        this.aangeboden = aangeboden;
    }




}


