package gov.usgs.cida.aquarius;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Partial;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author dmsibley
 * @author thongsav
 */
public class JodaDateConverter {
	private static final Logger log = LoggerFactory.getLogger(JodaDateConverter.class);
	private static DateTimeFormatter timeFormat = ISODateTimeFormat.dateTime().withOffsetParsed();
	private static DateTimeFormatter dateFormat = ISODateTimeFormat.date();
	
	private final static String AGGREGATED_TIME_MARKER = "T24:00";

	public static String printDate(Partial value) {
		String result = null;
		
		if (null != value) {
			int hourFieldIndex = -1;
			try {
				hourFieldIndex = value.get(DateTimeFieldType.hourOfDay());
			} catch(IllegalArgumentException e) {}
			
			if(hourFieldIndex < 0) {
				result = value.toString(dateFormat);
			} else {
				result = value.toString(timeFormat);
			}
		}
		
		return result;
	}

	public static Partial parseDate(String value) {
		Partial result = null;
		
		if (null != value) {
			try {
				if(value.contains(AGGREGATED_TIME_MARKER)) { //This represents a daily value, or other non-instant time
					String newValue = value.substring(0, value.indexOf(AGGREGATED_TIME_MARKER));
					DateTime parsedDateTime = dateFormat.parseDateTime(newValue);
					result = new Partial(
							new DateTimeFieldType[] {
									DateTimeFieldType.year(),
									DateTimeFieldType.monthOfYear(),
									DateTimeFieldType.dayOfMonth()
							}, 
							new int[] {
									parsedDateTime.getYear(),
									parsedDateTime.getMonthOfYear(),
									parsedDateTime.getDayOfMonth()
							});
				} else { //This should represent a DateTime as closely as possible
					DateTime parsedDateTime = timeFormat.parseDateTime(value);
					result = new Partial(
							new DateTimeFieldType[] {
									DateTimeFieldType.era(),
									DateTimeFieldType.centuryOfEra(),
									DateTimeFieldType.year(),
									DateTimeFieldType.monthOfYear(),
									DateTimeFieldType.dayOfMonth(),
									DateTimeFieldType.hourOfDay(),
									DateTimeFieldType.minuteOfHour(),
									DateTimeFieldType.secondOfMinute(),
									DateTimeFieldType.millisOfSecond()
							}, 
							new int[] {
									parsedDateTime.getEra(),
									parsedDateTime.getYearOfCentury(),
									parsedDateTime.getYear(),
									parsedDateTime.getMonthOfYear(),
									parsedDateTime.getDayOfMonth(),
									parsedDateTime.getHourOfDay(),
									parsedDateTime.getMinuteOfHour(),
									parsedDateTime.getSecondOfMinute(),
									parsedDateTime.getMillisOfSecond()
							});
				}
			} catch (Exception e) {
				log.warn("Problem parsing date string " + value + ", null is being returned.", e);
			}
		}
		return result;
	}
}
