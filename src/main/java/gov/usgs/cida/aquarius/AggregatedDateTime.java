package gov.usgs.cida.aquarius;

import java.io.Serializable;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.base.BaseDateTime;

/**
 * Data from Aquarius sometimes comes with a 24:00 time to represent a non-instant Date (Daily aggregate) with no time.
 * 
 * Joda DateTime does not support 2400 as a time. This class wraps 
 */
public class AggregatedDateTime
	extends BaseDateTime
	implements ReadableDateTime, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public AggregatedDateTime() {
		dateTime = new DateTime();
	}
	
	/**
	 * converts 2400 to 0000 and marks aggregated DateTime
	 */
	public AggregatedDateTime(DateTime inDateTime, boolean isAggregated) {
		dateTime = inDateTime;
		aggregated = isAggregated;
	}
	
	private DateTime dateTime;
	private boolean aggregated;
	
	public boolean isAggregated() {
		return aggregated;
	}

	public void setAggregated(boolean aggregated) {
		this.aggregated = aggregated;
	}

	@Override
	public long getMillis() {
		return dateTime.getMillis();
	}
	
	@Override
	public Chronology getChronology() {
		return dateTime.getChronology();
	}
	@Override
	public DateTimeZone getZone() {
		return dateTime.getZone();
	}
	@Override
	public int get(DateTimeFieldType type) {
		return dateTime.get(type);
	}
	
	@Override
	public boolean isSupported(DateTimeFieldType field) {
		return dateTime.isSupported(field);
	}
	@Override
	public Instant toInstant() {
		return dateTime.toInstant();
	}
	@Override
	public boolean isEqual(ReadableInstant instant) {
		//if we have an aggregated DateTime, this cannot be equal to an instant.
		if(aggregated) {
			return false;
		} else {
			return dateTime.isEqual(instant);
		}
	}
	@Override
	public boolean isAfter(ReadableInstant instant) {
		return dateTime.isAfter(instant);
	}
	@Override
	public boolean isBefore(ReadableInstant instant) {
		//TODO this is a short cut and currently says "is before start of time aggregated datetime", is not techincally after (eg: for a daily).
		return !dateTime.isAfter(instant);
	}
	@Override
	public int compareTo(ReadableInstant instant) {
		if(aggregated) {
			if(isAfter(instant)) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return dateTime.compareTo(instant);
		}
	}
	
	@Override
	public int getDayOfWeek() {
		return dateTime.getDayOfWeek();
	}
	@Override
	public int getDayOfMonth() {
		return dateTime.getDayOfMonth();
	}
	@Override
	public int getDayOfYear() {
		return dateTime.getDayOfYear();
	}
	@Override
	public int getWeekOfWeekyear() {
		return dateTime.getWeekOfWeekyear();
	}
	@Override
	public int getWeekyear() {
		return dateTime.getWeekyear();
	}
	@Override
	public int getMonthOfYear() {
		return dateTime.getMonthOfYear();
	}
	@Override
	public int getYear() {
		return dateTime.getYear();
	}
	@Override
	public int getYearOfEra() {
		return dateTime.getYearOfEra();
	}
	@Override
	public int getYearOfCentury() {
		return dateTime.getYearOfCentury();
	}
	@Override
	public int getCenturyOfEra() {
		return dateTime.getCenturyOfEra();
	}
	@Override
	public int getEra() {
		return dateTime.getCenturyOfEra();
	}
	@Override
	public int getMillisOfSecond() {
		//TODO decide if we should throw an exception since time might not be valid
		return dateTime.getMillisOfSecond();
	}
	@Override
	public int getMillisOfDay() {
		//TODO decide if we should throw an exception since time might not be valid
		return dateTime.getMillisOfDay();
	}
	@Override
	public int getSecondOfMinute() {
		//TODO decide if we should throw an exception since time might not be valid
		return dateTime.getSecondOfMinute();
	}
	@Override
	public int getSecondOfDay() {
		//TODO decide if we should throw an exception since time might not be valid
		return dateTime.getSecondOfDay();
	}
	@Override
	public int getMinuteOfHour() {
		//TODO decide if we should throw an exception since time might not be valid
		return dateTime.getMinuteOfHour();
	}
	@Override
	public int getMinuteOfDay() {
		//TODO decide if we should throw an exception since time might not be valid
		return dateTime.getMinuteOfDay();
	}
	@Override
	public int getHourOfDay() {
		//TODO decide if we should throw an exception since time might not be valid
		return dateTime.getHourOfDay();
	}
	@Override
	public DateTime toDateTime() {
		return dateTime.toDateTime();
	}
	@Override
	public MutableDateTime toMutableDateTime() {
		return dateTime.toMutableDateTime();
	}
	@Override
	public String toString(String pattern) throws IllegalArgumentException {
		return dateTime.toString(pattern);
	}
	@Override
	public String toString(String pattern, Locale locale) throws IllegalArgumentException {
		return dateTime.toString(pattern, locale);
	}
	
	
}
