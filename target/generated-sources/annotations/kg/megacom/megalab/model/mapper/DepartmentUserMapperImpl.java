package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.dto.DepartmentUserDto;
import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Department;
import kg.megacom.megalab.model.entity.DepartmentUser;
import kg.megacom.megalab.model.entity.Organization;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-06T11:54:55+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class DepartmentUserMapperImpl implements DepartmentUserMapper {

    @Override
    public DepartmentUserDto toDto(DepartmentUser entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentUserDto.DepartmentUserDtoBuilder departmentUserDto = DepartmentUserDto.builder();

        departmentUserDto.id( entity.getId() );
        departmentUserDto.department( departmentToDepartmentDto( entity.getDepartment() ) );
        departmentUserDto.user( userToUserDto( entity.getUser() ) );

        return departmentUserDto.build();
    }

    @Override
    public DepartmentUser toEntity(DepartmentUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        DepartmentUser.DepartmentUserBuilder departmentUser = DepartmentUser.builder();

        departmentUser.id( dto.getId() );
        departmentUser.department( departmentDtoToDepartment( dto.getDepartment() ) );
        departmentUser.user( userDtoToUser( dto.getUser() ) );

        return departmentUser.build();
    }

    @Override
    public List<DepartmentUserDto> toDtoList(List<DepartmentUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DepartmentUserDto> list = new ArrayList<DepartmentUserDto>( entityList.size() );
        for ( DepartmentUser departmentUser : entityList ) {
            list.add( toDto( departmentUser ) );
        }

        return list;
    }

    @Override
    public List<DepartmentUser> toEntityList(List<DepartmentUserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DepartmentUser> list = new ArrayList<DepartmentUser>( dtoList.size() );
        for ( DepartmentUserDto departmentUserDto : dtoList ) {
            list.add( toEntity( departmentUserDto ) );
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
}
