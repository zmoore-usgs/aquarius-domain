package gov.usgs.cida.aquarius;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author thongsav
 */
public class SignedDurationConverter {
	private static final Logger log = LoggerFactory.getLogger(SignedDurationConverter.class);

	public static String printDuration(SignedDuration value) {
		return value.toString();
	}

	public static SignedDuration parseDuration(String value) {
		return SignedDuration.parse(value);
	}
}
