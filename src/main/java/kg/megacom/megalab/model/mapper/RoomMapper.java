package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper extends BaseMapper<Room, RoomDto> {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

}
