package com.stackroute.Muzix.repository;

import com.stackroute.Muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(4);
        track.setTrackName("Love Maze");
        track.setTrackComments("BTS");

    }

    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }


    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(track.getTrackId(),fetchUser.getTrackId());
        trackRepository.delete(track);
    }

    @Test
    public void testSaveTrackFailure(){
        Track testUser = new Track();
        testUser.setTrackName("Fake Love");
        testUser.setTrackComments("BTS");
        trackRepository.save(testUser);
        Track fetchUser = trackRepository.findById(testUser.getTrackId()).get();
        Assert.assertNotSame(testUser, track);
        trackRepository.delete(testUser);
    }

    @Test
    public void testDeleteTrack(){
        trackRepository.save(track);
        Assert.assertNotNull(trackRepository.findById(track.getTrackId()).orElse(null));
        trackRepository.delete(track);
        Track fetchUser = trackRepository.findById(track.getTrackId()).orElse(null);
        Assert.assertNull(fetchUser);
    }

    @Test
    public void testFindTrackById(){
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getTrackId()).orElse(null);
        Assert.assertNotNull(fetchUser);
    }

    @Test
    public void testFindTrackByNameOrComments(){
        List<Track> fetchUser = trackRepository.findByTrackCommentsContainingIgnoreCaseOrTrackNameContainingIgnoreCase("Love Maze","");
        Assert.assertNotNull(fetchUser);

    }

    @Test
    public void testGetAllUser(){
        trackRepository.deleteAll();
        Track track = new Track();
        track.setTrackId(5);
        track.setTrackName("Fake Love");
        track.setTrackComments("BTS");
        Track track1 = new Track();
        track1.setTrackId(6);
        track1.setTrackName("DNA");
        track1.setTrackComments("BTS");
        trackRepository.save(track);
        trackRepository.save(track1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Fake Love",list.get(0).getTrackName());
        trackRepository.deleteAll();
    }
}
