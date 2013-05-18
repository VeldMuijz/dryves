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

    private String datum;
    private String inhoud;
    private int ritnr;
    private int ongelezen;

    public int getOngelezen() {
        return ongelezen;
    }

    public void setOngelezen(int ongelezen) {
        this.ongelezen = ongelezen;
    }
    public int getRitnr() {
        return ritnr;
    }

    public void setRitnr(int ritnr) {
        this.ritnr = ritnr;
    }
 private int afzender;

    public int getAfzender() {
        return afzender;
    }

    public void setAfzender(int afzender) {
        this.afzender = afzender;
    }
    public Berichten() {
    }

    public Berichten(int lidnr, String datum, String inhoud, int berichtid, int ritnr, int afzender, int ongelezen) {
        this.lidnr = lidnr;
       
        this.datum = datum;
        this.inhoud = inhoud;

        this.berichtid = berichtid;
        this.ritnr=ritnr;
        this.afzender=afzender;
        this.ongelezen=ongelezen;
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

   

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
   
            
          
    
}
