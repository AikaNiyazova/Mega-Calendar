package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Label;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-06T11:54:56+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class LabelMapperImpl implements LabelMapper {

    @Override
    public LabelDto toDto(Label entity) {
        if ( entity == null ) {
            return null;
        }

        LabelDto.LabelDtoBuilder labelDto = LabelDto.builder();

        labelDto.id( entity.getId() );
        labelDto.user( userToUserDto( entity.getUser() ) );
        labelDto.labelName( entity.getLabelName() );
        labelDto.labelColor( entity.getLabelColor() );

        return labelDto.build();
    }

    @Override
    public Label toEntity(LabelDto dto) {
        if ( dto == null ) {
            return null;
        }

        Label.LabelBuilder label = Label.builder();

        label.id( dto.getId() );
        label.user( userDtoToUser( dto.getUser() ) );
        label.labelName( dto.getLabelName() );
        label.labelColor( dto.getLabelColor() );

        return label.build();
    }

    @Override
    public List<LabelDto> toDtoList(List<Label> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LabelDto> list = new ArrayList<LabelDto>( entityList.size() );
        for ( Label label : entityList ) {
            list.add( toDto( label ) );
        }

        return list;
    }

    @Override
    public List<Label> toEntityList(List<LabelDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Label> list = new ArrayList<Label>( dtoList.size() );
        for ( LabelDto labelDto : dtoList ) {
            list.add( toEntity( labelDto ) );
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
