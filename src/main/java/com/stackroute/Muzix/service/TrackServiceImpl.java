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
        try{
            return trackRepository.save(track);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    //method to delete track
    public Track deleteTrack(int id) {
        Track track = trackRepository.findById(id).orElse(null);
        trackRepository.deleteById(id);
        return track;
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
}
