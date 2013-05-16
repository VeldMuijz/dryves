/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

/**
 *
 * @author H
 */
public class Berichten {
     private int lidnr;
    private String onderwerp;
    private String datum;
    private String inhoud;
    private int ritnr;

    public int getRitnr() {
        return ritnr;
    }

    public void setRitnr(int ritnr) {
        this.ritnr = ritnr;
    }

    public Berichten() {
    }

    public Berichten(int lidnr, String onderwerp, String datum, String inhoud, int berichtid, int ritnr) {
        this.lidnr = lidnr;
        this.onderwerp = onderwerp;
        this.datum = datum;
        this.inhoud = inhoud;

        this.berichtid = berichtid;
        this.ritnr=ritnr;
    }
     private int berichtid;

    public int getBerichtid() {
        return berichtid;
    }

    public void setBerichtid(int berichtid) {
        this.berichtid = berichtid;
    }
    

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }
    
    
    

    

    public int getLidnr() {
        return lidnr;
    }

    public void setLidnr(int lidnr) {
        this.lidnr = lidnr;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
   
            
          
    
}
