package gov.usgs.cia.aquarius;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Partial;
import org.junit.Test;

import gov.usgs.cida.aquarius.JodaDateConverter;

public class JodaDateConverterTest {
	
	@Test
	public void testConvert() {
		Partial test1 = JodaDateConverter.parseDate("2014-12-05T00:00:00.0000000-06:00");
		Partial test2 = JodaDateConverter.parseDate("2015-11-10T02:59:22.9912972Z");
		
		DateTime time1 = test1.toDateTime(new DateTime());
		DateTime time2 = test2.toDateTime(new DateTime());
		
		assertEquals(time1.getYear(), 2014);
		assertEquals(time2.getYear(), 2015);
	}
	
	
	@Test
	public void testConvert_DailyValues() {
		Partial rawPartial = JodaDateConverter.parseDate("2014-12-05T24:00:00.0000000-06:00"); //example of a DV timestamp in Aquarius
		
		DateTime test1 = rawPartial.toDateTime(new DateTime());
		
		assertEquals(test1.getYear(), 2014);
		assertEquals(test1.getMonthOfYear(), 12);
		assertEquals(test1.getDayOfMonth(), 5);
		
		assertEquals("Daily values do not include a time", JodaDateConverter.printDate(rawPartial), "2014-12-05");
	}

}
