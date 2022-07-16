package kg.megacom.megalab.model.mapper;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.MeetingFrequencyDto;
import kg.megacom.megalab.model.entity.MeetingFrequency;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-08T18:17:06+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class MeetingFrequencyMapperImpl implements MeetingFrequencyMapper {

    @Override
    public MeetingFrequencyDto toDto(MeetingFrequency entity) {
        if ( entity == null ) {
            return null;
        }

        MeetingFrequencyDto.MeetingFrequencyDtoBuilder meetingFrequencyDto = MeetingFrequencyDto.builder();

        meetingFrequencyDto.id( entity.getId() );
        List<DayOfWeek> list = entity.getDaysOfWeek();
        if ( list != null ) {
            meetingFrequencyDto.daysOfWeek( new ArrayList<DayOfWeek>( list ) );
        }
        meetingFrequencyDto.numberOfWeeks( entity.getNumberOfWeeks() );

        return meetingFrequencyDto.build();
    }

    @Override
    public MeetingFrequency toEntity(MeetingFrequencyDto dto) {
        if ( dto == null ) {
            return null;
        }

        MeetingFrequency.MeetingFrequencyBuilder meetingFrequency = MeetingFrequency.builder();

        meetingFrequency.id( dto.getId() );
        List<DayOfWeek> list = dto.getDaysOfWeek();
        if ( list != null ) {
            meetingFrequency.daysOfWeek( new ArrayList<DayOfWeek>( list ) );
        }
        meetingFrequency.numberOfWeeks( dto.getNumberOfWeeks() );

        return meetingFrequency.build();
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
