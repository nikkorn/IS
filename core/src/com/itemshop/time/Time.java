package com.itemshop.time;

/**
 * The game time.
 */
public class Time {

	/** The hour. */
	private int hour = 9;

	/** The minute. */
	private int minute = 30;
	
	/** The day. */
	private int day = 1;
	
	/** The season. */
	private Season season = Season.SUMMER;

	/** The maximum minute value. */
	private final int maximumMinute = 59;

	/** The maximum hour value. */
	private final int maximumHour = 23;
	
	/** The maximum day value. */
	private final int maximumDay = 30;
	
	/**
	 * Get the season.
	 * @returns The season.
	 */
	public Season getSeason() {
		return season;
	}
	
	/**
	 * Get the day.
	 * @returns The day.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Get the hour.
	 * @returns The hour.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Get the minute.
	 * @returns The minute.
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Get the time value formatted for display.
	 * 
	 * @returns The formatted time value.
	 */
	public String getFormattedTime() {
		return String.format("%02d:%02d", hour, minute);
	}

	/**
	 * Tick for every in-game minute.
	 */
	public void tick() {
		minute++;

		if (minute > maximumMinute) {
			hour++;
			minute = 0;

			if (hour > maximumHour) {
				day++;
				hour = 0;
				
				if (day > maximumDay) {
					day = 1;
					
					moveToNextSeason();
				}
			}
		}
	}

	/**
	 * Move to the next season.
	 */
	private void moveToNextSeason() {
		switch(season) {
			case WINTER:
				season = Season.SPRING;
				break;
			case SPRING:
				season = Season.SUMMER;
				break;
			case SUMMER:
				season = Season.FALL;
				break;
			case FALL:
				season = Season.WINTER;
				break;
		}
	}
}
