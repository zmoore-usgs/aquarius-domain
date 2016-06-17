package gov.usgs.cida.aquarius;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author dmsibley
 * @author thongsav
 */
public class DateConverter {
	private static final Logger log = LoggerFactory.getLogger(DateConverter.class);
	private static DateTimeFormatter timeFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
	
	private final static String AGGREGATED_TIME_MARKER = "T24:00";
	private final static String AGGREGATED_TIME_REPLACEMENT = "T00:00";

	public static String printDate(Temporal value) {
		String result = null;
		
		if (null != value) {
			if(value instanceof LocalDate) {
				result = dateFormat.format(value);
			} else {
				result = timeFormat.format(value);
			}
		}
		
		return result;
	}

	public static Temporal parseDate(String value) {
		Temporal result = null;
		
		if (null != value) {
			try {
				if(value.contains(AGGREGATED_TIME_MARKER)) { //This represents a daily value, or other non-instant time
					TemporalAccessor parsedDate = timeFormat.parse(value.replace(AGGREGATED_TIME_MARKER, AGGREGATED_TIME_REPLACEMENT));
					result = LocalDate.from(parsedDate);
				} else { //This should represent a DateTime as closely as possible
					TemporalAccessor parsedDateTime = timeFormat.parse(value);
					result = OffsetDateTime.from(parsedDateTime);
				}
			} catch (Exception e) {
				log.warn("Problem parsing date string " + value + ", null is being returned.", e);
			}
		}
		return result;
	}
}
