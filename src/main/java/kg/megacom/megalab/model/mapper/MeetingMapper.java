package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.entity.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingMapper extends BaseMapper<Meeting, MeetingDto> {

    MeetingMapper INSTANCE = Mappers.getMapper(MeetingMapper.class);

}
