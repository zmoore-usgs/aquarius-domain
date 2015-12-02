package gov.usgs.cida.aquarius;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class JodaDateConverter {
	private static final Logger log = LoggerFactory.getLogger(JodaDateConverter.class);

	public static String printDate(DateTime value) {
		String result = null;
		if (null != value) {
			result = value.toString(ISODateTimeFormat.dateTime());
		}
		return result;
	}

	public static DateTime parseDate(String value) {
		DateTime result = null;
		if (null != value) {
                    String[] splitValue = value.split("T");
                    //Check if hours equals 24. Replace 24 with 00 and increment day.
                    if(splitValue[1].substring(0, 2).equals("24")){
                        String changedTime = splitValue[0] + "T00" + splitValue[1].substring(2);
                        result = ISODateTimeFormat.dateTime().parseDateTime(changedTime);
                        result = result.plusDays(1);
                    }
                    else {
                        result = ISODateTimeFormat.dateTime().parseDateTime(value);
                    }
		}
		return result;
	}
}
