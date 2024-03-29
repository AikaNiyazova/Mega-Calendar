package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Label;
import kg.megacom.megalab.model.entity.Meeting;
import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-06T11:54:55+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class MeetingUserMapperImpl implements MeetingUserMapper {

    @Override
    public MeetingUserDto toDto(MeetingUser entity) {
        if ( entity == null ) {
            return null;
        }

        MeetingUserDto.MeetingUserDtoBuilder meetingUserDto = MeetingUserDto.builder();

        meetingUserDto.id( entity.getId() );
        meetingUserDto.meeting( meetingToMeetingDto( entity.getMeeting() ) );
        meetingUserDto.user( userToUserDto( entity.getUser() ) );
        meetingUserDto.status( entity.getStatus() );
        meetingUserDto.delegate( userToUserDto( entity.getDelegate() ) );
        meetingUserDto.label( labelToLabelDto( entity.getLabel() ) );
        meetingUserDto.reasonForRejection( entity.getReasonForRejection() );

        return meetingUserDto.build();
    }

    @Override
    public MeetingUser toEntity(MeetingUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        MeetingUser.MeetingUserBuilder meetingUser = MeetingUser.builder();

        meetingUser.id( dto.getId() );
        meetingUser.meeting( meetingDtoToMeeting( dto.getMeeting() ) );
        meetingUser.user( userDtoToUser( dto.getUser() ) );
        meetingUser.status( dto.getStatus() );
        meetingUser.delegate( userDtoToUser( dto.getDelegate() ) );
        meetingUser.label( labelDtoToLabel( dto.getLabel() ) );
        meetingUser.reasonForRejection( dto.getReasonForRejection() );

        return meetingUser.build();
    }

    @Override
    public List<MeetingUserDto> toDtoList(List<MeetingUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MeetingUserDto> list = new ArrayList<MeetingUserDto>( entityList.size() );
        for ( MeetingUser meetingUser : entityList ) {
            list.add( toDto( meetingUser ) );
        }

        return list;
    }

    @Override
    public List<MeetingUser> toEntityList(List<MeetingUserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MeetingUser> list = new ArrayList<MeetingUser>( dtoList.size() );
        for ( MeetingUserDto meetingUserDto : dtoList ) {
            list.add( toEntity( meetingUserDto ) );
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

    protected MeetingDto meetingToMeetingDto(Meeting meeting) {
        if ( meeting == null ) {
            return null;
        }

        MeetingDto.MeetingDtoBuilder meetingDto = MeetingDto.builder();

        meetingDto.id( meeting.getId() );
        meetingDto.meetingAuthor( userToUserDto( meeting.getMeetingAuthor() ) );
        meetingDto.meetingTopic( meeting.getMeetingTopic() );
        meetingDto.address( meeting.getAddress() );
        meetingDto.isVisible( meeting.getIsVisible() );
        meetingDto.isRepeatable( meeting.getIsRepeatable() );

        return meetingDto.build();
    }

    protected LabelDto labelToLabelDto(Label label) {
        if ( label == null ) {
            return null;
        }

        LabelDto.LabelDtoBuilder labelDto = LabelDto.builder();

        labelDto.id( label.getId() );
        labelDto.user( userToUserDto( label.getUser() ) );
        labelDto.labelName( label.getLabelName() );
        labelDto.labelColor( label.getLabelColor() );

        return labelDto.build();
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

    protected Meeting meetingDtoToMeeting(MeetingDto meetingDto) {
        if ( meetingDto == null ) {
            return null;
        }

        Meeting.MeetingBuilder meeting = Meeting.builder();

        meeting.id( meetingDto.getId() );
        meeting.meetingAuthor( userDtoToUser( meetingDto.getMeetingAuthor() ) );
        meeting.meetingTopic( meetingDto.getMeetingTopic() );
        meeting.address( meetingDto.getAddress() );
        meeting.isVisible( meetingDto.getIsVisible() );
        meeting.isRepeatable( meetingDto.getIsRepeatable() );

        return meeting.build();
    }

    protected Label labelDtoToLabel(LabelDto labelDto) {
        if ( labelDto == null ) {
            return null;
        }

        Label.LabelBuilder label = Label.builder();

        label.id( labelDto.getId() );
        label.user( userDtoToUser( labelDto.getUser() ) );
        label.labelName( labelDto.getLabelName() );
        label.labelColor( labelDto.getLabelColor() );

        return label.build();
    }
}
