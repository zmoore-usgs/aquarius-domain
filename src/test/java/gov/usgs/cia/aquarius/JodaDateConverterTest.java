package gov.usgs.cia.aquarius;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import gov.usgs.cida.aquarius.JodaDateConverter;

public class JodaDateConverterTest {
	
	@Test
	public void testConvert() {
		DateTime test1 = JodaDateConverter.parseDate("2014-12-05T00:00:00.0000000-06:00");
		DateTime test2 = JodaDateConverter.parseDate("2015-11-10T02:59:22.9912972Z");
		
		assertEquals(test1.getYear(), 2014);
		assertEquals(test2.getYear(), 2015);
	}

}
