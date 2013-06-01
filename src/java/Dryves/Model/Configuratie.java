package Dryves.Model;
// Generated 26-mrt-2013 21:03:05 by Hibernate Tools 3.2.1.GA



/**
 * Configuratie generated by hbm2java
 */
public class Configuratie  implements java.io.Serializable {


     private int confnr;
     private String confnaam;
     private String confwaarde;

    public Configuratie() {
    }

    /**
     * 
     * @param confnr unieke identifier van configuratie
     * @param confnaam naam van het configuratie item
     * @param confwaarde waarde van het configuratie item
     */
    
    public Configuratie(int confnr, String confnaam, String confwaarde) {
       this.confnr = confnr;
       this.confnaam = confnaam;
       this.confwaarde = confwaarde;
    }
   
    public int getConfnr() {
        return this.confnr;
    }
    
    public void setConfnr(int confnr) {
        this.confnr = confnr;
    }
    public String getConfnaam() {
        return this.confnaam;
    }
    
    public void setConfnaam(String confnaam) {
        this.confnaam = confnaam;
    }
    public String getConfwaarde() {
        return this.confwaarde;
    }
    
    public void setConfwaarde(String confwaarde) {
        this.confwaarde = confwaarde;
    }




}


