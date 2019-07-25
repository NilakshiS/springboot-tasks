package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;

import java.util.List;

//interface for the service class
public interface TrackService {

    //method to save a track in database
    Track saveTrack(Track track) throws TrackAlreadyExistsException;

    //method to update a track already saved in database
    Track updateTrack(Track track) throws TrackNotFoundException;

    //method to delete a track
    void deleteTrack(int id) throws TrackNotFoundException;

    //method to get tracks from last.fm api and save in database
    void saveTracksFromApi();

    //method to get all tracks saved in database
    List<Track> getAllTracks();

    //method to get a track by id
    Track getTrackById(int id) throws TrackNotFoundException;

    //method to get a track by name or comments
    List<Track> getTrackByNameOrComments(String name);
}

