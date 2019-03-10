package com.arm;

class Ohlc {
    private final int timeIntervalSec;

    private int startTime = -1;
    private int open = -1;
    private int high = -1;
    private int low = -1;
    private int close = -1;

    /**
     * Constructs an Ohcl object.
     *
     * @param timeIntervalSec the duration of an interval (seconds)
     */
    Ohlc(int timeIntervalSec) {
        this.timeIntervalSec = timeIntervalSec;
    }

    /**
     * Updates the OHCL.
     * <p>
     * If the previous time interval has ended and it is valid, then the OHCL output record is printed.
     *
     * @param time  the Unix time (seconds) at which the update occurred
     * @param value the updated value (-1 if not defined)
     */
    void update(int time, int value) {
        if (time - startTime > timeIntervalSec) {
            if (isIntervalValid()) {
                printRecord();
            }
            startTime = time - time % timeIntervalSec;
            open = close;
            high = close;
            low = close;
        }

        if (value >= 0) {
            if (open < 0) {
                open = value;
            }
            if (high < value) {
                high = value;
            }
            if (low > value || low < 0) {
                low = value;
            }
            close = value;
        }
    }

    private void printRecord() {
        System.out.format("%d\t%d\t%d\t%d\t%d\n", startTime, open, high, low, close);
    }

    private boolean isIntervalValid() {
        return startTime >= 0 && open >= 0 && high >= 0 && low >= 0 && close >= 0;
    }
}
