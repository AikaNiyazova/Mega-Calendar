package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.entity.Label;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LabelMapper extends BaseMapper<Label, LabelDto> {

    LabelMapper INSTANCE = Mappers.getMapper(LabelMapper.class);

}
