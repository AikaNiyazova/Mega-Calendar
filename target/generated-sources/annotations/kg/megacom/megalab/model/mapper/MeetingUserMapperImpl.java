package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.entity.MeetingUser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-26T22:44:38+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class MeetingUserMapperImpl implements MeetingUserMapper {

    @Override
    public MeetingUserDto toDto(MeetingUser entity) {
        if ( entity == null ) {
            return null;
        }

        MeetingUserDto meetingUserDto = new MeetingUserDto();

        return meetingUserDto;
    }

    @Override
    public MeetingUser toEntity(MeetingUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        MeetingUser meetingUser = new MeetingUser();

        return meetingUser;
    }

    @Override
    public List<MeetingUserDto> toDtoList(List<MeetingUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MeetingUserDto> list = new ArrayList<MeetingUserDto>( entityList.size() );
        for ( MeetingUser meetingUser : entityList ) {
            list.add( toDto( meetingUser ) );
        }

        return list;
    }

    @Override
    public List<MeetingUser> toEntityList(List<MeetingUserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MeetingUser> list = new ArrayList<MeetingUser>( dtoList.size() );
        for ( MeetingUserDto meetingUserDto : dtoList ) {
            list.add( toEntity( meetingUserDto ) );
        }

        return list;
    }
}
