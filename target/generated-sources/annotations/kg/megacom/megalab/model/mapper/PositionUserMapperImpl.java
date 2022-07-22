package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.PositionUserDto;
import kg.megacom.megalab.model.entity.PositionUser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-08T23:26:44+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class PositionUserMapperImpl implements PositionUserMapper {

    @Override
    public PositionUserDto toDto(PositionUser entity) {
        if ( entity == null ) {
            return null;
        }

        PositionUserDto positionUserDto = new PositionUserDto();

        return positionUserDto;
    }

    @Override
    public PositionUser toEntity(PositionUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        PositionUser positionUser = new PositionUser();

        return positionUser;
    }

    @Override
    public List<PositionUserDto> toDtoList(List<PositionUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PositionUserDto> list = new ArrayList<PositionUserDto>( entityList.size() );
        for ( PositionUser positionUser : entityList ) {
            list.add( toDto( positionUser ) );
        }

        return list;
    }

    @Override
    public List<PositionUser> toEntityList(List<PositionUserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PositionUser> list = new ArrayList<PositionUser>( dtoList.size() );
        for ( PositionUserDto positionUserDto : dtoList ) {
            list.add( toEntity( positionUserDto ) );
        }

        return list;
    }
}
