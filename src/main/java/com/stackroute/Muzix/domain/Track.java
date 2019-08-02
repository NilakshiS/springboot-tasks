package com.stackroute.Muzix.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document used to mark it as a database document in mongoDb
@Document
//Lombok annotations
//Lombok plugin automatically generates getters, setters and constructors for any class marked as @Data
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    //properties
    @Id     //annotated with @Id to make it primary key in database
    @ApiModelProperty(notes = "The Track id")           //Swagger2's annotation to describe properties
                                                        // in the documentation
    private int trackId;                                //Track's id

    @ApiModelProperty(notes = "The name of the Track")
    private String trackName;                           //Name of the track

    @ApiModelProperty(notes = "The comments about the Track")
    private String trackComments;                       //comments about the track, used to store artist name
}
