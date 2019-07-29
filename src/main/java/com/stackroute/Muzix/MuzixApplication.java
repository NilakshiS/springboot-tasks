package com.stackroute.Muzix;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.service.TrackService;
import com.stackroute.Muzix.service.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class MuzixApplication {
    public static void main(String[] args) {
        SpringApplication.run(MuzixApplication.class, args);
    }
}
