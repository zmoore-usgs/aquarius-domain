package gov.usgs.cia.aquarius;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import gov.usgs.cida.aquarius.AggregatedDateTime;
import gov.usgs.cida.aquarius.JodaDateConverter;

public class JodaDateConverterTest {
	
	@Test
	public void testConvert() {
		AggregatedDateTime test1 = JodaDateConverter.parseDate("2014-12-05T00:00:00.0000000-06:00");
		AggregatedDateTime test2 = JodaDateConverter.parseDate("2015-11-10T02:59:22.9912972Z");
		
		assertEquals(test1.getYear(), 2014);
		assertEquals(test2.getYear(), 2015);
	}
	
	
	@Test
	public void testConvert_DailyValues() {
		AggregatedDateTime test1 = JodaDateConverter.parseDate("2014-12-05T24:00:00.0000000-06:00");
		
		assertEquals(test1.getYear(), 2014);
		assertEquals(test1.getMonthOfYear(), 12);
		assertEquals(test1.getDayOfMonth(), 5);
		assertEquals(test1.getHourOfDay(), 0);
		assertEquals(test1.getMinuteOfHour(), 0);
		assertEquals(test1.getSecondOfMinute(), 0);
		
		assertEquals(JodaDateConverter.printDate(test1), "2014-12-05T24:00:00.000-06:00");
	}

}
