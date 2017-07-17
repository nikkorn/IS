package com.itemshop.schedule;

/**
 * The game time.
 */
public class Time {

    /** The hour. */
    private int hour = 9;

    /** The minute. */
    private int minute = 30;

    /** The maximum minute value. */
    private final int maximumMinute = 59;

    /** The maximum hour value. */
    private final int maximumHour = 23;

    /**
     * Get the hour.
     * @returns The hour.
     */
    public int getHour() { return hour; }

    /**
     * Get the minute.
     * @returns The minute.
     */
    public int getMinute() { return minute; }

    /**
     * Get the time value formatted for display.
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
                 hour = 0;
             }
         }
    }
}
