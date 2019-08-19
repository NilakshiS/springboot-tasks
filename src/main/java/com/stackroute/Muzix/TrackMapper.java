package com.stackroute.Muzix;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.dto.TrackDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackMapper {

        TrackMapper INSTANCE = Mappers.getMapper(TrackMapper.class);

        @Mapping(source = "trackId", target = "trackId")
        @Mapping(source = "trackName", target = "trackName")
        @Mapping(source = "trackComments", target = "trackComments")
        TrackDto trackToTrackDTO(Track entity);
        Track trackDTOToTrack(TrackDto dto);

}
