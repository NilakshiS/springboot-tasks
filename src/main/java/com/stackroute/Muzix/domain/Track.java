package com.stackroute.Muzix.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity used to mark it as a database entity
@Entity
public class Track {
    //properties
    @Id     //annotated with @Id to make it primary key in database
    private int trackId;            //Track's id
    private String trackName;       //Track's Name
    private String trackComments;   //Track's Comments

    //No args constructor
    public Track() {
    }

    //all Arguments constructor
    public Track(int trackId, String trackName, String trackComments) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackComments = trackComments;
    }

    //getters and setters
    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackComments() {
        return trackComments;
    }

    public void setTrackComments(String trackComments) {
        this.trackComments = trackComments;
    }

    //to string
    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", trackComments='" + trackComments + '\'' +
                '}';
    }
}
