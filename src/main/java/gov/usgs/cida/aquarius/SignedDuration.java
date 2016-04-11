package gov.usgs.cida.aquarius;

import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.ReadableDuration;

public class SignedDuration implements ReadableDuration {
	private String sign;
	private Duration duration;
	
	public SignedDuration(String sign, Duration duration) {
		super();
		this.sign = sign;
		this.duration = duration;
	}
	
	public String getSign() {
		return sign;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public String toString() {
		return (sign != null ? sign : "") + duration.toString();
	}

	@Override
	public int compareTo(ReadableDuration o) {
		return duration.compareTo(o);
	}

	@Override
	public long getMillis() {
		return duration.getMillis();
	}

	@Override
	public Duration toDuration() {
		return duration.toDuration();
	}

	@Override
	public Period toPeriod() {
		return duration.toPeriod();
	}

	@Override
	public boolean isEqual(ReadableDuration inDuration) {
		return duration.isEqual(inDuration);
	}

	@Override
	public boolean isLongerThan(ReadableDuration inDuration) {
		return duration.isLongerThan(inDuration);
	}

	@Override
	public boolean isShorterThan(ReadableDuration inDuration) {
		return duration.isShorterThan(inDuration);
	}
	
	public static SignedDuration parse(String value) {
		SignedDuration duration;
		if(value.startsWith("-")) {
			duration = new SignedDuration("-", Duration.parse(value.substring(1)));
		} else {
			duration = new SignedDuration(null, Duration.parse(value.substring(1)));
		}
		return duration;
	}
}
