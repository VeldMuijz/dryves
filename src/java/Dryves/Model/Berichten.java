/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import java.sql.Timestamp;

/**
 *
 * @author H
 */
public class Berichten {

	private int lidnr;
	private String stringDatum;
	private String stringTijd;
	private Timestamp datum;
	private String inhoudbericht;
	private int ritnr;
	private int ongelezen;
	private int afzender;
	private int gelezen;
	private String onderwerp;
	private int berichtnr;

	public Berichten() {
	}
        
        /**
         * 
         * @param lidnr unieke identifier van een lid
         * @param stringDatum datum van een bericht in String formaat
         * @param datum datum van een bericht
         * @param inhoudbericht inhoud van het bericht
         * @param ritnr unieke identifier van een rit
         * @param ongelezen indicatie of een bericht ongelezen is of niet
         * @param afzender afzender van een bericht
         * @param gelezen indicatie of een bericht gelezen is of niet
         * @param onderwerp onderwerp van een bericht
         * @param berichtnr unieke identifier van een bericht
         * @param berichtid unieke identifier van een bericht
         */

	public Berichten(int lidnr, String stringDatum, Timestamp datum, String inhoudbericht, int ritnr, int ongelezen, int afzender, int gelezen, String onderwerp, int berichtnr, int berichtid) {
		this.lidnr = lidnr;
		this.stringDatum = stringDatum;
		this.datum = datum;
		this.inhoudbericht = inhoudbericht;
		this.ritnr = ritnr;
		this.ongelezen = ongelezen;
		this.afzender = afzender;
		this.gelezen = gelezen;
		this.onderwerp = onderwerp;
		this.berichtnr = berichtnr;
		this.berichtid = berichtid;
	}
	private int berichtid;

	public int getBerichtid() {
		return berichtid;
	}

	public void setBerichtid(int berichtid) {
		this.berichtid = berichtid;
	}

	public String getInhoud() {
		return inhoudbericht;
	}

	public void setInhoud(String inhoud) {
		this.inhoudbericht = inhoud;
	}

	public int getLidnr() {
		return lidnr;
	}

	public void setLidnr(int lidnr) {
		this.lidnr = lidnr;
	}

	public String getStringDatum() {
		return stringDatum;
	}

	public void setStringDatum(String stringDatum) {
		this.stringDatum = stringDatum;
	}

	public Timestamp getDatum() {
		return datum;
	}

	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}

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

	public int getAfzender() {
		return afzender;
	}

	public void setAfzender(int afzender) {
		this.afzender = afzender;
	}

	public String getInhoudbericht() {
		return inhoudbericht;
	}

	public void setInhoudbericht(String inhoudbericht) {
		this.inhoudbericht = inhoudbericht;
	}

	public int getGelezen() {
		return gelezen;
	}

	public void setGelezen(int gelezen) {
		this.gelezen = gelezen;
	}

	public String getOnderwerp() {
		return onderwerp;
	}

	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}

	public int getBerichtnr() {
		return berichtnr;
	}

	public void setBerichtnr(int berichtnr) {
		this.berichtnr = berichtnr;
	}

	public String getStringTijd() {
		return stringTijd;
	}

	public void setStringTijd(String stringTijd) {
		this.stringTijd = stringTijd;
	}
	
}
