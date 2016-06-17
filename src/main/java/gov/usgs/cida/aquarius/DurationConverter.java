package gov.usgs.cida.aquarius;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author thongsav
 */
public class DurationConverter {
	private static final Logger log = LoggerFactory.getLogger(DurationConverter.class);

	public static String printDuration(Duration value) {
		return value.toString();
	}

	public static Duration parseDuration(String value) {
		return Duration.parse(value);
	}
}
