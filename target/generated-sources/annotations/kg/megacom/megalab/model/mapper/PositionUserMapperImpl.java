package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.dto.PositionDto;
import kg.megacom.megalab.model.dto.PositionUserDto;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Department;
import kg.megacom.megalab.model.entity.Organization;
import kg.megacom.megalab.model.entity.Position;
import kg.megacom.megalab.model.entity.PositionUser;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-02T19:29:44+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class PositionUserMapperImpl implements PositionUserMapper {

    @Override
    public PositionUserDto toDto(PositionUser entity) {
        if ( entity == null ) {
            return null;
        }

        PositionUserDto.PositionUserDtoBuilder positionUserDto = PositionUserDto.builder();

        positionUserDto.id( entity.getId() );
        positionUserDto.position( positionToPositionDto( entity.getPosition() ) );
        positionUserDto.user( userToUserDto( entity.getUser() ) );

        return positionUserDto.build();
    }

    @Override
    public PositionUser toEntity(PositionUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        PositionUser.PositionUserBuilder positionUser = PositionUser.builder();

        positionUser.id( dto.getId() );
        positionUser.position( positionDtoToPosition( dto.getPosition() ) );
        positionUser.user( userDtoToUser( dto.getUser() ) );

        return positionUser.build();
    }

    @Override
    public List<PositionUserDto> toDtoList(List<PositionUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PositionUserDto> list = new ArrayList<PositionUserDto>( entityList.size() );
        for ( PositionUser positionUser : entityList ) {
            list.add( toDto( positionUser ) );
        }

        return list;
    }

    @Override
    public List<PositionUser> toEntityList(List<PositionUserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PositionUser> list = new ArrayList<PositionUser>( dtoList.size() );
        for ( PositionUserDto positionUserDto : dtoList ) {
            list.add( toEntity( positionUserDto ) );
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

    protected DepartmentDto departmentToDepartmentDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto.DepartmentDtoBuilder departmentDto = DepartmentDto.builder();

        departmentDto.id( department.getId() );
        departmentDto.organization( organizationToOrganizationDto( department.getOrganization() ) );
        departmentDto.departmentName( department.getDepartmentName() );
        departmentDto.head( userToUserDto( department.getHead() ) );
        departmentDto.isDeleted( department.getIsDeleted() );

        return departmentDto.build();
    }

    protected PositionDto positionToPositionDto(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionDto.PositionDtoBuilder positionDto = PositionDto.builder();

        positionDto.id( position.getId() );
        positionDto.department( departmentToDepartmentDto( position.getDepartment() ) );
        positionDto.positionName( position.getPositionName() );
        positionDto.isDeleted( position.getIsDeleted() );

        return positionDto.build();
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

    protected Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        department.id( departmentDto.getId() );
        department.organization( organizationDtoToOrganization( departmentDto.getOrganization() ) );
        department.departmentName( departmentDto.getDepartmentName() );
        department.head( userDtoToUser( departmentDto.getHead() ) );
        department.isDeleted( departmentDto.getIsDeleted() );

        return department.build();
    }

    protected Position positionDtoToPosition(PositionDto positionDto) {
        if ( positionDto == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.id( positionDto.getId() );
        position.department( departmentDtoToDepartment( positionDto.getDepartment() ) );
        position.positionName( positionDto.getPositionName() );
        position.isDeleted( positionDto.getIsDeleted() );

        return position.build();
    }
}
