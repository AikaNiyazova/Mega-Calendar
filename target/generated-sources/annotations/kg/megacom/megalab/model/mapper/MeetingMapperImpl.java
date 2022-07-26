package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.entity.Meeting;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-26T22:44:38+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class MeetingMapperImpl implements MeetingMapper {

    @Override
    public MeetingDto toDto(Meeting entity) {
        if ( entity == null ) {
            return null;
        }

        MeetingDto meetingDto = new MeetingDto();

        return meetingDto;
    }

    @Override
    public Meeting toEntity(MeetingDto dto) {
        if ( dto == null ) {
            return null;
        }

        Meeting meeting = new Meeting();

        return meeting;
    }

    @Override
    public List<MeetingDto> toDtoList(List<Meeting> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MeetingDto> list = new ArrayList<MeetingDto>( entityList.size() );
        for ( Meeting meeting : entityList ) {
            list.add( toDto( meeting ) );
        }

        return list;
    }

    @Override
    public List<Meeting> toEntityList(List<MeetingDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Meeting> list = new ArrayList<Meeting>( dtoList.size() );
        for ( MeetingDto meetingDto : dtoList ) {
            list.add( toEntity( meetingDto ) );
        }

        return list;
    }
}
