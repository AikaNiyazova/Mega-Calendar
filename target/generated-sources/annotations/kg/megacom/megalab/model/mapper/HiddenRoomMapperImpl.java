package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.HiddenRoom;
import kg.megacom.megalab.model.entity.Room;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-25T17:20:58+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class HiddenRoomMapperImpl implements HiddenRoomMapper {

    @Override
    public HiddenRoomDto toDto(HiddenRoom entity) {
        if ( entity == null ) {
            return null;
        }

        HiddenRoomDto.HiddenRoomDtoBuilder hiddenRoomDto = HiddenRoomDto.builder();

        hiddenRoomDto.id( entity.getId() );
        hiddenRoomDto.room( roomToRoomDto( entity.getRoom() ) );
        hiddenRoomDto.hidingStartDate( entity.getHidingStartDate() );
        hiddenRoomDto.hidingEndDate( entity.getHidingEndDate() );
        hiddenRoomDto.reasonForHiding( entity.getReasonForHiding() );

        return hiddenRoomDto.build();
    }

    @Override
    public HiddenRoom toEntity(HiddenRoomDto dto) {
        if ( dto == null ) {
            return null;
        }

        HiddenRoom.HiddenRoomBuilder hiddenRoom = HiddenRoom.builder();

        hiddenRoom.id( dto.getId() );
        hiddenRoom.room( roomDtoToRoom( dto.getRoom() ) );
        hiddenRoom.hidingStartDate( dto.getHidingStartDate() );
        hiddenRoom.hidingEndDate( dto.getHidingEndDate() );
        hiddenRoom.reasonForHiding( dto.getReasonForHiding() );

        return hiddenRoom.build();
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

    protected RoomDto roomToRoomDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto.RoomDtoBuilder roomDto = RoomDto.builder();

        roomDto.id( room.getId() );
        roomDto.roomName( room.getRoomName() );
        roomDto.roomCapacity( room.getRoomCapacity() );
        roomDto.location( room.getLocation() );
        roomDto.isDashboardAvailable( room.getIsDashboardAvailable() );
        roomDto.isProjectorAvailable( room.getIsProjectorAvailable() );
        roomDto.isAcAvailable( room.getIsAcAvailable() );
        roomDto.isDeleted( room.getIsDeleted() );

        return roomDto.build();
    }

    protected Room roomDtoToRoom(RoomDto roomDto) {
        if ( roomDto == null ) {
            return null;
        }

        Room.RoomBuilder room = Room.builder();

        room.id( roomDto.getId() );
        room.roomName( roomDto.getRoomName() );
        room.roomCapacity( roomDto.getRoomCapacity() );
        room.location( roomDto.getLocation() );
        room.isDashboardAvailable( roomDto.getIsDashboardAvailable() );
        room.isProjectorAvailable( roomDto.getIsProjectorAvailable() );
        room.isAcAvailable( roomDto.getIsAcAvailable() );
        room.isDeleted( roomDto.getIsDeleted() );

        return room.build();
    }
}
