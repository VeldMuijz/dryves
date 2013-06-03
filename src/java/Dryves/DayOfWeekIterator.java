/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.util.Iterator;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;

/**
 * Deze klasse wordt gebruikt om door een week heen te itereren. Hierbij is het
 * mogelijk om verschillende dagen te selecteren uit weken tusssen een
 * begindatum en een einddatum.
 *
 * @author jeroen
 */
public class DayOfWeekIterator implements Iterator<DateTime> {

	private final DateTime end;
	private DateTime nextDate;

	/**
	 * Constructor voor de DayOfWeekIterator klasse
	 *
	 * @param start timestamp met de startdatum
	 * @param end timestamp met de einddatum
	 * @param dayOfWeekToIterate integer met een dag van de week
	 */
	public DayOfWeekIterator(DateTime start, DateTime end, int dayOfWeekToIterate) {
		this.end = end;
		nextDate = start.withDayOfWeek(dayOfWeekToIterate);
		if (start.getDayOfWeek() > dayOfWeekToIterate) {
			nextDate = nextDate.plusWeeks(1);
		}
	}

	/**
	 * Deze methode geeft aan of er nog een volgende datum beschikbaar is binnen
	 * de range begindatum <> einddatum
	 *
	 * @return true als er nog een datum beschikbaar is tussen de begin- en
	 * einddatum, false wanneer de volgende datum buiten de range valt
	 */
	public boolean hasNext() {
		return !nextDate.isAfter(end);
	}

	/**
	 * Deze methode verwisseld de huidige datum voor de eerstvolgende datum
	 *
	 * @return een DateTime met de eerstvolgende datum
	 */
	public DateTime next() {
		DateTime result = nextDate;
		nextDate = nextDate.plusWeeks(1);
		return result;
	}

	/**
	 * Deze methode is ongebruikt gebleven dus leeg.
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
