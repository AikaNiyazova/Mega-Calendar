package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.MeetingFrequencyDto;
import kg.megacom.megalab.model.entity.MeetingFrequency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingFrequencyMapper extends BaseMapper<MeetingFrequency, MeetingFrequencyDto> {

    MeetingFrequencyMapper INSTANCE = Mappers.getMapper(MeetingFrequencyMapper.class);

}
