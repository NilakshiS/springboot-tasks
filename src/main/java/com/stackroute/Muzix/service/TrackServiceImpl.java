package com.stackroute.Muzix.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
import com.stackroute.Muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    //method to save track
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        //check if track already exists
        if (trackRepository.existsById(track.getTrackId())) {
            //throw custom exception
            throw new TrackAlreadyExistsException("Track Already Exists!");
        }
        //otherwise try to save track
        Track savedTrack = trackRepository.save(track);
        //if new track was not created throw custom exception
        //return the track that was inserted
        try {
            return savedTrack;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //method to update an existing track
    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException {
        //check if track does not exist
        if (!trackRepository.existsById(track.getTrackId())) {
            //throw custom exception
            throw new TrackNotFoundException("Track Not Found!");
        }
        //otherwise update the track
        return trackRepository.save(track);
    }

    //method to delete a track
    @Override
    public Track deleteTrack(int id) throws TrackNotFoundException {
        //check if track exists
        if (!trackRepository.existsById(id)) {
            //throw custom exception
            throw new TrackNotFoundException("Track Not Found!");
        }
        //otherwise delete the track
        Track track = trackRepository.findById(id).orElse(null);
        trackRepository.deleteById(id);
        return track;
    }

    //method to get all tracks in database
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    //method to get a single track by id
    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        //check if track exists
        if (!trackRepository.existsById(id)) {
            //throw custom exception
            throw new TrackNotFoundException("Track Not Found!");
        }
        //otherwise get the track
        return trackRepository.findById(id).orElse(null);
    }

    //method to search for track by name or comments
    @Override
    public List<Track> getTrackByNameOrComments(String name) {
        return trackRepository.findByTrackCommentsContainingIgnoreCaseOrTrackNameContainingIgnoreCase(name, name);
    }

    //method to get tracks from api and save to database
    @Override
    public void saveTracksFromApi() {
        //RestTemplate gets response from an api
        RestTemplate restTemplate = new RestTemplate();
        //url of Last.fm api
        String fooResourceUrl = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=081010bfa07bd3c5b56ee809b476993a&format=json";
        //store response in a ResponseEntity
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

        //Object Mapper to access the JSON from the response entity
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            //read the response body to get JSON object
            root = mapper.readTree(response.getBody());
            //Store the JSON array in the object to a variable
            ArrayNode arrayNode = (ArrayNode) root.path("tracks").path("track");

            //iterate the JSON array
            for (int i = 0; i < arrayNode.size(); i++) {
                //get a new Track object and fill it with data using setters
                Track track = new Track();
                track.setTrackId(i + 4);//added 4 because 3 tracks are already added at startup
                track.setTrackName(arrayNode.get(i).path("name").asText());
                track.setTrackComments(arrayNode.get(i).path("artist").path("name").asText());
                //save the track to database
                trackRepository.save(track);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
