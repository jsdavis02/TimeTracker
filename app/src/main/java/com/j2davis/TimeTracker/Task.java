package com.j2davis.TimeTracker;

import java.util.ArrayList;

public class Task {


    private int id = -1;
    private String name = "task";
    private ArrayList<TaskTrackEvent> trackEvents = new ArrayList<>();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.trackEvents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TaskTrackEvent> addTrackEvent(TaskTrackEvent tskTrackEvent) {
        this.trackEvents.add(tskTrackEvent);
        return this.trackEvents;
    }

    public ArrayList<TaskTrackEvent> getTrackEvents() {
        return trackEvents;
    }

    public void setTrackEvents(ArrayList<TaskTrackEvent> trackEvents) {
        this.trackEvents = trackEvents;
    }




}
