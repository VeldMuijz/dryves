/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.util.Iterator;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;

/**
 *
 * @author jeroen
 */
public class DayOfWeekIterator implements Iterator<DateTime>{
    private final DateTime end;
    private DateTime nextDate;

    public DayOfWeekIterator(DateTime start, DateTime end, int dayOfWeekToIterate){
        this.end = end;
        nextDate = start.withDayOfWeek(dayOfWeekToIterate);
        if (start.getDayOfWeek() > dayOfWeekToIterate) {
            nextDate = nextDate.plusWeeks(1);
        }
    }

    public boolean hasNext() {
        return !nextDate.isAfter(end);
    }

    public DateTime next() {
        DateTime result = nextDate;
        nextDate = nextDate.plusWeeks(1);
        return result;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
 }

