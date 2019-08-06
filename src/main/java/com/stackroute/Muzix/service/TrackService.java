package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;

import java.util.List;

//interface for the service class
public interface TrackService {

    //method to save a track in database
    Track saveTrack(Track track);

    //method to delete a track
    Track deleteTrack(int id);

    //method to get all tracks saved in database
    List<Track> getAllTracks();

    //method to get a track by id
    Track getTrackById(int id);

    //method to search track by name or comments
    List<Track> getTrackByNameOrComments(String name);
}
