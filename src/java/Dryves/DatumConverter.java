/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import Dryves.Controller.RitPlannen;
import Dryves.Model.Rit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author jeroen
 */
public class DatumConverter {

	private Date datum = null;
	private Date einddatum = null;
	private String korteTijd;
	private String korteDatum;
	private DateTime timestamp;

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

	public DateTime convertTimestamp(String stringdatum, String tijd) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");
		SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		try {
			datum = dateFormat.parse(stringdatum + 'T' + tijd);
			timestamp = DateTime.parse(timestampFormat.format(datum));
			System.out.println("++++++++++++++DatumConverter+++++++++++++++++\n"
					+ " Dit is timestamp na conversie: " + timestamp);

		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("************ Programma snapt Timestamp niet!");
		}
		return timestamp;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getEinddatum() {
		return einddatum;
	}

	public void setEinddatum(Date einddatum) {
		this.einddatum = einddatum;
	}

	public String getStringDatum() {
		return korteDatum;
	}

	public void setStringDatum(String korteDatum) {
		this.korteDatum = korteDatum;
	}

	public String getKorteTijd() {
		return korteTijd;
	}

	public void setKorteTijd(String korteTijd) {
		this.korteTijd = korteTijd;
	}

	public String getKorteDatum() {
		return korteDatum;
	}

	public void setKorteDatum(String korteDatum) {
		this.korteDatum = korteDatum;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}
}
