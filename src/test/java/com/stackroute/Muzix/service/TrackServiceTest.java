package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
import com.stackroute.Muzix.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    Track track;

    //Create a mock for TrackRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackName("Love Maze");
        track.setTrackComments("BTS");
        list = new ArrayList<>();
        list.add(track);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save(any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);
        //Assert.assertEquals(user,savedUser);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }

    @Test
    public void getAllTracks() {
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        Assert.assertEquals(list, tracklist);
    }

    @Test
    public void updateTrackTestSuccess() throws TrackNotFoundException {
        when(trackRepository.existsById(any())).thenReturn(true);
        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack = trackService.updateTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);

    }

    @Test(expected = TrackNotFoundException.class)
    public void updateTrackTestFailure() throws TrackNotFoundException {
        when(trackRepository.existsById(anyInt())).thenReturn(false);
        when(trackRepository.save(any())).thenReturn(null);
        Track savedTrack = trackService.updateTrack(track);

        //Assert.assertEquals(user,savedUser);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }

    @Test
    public void deleteTrackTestSuccess() throws TrackNotFoundException {
        when(trackRepository.existsById(anyInt())).thenReturn(true);

        trackService.deleteTrack(track.getTrackId());

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).deleteById(track.getTrackId());

    }

    @Test(expected = TrackNotFoundException.class)
    public void deleteTrackTestFailure() throws TrackNotFoundException {
        when(trackRepository.existsById(anyInt())).thenReturn(false);
        trackService.deleteTrack(track.getTrackId());
    }

    @Test
    public void getTrackByIdTestSuccess() throws TrackNotFoundException {
        when(trackRepository.existsById(anyInt())).thenReturn(true);
        when(trackRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(track));

        Track fetchedTrack = trackService.getTrackById(track.getTrackId());
        Assert.assertEquals(track,fetchedTrack);
        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).findById(track.getTrackId());

    }

    @Test(expected = TrackNotFoundException.class)
    public void getTrackByIdTestFailure() throws TrackNotFoundException {
        when(trackRepository.existsById((anyInt()))).thenReturn(false);
        trackService.getTrackById(track.getTrackId());
    }

    @Test
    public void searchTrackTest() {
        when(trackRepository.findByTrackCommentsContainingIgnoreCaseOrTrackNameContainingIgnoreCase(anyString(),anyString())).thenReturn(list);
        List<Track> searchResultsTrack = trackService.getTrackByNameOrComments("track");
        Assert.assertEquals(searchResultsTrack,list);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).findByTrackCommentsContainingIgnoreCaseOrTrackNameContainingIgnoreCase("track","track");

    }

}