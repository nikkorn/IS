package com.itemshop.schedule;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Time {
    /** The ScheduledExecutorService used to update the time periodically. */
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /** The duration of one in-game minute in ms. */
    private static final long minuteDuration = 2000;

    private int hour = 9;

    private int minute = 30;

    private int maximumMinute = 59;

    private int maximumHour = 23;


    public Time() {
        this.startUpdates();
    }

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

    public String getFormattedTime() {
        return String.format("%02d:%02d", hour, minute);
    }
}
