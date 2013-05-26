/*
 * To change this template; choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

import java.sql.Timestamp;

/**
 *
 * @author jeroen
 */
public class Beoordeling {

	private int beoordelingnr;
	private int waardering;
	private int stiptheid;
	private int rijstijl;
	private int gezelligheid;
	private int betrouwbaarheid;
	private String commentaar;
	private int lidnr;
	private int aankoopnr;
	private Timestamp datum;
	private String korteDatum;
	private String korteTijd;
	

	public Beoordeling() {
	}

	public int getBeoordelingnr() {
		return beoordelingnr;
	}

	public void setBeoordelingnr(int beoordelingnr) {
		this.beoordelingnr = beoordelingnr;
	}

	public int getWaardering() {
		return waardering;
	}

	public void setWaardering(int waardering) {
		this.waardering = waardering;
	}

	public int getStiptheid() {
		return stiptheid;
	}

	public void setStiptheid(int stiptheid) {
		this.stiptheid = stiptheid;
	}

	public int getRijstijl() {
		return rijstijl;
	}

	public void setRijstijl(int rijstijl) {
		this.rijstijl = rijstijl;
	}

	public int getGezelligheid() {
		return gezelligheid;
	}

	public void setGezelligheid(int gezelligheid) {
		this.gezelligheid = gezelligheid;
	}

	public int getBetrouwbaarheid() {
		return betrouwbaarheid;
	}

	public void setBetrouwbaarheid(int betrouwbaarheid) {
		this.betrouwbaarheid = betrouwbaarheid;
	}

	public String getCommentaar() {
		return commentaar;
	}

	public void setCommentaar(String commentaar) {
		this.commentaar = commentaar;
	}

	public int getLidnr() {
		return lidnr;
	}

	public void setLidnr(int lidnr) {
		this.lidnr = lidnr;
	}

	public int getAankoopnr() {
		return aankoopnr;
	}

	public void setAankoopnr(int aankoopnr) {
		this.aankoopnr = aankoopnr;
	}

	public Timestamp getDatum() {
		return datum;
	}

	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}

	public String getKorteDatum() {
		return korteDatum;
	}

	public void setKorteDatum(String korteDatum) {
		this.korteDatum = korteDatum;
	}

	public String getKorteTijd() {
		return korteTijd;
	}

	public void setKorteTijd(String korteTijd) {
		this.korteTijd = korteTijd;
	}
	
}
