package Dryves.Model;
// Generated 26-mrt-2013 21:03:05 by Hibernate Tools 3.2.1.GA

import java.sql.Timestamp;

/**
 * Aankoop generated by hbm2java
 */

public class Aankoop  implements java.io.Serializable {

     private int aankoopnr;
     private int ritnr;
     private int lidnr;
     private int ontmoetingnr;
     private String betaalwijze;
     private Timestamp datum;
     private int factuurnr;
	 private String stringTijd;
	 private String stringDatum;
         private int beoordeeld;
	 

    public Aankoop() {
    }


    /**
     * 
     * @param aankoopnr unieke identifier van een aankoop
     * @param ritnr unieke identifier van een rit
     * @param lidnr unieke identifier van een lid
     * @param ontmoetingnr unieke identifier van een ontmoeting
     * @param factuurnr unieke identifier van een factuur
     */
    public Aankoop(int aankoopnr, int ritnr, int lidnr, int ontmoetingnr, int factuurnr) {
        this.aankoopnr = aankoopnr;
        this.ritnr = ritnr;
        this.lidnr = lidnr;
        this.ontmoetingnr = ontmoetingnr;
        this.factuurnr = factuurnr;
    }
    
    /**
     * 
     * @param aankoopnr unieke identifier van een aankoop
     * @param ritnr unieke identifier van een rit
     * @param lidnr unieke identifier van een lid
     * @param ontmoetingnr unieke identifier van een ontmoeting
     * @param betaalwijze wijze van betalen
     * @param datum datum van de aankoop
     * @param factuurnr unieke identifier van een factuur
     * @param beoordeeld geeft aan of een aankoop is beoordeeld
     */
    
    public Aankoop(int aankoopnr, int ritnr, int lidnr, int ontmoetingnr, String betaalwijze, Timestamp datum, int factuurnr, int beoordeeld) {
       this.aankoopnr = aankoopnr;
       this.ritnr = ritnr;
       this.lidnr = lidnr;
       this.ontmoetingnr = ontmoetingnr;
       this.betaalwijze = betaalwijze;
       this.datum = datum;
       this.factuurnr = factuurnr;
    }
   
    public int getAankoopnr() {
        return this.aankoopnr;
    }
    
    public void setAankoopnr(int aankoopnr) {
        this.aankoopnr = aankoopnr;
    }
    public int getRitnr() {
        return this.ritnr;
    }
    
    public void setRitnr(int ritnr) {
        this.ritnr = ritnr;
    }
    public int getLidnr() {
        return this.lidnr;
    }
    
    public void setLidnr(int lidnr) {
        this.lidnr = lidnr;
    }
    public int getOntmoetingnr() {
        return this.ontmoetingnr;
    }
    
    public void setOntmoetingnr(int ontmoetingnr) {
        this.ontmoetingnr = ontmoetingnr;
    }
    public String getBetaalwijze() {
        return this.betaalwijze;
    }
    
    public void setBetaalwijze(String betaalwijze) {
        this.betaalwijze = betaalwijze;
    }
    public Timestamp getDatum() {
        return this.datum;
    }
    
    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }
    public int getFactuurnr() {
        return this.factuurnr;
    }
    
    public void setFactuurnr(int factuurnr) {
        this.factuurnr = factuurnr;
    }

	public String getStringTijd() {
		return stringTijd;
	}

	public void setStringTijd(String stringTijd) {
		this.stringTijd = stringTijd;
	}

	public String getStringDatum() {
		return stringDatum;
	}

	public void setStringDatum(String stringDatum) {
		this.stringDatum = stringDatum;
	}

    public int getBeoordeeld() {
        return beoordeeld;
    }

    public void setBeoordeeld(int beoordeeld) {
        this.beoordeeld = beoordeeld;
    }
        
        


}


