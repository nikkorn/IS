package com.itemshop.schedule;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * The game time.
 */
public class Time {
    /** The ScheduledExecutorService used to update the time periodically. */
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /** The duration of one in-game minute in ms. */
    private static final long minuteDuration = 2000;

    /** The hour. */
    private int hour = 9;

    /** The minute. */
    private int minute = 30;

    /** The maximum minute value. */
    private final int maximumMinute = 59;

    /** The maximum hour value. */
    private final int maximumHour = 23;

    /**
     * Creates and starts updating the time instance.
     */
    public Time() {
        this.startUpdates();
    }

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
     * Starts updates to the time instance.
     */
    private void startUpdates() {
        Runnable updateTime = () -> {
            minute++;

            if (minute > maximumMinute) {
               hour++;
               minute = 0;

                if (hour > maximumHour) {
                    hour = 0;
                }
            }
        };

        // Begin updating the time.
        executorService.scheduleAtFixedRate(updateTime, minuteDuration, minuteDuration, TimeUnit.MILLISECONDS);
    }
}
