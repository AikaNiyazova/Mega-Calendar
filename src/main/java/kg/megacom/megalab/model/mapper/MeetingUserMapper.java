package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.entity.MeetingUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingUserMapper extends BaseMapper<MeetingUser, MeetingUserDto> {

    MeetingUserMapper INSTANCE = Mappers.getMapper(MeetingUserMapper.class);

}
