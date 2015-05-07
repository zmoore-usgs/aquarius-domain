package gov.usgs.cida.aquarius;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class BigDecimalConverter {
	private static final Logger log = LoggerFactory.getLogger(BigDecimalConverter.class);
	
	public static String printBigDecimal(BigDecimal value) {
		String result = null;
		if (null != value) {
			result = value.toPlainString();
		}
		return result;
	}

	public static BigDecimal parseBigDecimal(String value) {
		BigDecimal result = null;
		if (null != value) {
			result = new BigDecimal(value);
		}
		return result;
	}
}
