package com.stackroute.Muzix.controller;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Controller class that handles requests and sends a response
@RestController
@RequestMapping("api/v1")
public class TrackController {

    private TrackService trackService;
    private ResponseEntity responseEntity;


    //Autowired to inject the trackService dependency
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    //handler to save track
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        try {
                //check if track already exists
                if (trackService.getTrackById(track.getTrackId()) != null){
                    //if it does then update the track
                    trackService.saveTrack(track);
                    responseEntity = new ResponseEntity<>("Successfully updated", HttpStatus.OK);
                }
                else {
                    //otherwise create new track
                    trackService.saveTrack(track);
                    responseEntity = new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
                }

        }catch (Exception e){
            responseEntity = new ResponseEntity<>("caught:"+e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    //handler to get all tracks
    public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    //handler to get a track by its id
    public ResponseEntity<?> getTrack(@PathVariable String id){
        try {
            return new ResponseEntity<>(trackService.getTrackById(Integer.parseInt(id)), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("track/{id}")
    //handler to delete a track by its id
    public ResponseEntity<?> deleteTrack(@PathVariable String id){
        try {
            trackService.deleteTrack(Integer.parseInt(id));
            responseEntity = new ResponseEntity<>("track deleted", HttpStatus.OK);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>("caught:"+e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track/search/{name}")
    //handler to search for a track by name or comments
    public ResponseEntity<?> searchTrack(@PathVariable String name){
        return new ResponseEntity<>(trackService.getTrackByNameOrComments(name), HttpStatus.OK);

    }
}
