package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-04T14:06:06+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( entity.getId() );
        userDto.photoPath( entity.getPhotoPath() );
        userDto.firstName( entity.getFirstName() );
        userDto.lastName( entity.getLastName() );
        userDto.patronymic( entity.getPatronymic() );
        userDto.msisdn( entity.getMsisdn() );
        userDto.email( entity.getEmail() );
        userDto.password( entity.getPassword() );
        userDto.role( roleToRoleDto( entity.getRole() ) );
        userDto.status( entity.getStatus() );
        userDto.isDeleted( entity.getIsDeleted() );

        return userDto.build();
    }

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( dto.getId() );
        user.photoPath( dto.getPhotoPath() );
        user.firstName( dto.getFirstName() );
        user.lastName( dto.getLastName() );
        user.patronymic( dto.getPatronymic() );
        user.msisdn( dto.getMsisdn() );
        user.email( dto.getEmail() );
        user.password( dto.getPassword() );
        user.role( roleDtoToRole( dto.getRole() ) );
        user.status( dto.getStatus() );
        user.isDeleted( dto.getIsDeleted() );

        return user.build();
    }

    @Override
    public List<UserDto> toDtoList(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toEntityList(List<UserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDto userDto : dtoList ) {
            list.add( toEntity( userDto ) );
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
}
