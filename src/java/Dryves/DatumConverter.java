/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import Dryves.Controller.RitPlannen;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 * Deze klasse is een klasse die gebruikt word om timestamps te maken en te
 * bewerken.
 *
 * @author jeroen
 */
public class DatumConverter {

	private Date datum = null;
	private Date einddatum = null;
	private String korteTijd;
	private String korteDatum;
	private DateTime timestamp;

	/**
	 * Deze methode maakt van een timestamp een kortere datum.
	 *
	 * @param datum bevat een datum van het object Date
	 * @return een string met een korte datum dd/mm/yyyy
	 */
	public String korteDatum(Date datum) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat datumFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			System.out.println(datum);
			datum = dateFormat.parse(datum.toString());
			korteDatum = datumFormat.format(datum);

			System.out.println("++++++++++++++DatumConverter+++++++++++++++++\n"
					+ " Dit is datum na conversie: " + korteDatum);

		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("************ Programma snapt Timestamp niet!");
		}
		System.out.println("kortedatum in dc: " + korteDatum);
		return korteDatum;
	}

	/**
	 * Deze methode maakt van een timestamp een kortere tijd.
	 *
	 * @param datum bevat een datum van het object Date
	 * @return een string met een korte tijd weergave HH:mm
	 */
	public String korteTijd(Date datum) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat tijdFormat = new SimpleDateFormat("HH:mm");

		try {
			System.out.println(datum);
			datum = dateFormat.parse(datum.toString());
			korteTijd = tijdFormat.format(datum);

			System.out.println("++++++++++++++DatumConverter+++++++++++++++++\n"
					+ " Dit is tijd na conversie: " + korteTijd);

		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("************ Programma snapt Timestamp niet!");
		}
		return korteTijd;
	}

	/**
	 * Maak een timestamp uit twee strings, een datum en een tijd
	 *
	 * @param stringdatum een string wat de datum bevat
	 * @param stringtijd een string dat de tijd bevat
	 * @return een timestamp in het formaat yyyy-MM-ddTHH:mm
	 */
	public DateTime convertTimestamp(String stringdatum, String stringtijd) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");
		SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		try {
			datum = dateFormat.parse(stringdatum + 'T' + stringtijd);
			timestamp = DateTime.parse(timestampFormat.format(datum));
			System.out.println("++++++++++++++DatumConverter+++++++++++++++++\n"
					+ " Dit is timestamp na conversie: " + timestamp);

		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("************ Programma snapt Timestamp niet!");
		}
		return timestamp;
	}

	/**
	 * Maak een timestamp aan van een Amerikaanse timestamp
	 *
	 * @param stringdatum een string die een datum bevat in Amerikaans format
	 * @param stringtijd een string die een tijd bevat in HH:mm format
	 * @return een timestamp in yyyy-MM-ddTHH:mm
	 */
	public DateTime convertUSTimestamp(String stringdatum, String stringtijd) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy'T'HH:mm");
		SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		try {
			datum = dateFormat.parse(stringdatum + 'T' + stringtijd);
			timestamp = DateTime.parse(timestampFormat.format(datum));
			System.out.println("++++++++++++++DatumConverter+++++++++++++++++\n"
					+ " Dit is timestamp na conversie: " + timestamp);

		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("************ Programma snapt Timestamp niet!");
		}
		return timestamp;
	}

	/**
	  Deze methode geeft een datum terug die in deze klasse is geset
	 *
	 * @return datum van het datatype Date
	 */
	public Date getDatum() {
		return datum;
	}
	
	/**
	 * Deze methode set attribuut datum met de parameter datum
	 * @param datum is een datum van het datatype Date
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/**
	 * Deze methode geeft een einddatum terug die in deze klasse is geset
	 *
	 * @return einddatum van het datatype Date
	 */
	public Date getEinddatum() {
		return einddatum;
	}
	
	/**
	 * Deze methode set attribuut einddatum met de parameter einddatum
	 * @param einddatum einddatum van het object type Date
	 */
	public void setEinddatum(Date einddatum) {
		this.einddatum = einddatum;
	}

	/**
	 * Deze methode geeft een datum terug die in deze klasse is geset
	 *
	 * @return
	 */
	public String getStringDatum() {
		return korteDatum;
	}
	
	/**
	 * Deze methode set attribute Stringdatum met de parameter korteDatum
	 * @param korteDatum is een string wat een datum bevat
	 */
	public void setStringDatum(String korteDatum) {
		this.korteDatum = korteDatum;
	}

	/**
	 * Deze methode geeft een tijd terug die in deze klasse is geset
	 *
	 * @return string met een tijd HH:mm
	 */
	public String getKorteTijd() {
		return korteTijd;
	}

	/**
	 * Deze methode set attribute korteTijd met de parameter korteTijd
	 * @param korteTijd is een string die een korteTijd bevat in formaat HH:mm
	 */
	public void setKorteTijd(String korteTijd) {
		this.korteTijd = korteTijd;
	}

	/**
	 * Deze methode geeft een datum terug die in deze klasse is geset
	 *
	 * @return string met een datum dd/mm/yyyy
	 */
	public String getKorteDatum() {
		return korteDatum;
	}
	
	/**
	 * Deze methode set attribute korteDatum met parameter korteDatum
	 * @param korteDatum is een string die een korteDatum bevat dd/mm/yyyy
	 */
	public void setKorteDatum(String korteDatum) {
		this.korteDatum = korteDatum;
	}

	/**
	 * Deze methode geeft een datum (timestamp) terug die in deze klasse is
	 * geset
	 *
	 * @return string met daar in een timestamp
	 */
	public DateTime getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Deze methode set attribute Timestamp met parameter Timestamp
	 * @param timestamp is een DateTime met een timestamp erin
	 */
	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}
}
