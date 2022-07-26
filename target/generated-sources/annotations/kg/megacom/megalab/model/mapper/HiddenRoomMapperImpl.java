package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.entity.HiddenRoom;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-26T22:44:38+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class HiddenRoomMapperImpl implements HiddenRoomMapper {

    @Override
    public HiddenRoomDto toDto(HiddenRoom entity) {
        if ( entity == null ) {
            return null;
        }

        HiddenRoomDto hiddenRoomDto = new HiddenRoomDto();

        return hiddenRoomDto;
    }

    @Override
    public HiddenRoom toEntity(HiddenRoomDto dto) {
        if ( dto == null ) {
            return null;
        }

        HiddenRoom hiddenRoom = new HiddenRoom();

        return hiddenRoom;
    }

    @Override
    public List<HiddenRoomDto> toDtoList(List<HiddenRoom> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<HiddenRoomDto> list = new ArrayList<HiddenRoomDto>( entityList.size() );
        for ( HiddenRoom hiddenRoom : entityList ) {
            list.add( toDto( hiddenRoom ) );
        }

        return list;
    }

    @Override
    public List<HiddenRoom> toEntityList(List<HiddenRoomDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<HiddenRoom> list = new ArrayList<HiddenRoom>( dtoList.size() );
        for ( HiddenRoomDto hiddenRoomDto : dtoList ) {
            list.add( toEntity( hiddenRoomDto ) );
        }

        return list;
    }
}
