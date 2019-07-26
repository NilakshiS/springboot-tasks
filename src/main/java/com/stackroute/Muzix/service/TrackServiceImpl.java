package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//service class contains the business logic
@Service
public class TrackServiceImpl implements TrackService {

    //TrackRepository object to perform database
    private TrackRepository trackRepository;

    //Autowired constructor to inject dependency
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    //method to save track
    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

    @Override
    //method to delete track
    public void deleteTrack(int id) {
        trackRepository.deleteById(id);
    }

    @Override
    //method to get all tracks
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    //method to get a track by id
    public Track getTrackById(int id) {
        return trackRepository.findById(id).orElse(null);
    }

    @Override
    //method to search a track by its name or comments
    public List<Track> getTrackByNameOrComments(String name) {
        return trackRepository.findTrackByNameOrComments(name);
    }
}
