package dev.oscarreyes.javajson.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Fixture {

	/**
	 * Format of timestamps
	 */
	public static SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS");

	/**
	 * ID
	 */
	public String ID;

	/**
	 * Start time in UTC
	 */
	public String StartDateUtc;

	/**
	 * End time in UTC
	 */
	public String EndDateUtc;

	/**
	 * Venue name
	 */
	public String VenueName;

	/**
	 * Home team
	 */
	public String HomeTeamName;

	/**
	 * Guest team
	 */
	public String GuestTeamName;

	/**
	 * Home team sets
	 */
	public Integer HomeTeamSets;

	/**
	 * Guest team sets
	 */
	public Integer GuestTeamSets;

	/**
	 * Home team set score
	 */
	public Integer HomeTeamSetScore;

	/**
	 * Guest team set score
	 */
	public Integer GuestTeamSetScore;

	/**
	 * RTMP endpoint
	 */
	public String RtmpUrl;

	/**
	 * Streaming bitrate
	 */
	public String Bitrate;

	/**
	 * Streaming resolution
	 */
	public String Resolution;

	/**
	 * Competition code
	 */
	public String Contest;

	/**
	 * Start date
	 */
	private Date start;

	/**
	 * End date
	 */
	private Date end;

	/**
	 * Constructs a default fixture
	 */
	public Fixture() {
		TimeZone zone = TimeZone.getTimeZone("UTC");
//        if (TimeZone.getDefault().useDaylightTime()) {
//            zone.setRawOffset(-TimeZone.getDefault().getDSTSavings());
//        }
		format.setTimeZone(zone);
	}

	/**
	 * Update fixture
	 *
	 * @param fixture Fixture update
	 */
	public synchronized void update(Fixture fixture) throws ParseException {
		Date tempStart = format.parse(fixture.StartDateUtc);
		Date tempEnd = format.parse(fixture.EndDateUtc);

		ID = fixture.ID;
		Contest = fixture.Contest;
		VenueName = fixture.VenueName;

		RtmpUrl = fixture.RtmpUrl;

		StartDateUtc = fixture.StartDateUtc;
		start = tempStart;
		EndDateUtc = fixture.EndDateUtc;
		end = tempEnd;

		HomeTeamName = fixture.HomeTeamName;
		HomeTeamSets = fixture.HomeTeamSets;
		HomeTeamSetScore = fixture.HomeTeamSetScore;

		GuestTeamName = fixture.GuestTeamName;
		GuestTeamSets = fixture.GuestTeamSets;
		GuestTeamSetScore = fixture.GuestTeamSetScore;

		Bitrate = fixture.Bitrate;
		Resolution = fixture.Resolution;
	}

	/**
	 * Get start time
	 */
	public synchronized Date getStart() {
		return start;
	}

	/**
	 * Set start time
	 *
	 * @param time Start time
	 */
	public synchronized void setStart(Date time) {
		start = time;
		StartDateUtc = format.format(time);
	}

	/**
	 * Get end time
	 */
	public synchronized Date getEnd() {
		return end;
	}

	/**
	 * Set end time
	 *
	 * @param time End time
	 */
	public synchronized void setEnd(Date time) {
		end = time;
		EndDateUtc = format.format(time);
	}

	/**
	 * @return Match description
	 */
	public String getMatch() {
		return String.format("%s vs %s", HomeTeamName, GuestTeamName);
	}

	/**
	 * @return Match time description
	 */
	public String getMatchTime() {
		if (start == null || end == null) return "not scheduled yet";
		return String.format(Locale.ENGLISH, "%1$td %1$tb %1$tH:%1$tM-%2$tH:%2$tM", start, end);
	}

	/**
	 * Check if fixture is playing
	 *
	 * @return Fixture is playing
	 */
	public synchronized boolean isPlaying() {
		if (start == null || end == null) return false;
		long startTime = start.getTime();
		long endTime = end.getTime();
		long now = System.currentTimeMillis();
		return (now >= startTime && now <= endTime);
	}

	@Override
	public String toString() {
		return getMatchTime() + ": " + getMatch();
	}
}