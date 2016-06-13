package gov.usgs.cida.aquarius;

import org.joda.time.DateTime;
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
	
	private final static String AGGREGATED_TIME_MARKER = "T24:00";
	private final static String AGGREGATED_TIME_INTERNAL_REPRESENTATION = "T00:00";

	public static String printDate(AggregatedDateTime value) {
		String result = null;
		if (null != value) {
			result = value.toString(timeFormat);
		}
		
		if(value.isAggregated()) { //assumes 24:00 was used to set aggregated and converted to 00:00, undoing.
			result = result.replace(AGGREGATED_TIME_INTERNAL_REPRESENTATION, AGGREGATED_TIME_MARKER);
		}
		return result;
	}

	public static AggregatedDateTime parseDate(String value) {
		DateTime result = null;
		boolean isAggregated = false;
		
		if (null != value) {
			try {
				String newValue = value; 
				if(value.contains(AGGREGATED_TIME_MARKER)) {
					newValue = value.replaceFirst(AGGREGATED_TIME_MARKER, AGGREGATED_TIME_INTERNAL_REPRESENTATION);
					isAggregated = true;
				}
				result = timeFormat.parseDateTime(newValue);
			} catch (Exception e) {
				log.warn("Problem parsing date string " + value + ", null is being returned.", e);
			}
		}
		return new AggregatedDateTime(result, isAggregated);
	}
}
