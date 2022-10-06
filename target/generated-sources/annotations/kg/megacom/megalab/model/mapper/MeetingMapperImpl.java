package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Meeting;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-06T11:54:56+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class MeetingMapperImpl implements MeetingMapper {

    @Override
    public MeetingDto toDto(Meeting entity) {
        if ( entity == null ) {
            return null;
        }

        MeetingDto.MeetingDtoBuilder meetingDto = MeetingDto.builder();

        meetingDto.id( entity.getId() );
        meetingDto.meetingAuthor( userToUserDto( entity.getMeetingAuthor() ) );
        meetingDto.meetingTopic( entity.getMeetingTopic() );
        meetingDto.address( entity.getAddress() );
        meetingDto.isVisible( entity.getIsVisible() );
        meetingDto.isRepeatable( entity.getIsRepeatable() );

        return meetingDto.build();
    }

    @Override
    public Meeting toEntity(MeetingDto dto) {
        if ( dto == null ) {
            return null;
        }

        Meeting.MeetingBuilder meeting = Meeting.builder();

        meeting.id( dto.getId() );
        meeting.meetingAuthor( userDtoToUser( dto.getMeetingAuthor() ) );
        meeting.meetingTopic( dto.getMeetingTopic() );
        meeting.address( dto.getAddress() );
        meeting.isVisible( dto.getIsVisible() );
        meeting.isRepeatable( dto.getIsRepeatable() );

        return meeting.build();
    }

    @Override
    public List<MeetingDto> toDtoList(List<Meeting> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MeetingDto> list = new ArrayList<MeetingDto>( entityList.size() );
        for ( Meeting meeting : entityList ) {
            list.add( toDto( meeting ) );
        }

        return list;
    }

    @Override
    public List<Meeting> toEntityList(List<MeetingDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Meeting> list = new ArrayList<Meeting>( dtoList.size() );
        for ( MeetingDto meetingDto : dtoList ) {
            list.add( toEntity( meetingDto ) );
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
