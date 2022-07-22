package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.MeetingFrequencyDto;
import kg.megacom.megalab.model.entity.MeetingFrequency;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-08T23:26:44+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class MeetingFrequencyMapperImpl implements MeetingFrequencyMapper {

    @Override
    public MeetingFrequencyDto toDto(MeetingFrequency entity) {
        if ( entity == null ) {
            return null;
        }

        MeetingFrequencyDto meetingFrequencyDto = new MeetingFrequencyDto();

        return meetingFrequencyDto;
    }

    @Override
    public MeetingFrequency toEntity(MeetingFrequencyDto dto) {
        if ( dto == null ) {
            return null;
        }

        MeetingFrequency meetingFrequency = new MeetingFrequency();

        return meetingFrequency;
    }

    @Override
    public List<MeetingFrequencyDto> toDtoList(List<MeetingFrequency> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MeetingFrequencyDto> list = new ArrayList<MeetingFrequencyDto>( entityList.size() );
        for ( MeetingFrequency meetingFrequency : entityList ) {
            list.add( toDto( meetingFrequency ) );
        }

        return list;
    }

    @Override
    public List<MeetingFrequency> toEntityList(List<MeetingFrequencyDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MeetingFrequency> list = new ArrayList<MeetingFrequency>( dtoList.size() );
        for ( MeetingFrequencyDto meetingFrequencyDto : dtoList ) {
            list.add( toEntity( meetingFrequencyDto ) );
        }

        return list;
    }
}
