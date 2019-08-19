package com.stackroute.Muzix.controller;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.dto.TrackDto;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
import com.stackroute.Muzix.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller class that handles requests and sends a response
@RestController
@RequestMapping("api/v1")
//Swagger2 annotation for documentation
@Api(tags = {"Track Controller"})
public class TrackController {

    private TrackService trackService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    //Autowired to inject the trackService dependency
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //swagger2 annotation for documentation
    @ApiOperation(value = "Insert a track", response = ResponseEntity.class)
    //mapping to post request to /track
    @PostMapping("track")
    //handler to save track
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        logger.info("save track handler: "+track.toString());
        trackService.saveTrack(track);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get a list of all available tracks", response = ResponseEntity.class)
    //mapping to get request to /track
    @GetMapping("track")
    //handler to get all track
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get the track requested by id", response = ResponseEntity.class)
    //mapping to get request to /track/id
    @GetMapping("track/{id}")
    //handler to get a track by id
    public ResponseEntity<?> getTrack(@PathVariable String id) {
        logger.info("get track by id handler: "+id);
        try {
            return new ResponseEntity<>(trackService.getTrackById(Integer.parseInt(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Update a track", response = ResponseEntity.class)
    //mapping to put request to /track
    @PutMapping("track")
    //handler to update a track
    public ResponseEntity<?> updateTrack(@RequestBody TrackDto track) throws TrackNotFoundException {
        logger.info("update track handler: "+track.toString());
        trackService.updateTrack(track);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete the track whose id id given", response = ResponseEntity.class)
    //mapping to delete request to /track/id
    @DeleteMapping("track/{id}")
    //handler to delete a track
    public ResponseEntity<?> deleteTrack(@PathVariable String id) throws TrackNotFoundException {
        logger.info("delete track handler: "+id);
        trackService.deleteTrack(Integer.parseInt(id));
        return new ResponseEntity<>("track deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "Search all tracks by name", response = ResponseEntity.class)
    //mapping to get request to /track/search/name
    @GetMapping("track/search/{name}")
    //handler to search for a track
    public ResponseEntity<?> searchTrack(@PathVariable String name) {
        logger.info("search track handler: "+name);
        return new ResponseEntity<>(trackService.getTrackByNameOrComments(name), HttpStatus.OK);
    }
}
