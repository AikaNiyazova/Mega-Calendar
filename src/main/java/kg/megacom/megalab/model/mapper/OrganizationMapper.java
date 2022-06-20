package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper extends BaseMapper<Organization, OrganizationDto> {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

}
