package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.MeetingDateTimeDto;
import kg.megacom.megalab.model.entity.MeetingDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingDateTimeMapper extends BaseMapper<MeetingDateTime, MeetingDateTimeDto> {

    MeetingDateTimeMapper INSTANCE = Mappers.getMapper(MeetingDateTimeMapper.class);

}
