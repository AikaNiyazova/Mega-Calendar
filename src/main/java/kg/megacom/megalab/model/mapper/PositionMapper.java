package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.PositionDto;
import kg.megacom.megalab.model.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PositionMapper extends BaseMapper<Position, PositionDto> {

    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

}
