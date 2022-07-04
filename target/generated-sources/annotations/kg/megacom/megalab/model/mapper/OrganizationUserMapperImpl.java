package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.OrganizationUserDto;
import kg.megacom.megalab.model.entity.OrganizationUser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T15:01:09+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class OrganizationUserMapperImpl implements OrganizationUserMapper {

    @Override
    public OrganizationUserDto toDto(OrganizationUser entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationUserDto organizationUserDto = new OrganizationUserDto();

        return organizationUserDto;
    }

    @Override
    public OrganizationUser toEntity(OrganizationUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrganizationUser organizationUser = new OrganizationUser();

        return organizationUser;
    }

    @Override
    public List<OrganizationUserDto> toDtoList(List<OrganizationUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OrganizationUserDto> list = new ArrayList<OrganizationUserDto>( entityList.size() );
        for ( OrganizationUser organizationUser : entityList ) {
            list.add( toDto( organizationUser ) );
        }

        return list;
    }

    @Override
    public List<OrganizationUser> toEntityList(List<OrganizationUserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<OrganizationUser> list = new ArrayList<OrganizationUser>( dtoList.size() );
        for ( OrganizationUserDto organizationUserDto : dtoList ) {
            list.add( toEntity( organizationUserDto ) );
        }

        return list;
    }
}
