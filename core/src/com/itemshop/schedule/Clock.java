package com.itemshop.schedule;

/**
 * The game clock.
 */
public class Clock {

    private static Clock clock;

    private Time time;

    private Clock() {
        this.time = new Time();
    }

    /** Gets the Clock instance. */
    public static Clock getClock() {
        if (clock != null) {
            return clock;
        } else {
            clock = new Clock();

            return clock;
        }
    }

    public String getFormattedClock() {
        return time.getFormattedTime();
    }
}
