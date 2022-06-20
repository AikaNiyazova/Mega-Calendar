package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.PositionUserDto;
import kg.megacom.megalab.model.entity.PositionUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PositionUserMapper extends BaseMapper<PositionUser, PositionUserDto> {

    PositionUserMapper INSTANCE = Mappers.getMapper(PositionUserMapper.class);

}
