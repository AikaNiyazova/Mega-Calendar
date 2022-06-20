package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper extends BaseMapper<Role, RoleDto> {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

}
