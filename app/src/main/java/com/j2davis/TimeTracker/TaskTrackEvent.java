package com.j2davis.TimeTracker;

import java.time.LocalDateTime;

public class TaskTrackEvent {


    private LocalDateTime start = null;
    private LocalDateTime end = null;
    private long totalTime = 0;
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}
