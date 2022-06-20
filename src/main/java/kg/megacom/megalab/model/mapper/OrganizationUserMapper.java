package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.OrganizationUserDto;
import kg.megacom.megalab.model.entity.OrganizationUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationUserMapper extends BaseMapper<OrganizationUser, OrganizationUserDto> {

    OrganizationUserMapper INSTANCE = Mappers.getMapper(OrganizationUserMapper.class);

}
