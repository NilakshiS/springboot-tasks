package com.stackroute.Muzix.repository;

import com.stackroute.Muzix.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository class used to perform database operations, extends JpaRepository
//JpaRepository<EntityClassName,WrapperTypeOfIdPropertyInEntityClass>
@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    //Query to search a track by its name or comments in the database
    List<Track> findByTrackCommentsContainingIgnoreCaseOrTrackNameContainingIgnoreCase(String comments,String name);

}
