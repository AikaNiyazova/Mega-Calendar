package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.dto.OrganizationUserDto;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Organization;
import kg.megacom.megalab.model.entity.OrganizationUser;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-23T17:49:19+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class OrganizationUserMapperImpl implements OrganizationUserMapper {

    @Override
    public OrganizationUserDto toDto(OrganizationUser entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationUserDto.OrganizationUserDtoBuilder organizationUserDto = OrganizationUserDto.builder();

        organizationUserDto.id( entity.getId() );
        organizationUserDto.organization( organizationToOrganizationDto( entity.getOrganization() ) );
        organizationUserDto.user( userToUserDto( entity.getUser() ) );

        return organizationUserDto.build();
    }

    @Override
    public OrganizationUser toEntity(OrganizationUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrganizationUser.OrganizationUserBuilder organizationUser = OrganizationUser.builder();

        organizationUser.id( dto.getId() );
        organizationUser.organization( organizationDtoToOrganization( dto.getOrganization() ) );
        organizationUser.user( userDtoToUser( dto.getUser() ) );

        return organizationUser.build();
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

    protected OrganizationDto organizationToOrganizationDto(Organization organization) {
        if ( organization == null ) {
            return null;
        }

        OrganizationDto.OrganizationDtoBuilder organizationDto = OrganizationDto.builder();

        organizationDto.id( organization.getId() );
        organizationDto.organizationName( organization.getOrganizationName() );
        organizationDto.admin( userToUserDto( organization.getAdmin() ) );
        organizationDto.isDeleted( organization.getIsDeleted() );

        return organizationDto.build();
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

    protected Organization organizationDtoToOrganization(OrganizationDto organizationDto) {
        if ( organizationDto == null ) {
            return null;
        }

        Organization.OrganizationBuilder organization = Organization.builder();

        organization.id( organizationDto.getId() );
        organization.organizationName( organizationDto.getOrganizationName() );
        organization.admin( userDtoToUser( organizationDto.getAdmin() ) );
        organization.isDeleted( organizationDto.getIsDeleted() );

        return organization.build();
    }
}
