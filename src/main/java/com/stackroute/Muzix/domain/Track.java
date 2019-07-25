package com.stackroute.Muzix.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity used to mark it as a database entity
@Entity
//Lombok annotations
//Lombok plugin automatically generates getters, setters and constructors for any class marked as @Data
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    //properties
    @Id     //annotated with @Id to make it primary key in database
    @GeneratedValue(strategy = GenerationType.AUTO)     //To make it an auto-incremented id
    @ApiModelProperty(notes = "The Track id")           //Swagger2's annotation to describe properties
                                                        // in the documentation
    private int trackId;                                //Track's id

    @ApiModelProperty(notes = "The name of the Track")
    private String trackName;                           //Name of the track

    @ApiModelProperty(notes = "The comments about the Track")
    private String trackComments;                       //comments about the track, used to store artist name
}
