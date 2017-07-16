package com.itemshop.schedule;

/**
 * The game clock.
 */
public class Clock {
    /** The games clock. */
    private static Clock clock;

    /** The clocks time. */
    private Time time;

    /**
     * Creates the clock.
     */
    private Clock() {
        this.time = new Time();
    }

    /**
     * Gets the game clock.
     * @returns The clock.
     */
    public static Clock getClock() {
        if (clock != null) {
            return clock;
        } else {
            clock = new Clock();

            return clock;
        }
    }

    /**
     * Get the clock value formatted for display.
     * @returns The formatted clock value.
     */
    public String getFormattedClock() {
        return time.getFormattedTime();
    }
}
