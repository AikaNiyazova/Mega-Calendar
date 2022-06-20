package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto>{

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
