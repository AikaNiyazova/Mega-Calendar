package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.entity.HiddenRoom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HiddenRoomMapper extends BaseMapper<HiddenRoom, HiddenRoomDto> {

    HiddenRoomMapper INSTANCE = Mappers.getMapper(HiddenRoomMapper.class);

}
