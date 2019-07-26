package com.stackroute.Muzix;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
//set the property source so that compiler knows where to fetch values from
@PropertySource("classpath:application.properties")
public class MuzixApplication extends SpringBootServletInitializer implements CommandLineRunner {

	//TrackService dependency to save tracks in database
	private TrackService trackService;

	//autowire the setter to inject trackService dependency
	@Autowired
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	//setting values using @Value
    @Value("${id1}")
    private int id1;

    @Value("${name1}")
    private String name1;

    @Value("${comment1}")
    private String comment1;

    //Environment can be used to get values from .properties file
	private Environment environment;

	//autowire the setter to inject environment dependency
    @Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
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
			//insert one track using @value
			trackService.saveTrack(new Track(id1, name1, comment1));
			//insert the other track using environment.getProperty()
			trackService.saveTrack(new Track(Integer.parseInt(environment.getProperty("id2")), environment.getProperty("name2"), environment.getProperty("comment2")));
		} catch (TrackAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
}
