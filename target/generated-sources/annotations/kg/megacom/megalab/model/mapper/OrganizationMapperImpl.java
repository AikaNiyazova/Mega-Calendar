package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Organization;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-02T19:29:44+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public OrganizationDto toDto(Organization entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationDto.OrganizationDtoBuilder organizationDto = OrganizationDto.builder();

        organizationDto.id( entity.getId() );
        organizationDto.organizationName( entity.getOrganizationName() );
        organizationDto.admin( userToUserDto( entity.getAdmin() ) );
        organizationDto.isDeleted( entity.getIsDeleted() );

        return organizationDto.build();
    }

    @Override
    public Organization toEntity(OrganizationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Organization.OrganizationBuilder organization = Organization.builder();

        organization.id( dto.getId() );
        organization.organizationName( dto.getOrganizationName() );
        organization.admin( userDtoToUser( dto.getAdmin() ) );
        organization.isDeleted( dto.getIsDeleted() );

        return organization.build();
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

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto.RoleDtoBuilder roleDto = RoleDto.builder();

        roleDto.id( role.getId() );
        roleDto.roleName( role.getRoleName() );
        List<Authority> list = role.getAuthorities();
        if ( list != null ) {
            roleDto.authorities( new ArrayList<Authority>( list ) );
        }
        roleDto.isDeleted( role.getIsDeleted() );

        return roleDto.build();
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.photoPath( user.getPhotoPath() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.patronymic( user.getPatronymic() );
        userDto.msisdn( user.getMsisdn() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.role( roleToRoleDto( user.getRole() ) );
        userDto.status( user.getStatus() );
        userDto.isDeleted( user.getIsDeleted() );

        return userDto.build();
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.id( roleDto.getId() );
        role.roleName( roleDto.getRoleName() );
        List<Authority> list = roleDto.getAuthorities();
        if ( list != null ) {
            role.authorities( new ArrayList<Authority>( list ) );
        }
        role.isDeleted( roleDto.getIsDeleted() );

        return role.build();
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.photoPath( userDto.getPhotoPath() );
        user.firstName( userDto.getFirstName() );
        user.lastName( userDto.getLastName() );
        user.patronymic( userDto.getPatronymic() );
        user.msisdn( userDto.getMsisdn() );
        user.email( userDto.getEmail() );
        user.password( userDto.getPassword() );
        user.role( roleDtoToRole( userDto.getRole() ) );
        user.status( userDto.getStatus() );
        user.isDeleted( userDto.getIsDeleted() );

        return user.build();
    }
}
