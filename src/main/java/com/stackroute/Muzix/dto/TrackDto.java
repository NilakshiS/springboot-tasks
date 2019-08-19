package com.stackroute.Muzix.dto;

public class TrackDto {
    private int trackId;
    private String trackName;
    private String trackComments;

    @Override
    public String toString() {
        return "TrackDto{" +
                "trackId=" + trackId +
                ", trackComments=" + trackComments +
                '}';
    }

    public TrackDto() {
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackComments() {
        return trackComments;
    }

    public void setTrackComments(String trackComments) {
        this.trackComments = trackComments;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
}
