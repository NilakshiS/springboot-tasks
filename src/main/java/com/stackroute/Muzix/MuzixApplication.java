package com.stackroute.Muzix;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MuzixApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private TrackService trackService;

    @Autowired
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MuzixApplication.class);
    }

    public static void main(String[] args){
        SpringApplication.run(MuzixApplication.class, args);
    }

    @Override
    public void run(String... args){
        try {
            trackService.saveTrack(new Track(1, "Trivia:Love", "BTS - RM"));
            trackService.saveTrack(new Track(2, "Trivia:Seesaw", "BTS - Suga"));
            trackService.saveTrack(new Track(3, "Trivia:Just Dance", "BTS - J-Hope"));
            trackService.saveTracksFromApi();
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
