package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.Room;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T15:01:09+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDto toDto(Room entity) {
        if ( entity == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        return roomDto;
    }

    @Override
    public Room toEntity(RoomDto dto) {
        if ( dto == null ) {
            return null;
        }

        Room room = new Room();

        return room;
    }

    @Override
    public List<RoomDto> toDtoList(List<Room> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoomDto> list = new ArrayList<RoomDto>( entityList.size() );
        for ( Room room : entityList ) {
            list.add( toDto( room ) );
        }

        return list;
    }

    @Override
    public List<Room> toEntityList(List<RoomDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Room> list = new ArrayList<Room>( dtoList.size() );
        for ( RoomDto roomDto : dtoList ) {
            list.add( toEntity( roomDto ) );
        }

        return list;
    }
}
