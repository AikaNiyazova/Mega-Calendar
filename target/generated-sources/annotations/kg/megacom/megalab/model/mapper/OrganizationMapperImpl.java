package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.entity.Organization;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T15:01:09+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public OrganizationDto toDto(Organization entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationDto organizationDto = new OrganizationDto();

        return organizationDto;
    }

    @Override
    public Organization toEntity(OrganizationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Organization organization = new Organization();

        return organization;
    }

    @Override
    public List<OrganizationDto> toDtoList(List<Organization> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OrganizationDto> list = new ArrayList<OrganizationDto>( entityList.size() );
        for ( Organization organization : entityList ) {
            list.add( toDto( organization ) );
        }

        return list;
    }

    @Override
    public List<Organization> toEntityList(List<OrganizationDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Organization> list = new ArrayList<Organization>( dtoList.size() );
        for ( OrganizationDto organizationDto : dtoList ) {
            list.add( toEntity( organizationDto ) );
        }

        return list;
    }
}
