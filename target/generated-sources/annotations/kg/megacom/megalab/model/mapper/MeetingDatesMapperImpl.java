package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.MeetingDatesDto;
import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Meeting;
import kg.megacom.megalab.model.entity.MeetingDates;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.entity.Room;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Authority;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-19T17:38:23+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class MeetingDatesMapperImpl implements MeetingDatesMapper {

    @Override
    public MeetingDatesDto toDto(MeetingDates entity) {
        if ( entity == null ) {
            return null;
        }

        MeetingDatesDto.MeetingDatesDtoBuilder meetingDatesDto = MeetingDatesDto.builder();

        meetingDatesDto.id( entity.getId() );
        meetingDatesDto.meeting( meetingToMeetingDto( entity.getMeeting() ) );
        meetingDatesDto.meetingDate( entity.getMeetingDate() );

        return meetingDatesDto.build();
    }

    @Override
    public MeetingDates toEntity(MeetingDatesDto dto) {
        if ( dto == null ) {
            return null;
        }

        MeetingDates.MeetingDatesBuilder meetingDates = MeetingDates.builder();

        meetingDates.id( dto.getId() );
        meetingDates.meeting( meetingDtoToMeeting( dto.getMeeting() ) );
        meetingDates.meetingDate( dto.getMeetingDate() );

        return meetingDates.build();
    }

    @Override
    public List<MeetingDatesDto> toDtoList(List<MeetingDates> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MeetingDatesDto> list = new ArrayList<MeetingDatesDto>( entityList.size() );
        for ( MeetingDates meetingDates : entityList ) {
            list.add( toDto( meetingDates ) );
        }

        return list;
    }

    @Override
    public List<MeetingDates> toEntityList(List<MeetingDatesDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MeetingDates> list = new ArrayList<MeetingDates>( dtoList.size() );
        for ( MeetingDatesDto meetingDatesDto : dtoList ) {
            list.add( toEntity( meetingDatesDto ) );
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

    protected RoomDto roomToRoomDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto.RoomDtoBuilder roomDto = RoomDto.builder();

        roomDto.id( room.getId() );
        roomDto.roomName( room.getRoomName() );
        roomDto.roomCapacity( room.getRoomCapacity() );
        roomDto.location( room.getLocation() );
        roomDto.isDashboardAvailable( room.getIsDashboardAvailable() );
        roomDto.isProjectorAvailable( room.getIsProjectorAvailable() );
        roomDto.isAcAvailable( room.getIsAcAvailable() );

        return roomDto.build();
    }

    protected MeetingDto meetingToMeetingDto(Meeting meeting) {
        if ( meeting == null ) {
            return null;
        }

        MeetingDto.MeetingDtoBuilder meetingDto = MeetingDto.builder();

        meetingDto.id( meeting.getId() );
        meetingDto.meetingAuthor( userToUserDto( meeting.getMeetingAuthor() ) );
        meetingDto.meetingTopic( meeting.getMeetingTopic() );
        meetingDto.meetingStartTime( meeting.getMeetingStartTime() );
        meetingDto.meetingEndTime( meeting.getMeetingEndTime() );
        meetingDto.room( roomToRoomDto( meeting.getRoom() ) );
        meetingDto.address( meeting.getAddress() );
        meetingDto.isVisible( meeting.getIsVisible() );

        return meetingDto.build();
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

    protected Room roomDtoToRoom(RoomDto roomDto) {
        if ( roomDto == null ) {
            return null;
        }

        Room.RoomBuilder room = Room.builder();

        room.id( roomDto.getId() );
        room.roomName( roomDto.getRoomName() );
        room.roomCapacity( roomDto.getRoomCapacity() );
        room.location( roomDto.getLocation() );
        room.isDashboardAvailable( roomDto.getIsDashboardAvailable() );
        room.isProjectorAvailable( roomDto.getIsProjectorAvailable() );
        room.isAcAvailable( roomDto.getIsAcAvailable() );

        return room.build();
    }

    protected Meeting meetingDtoToMeeting(MeetingDto meetingDto) {
        if ( meetingDto == null ) {
            return null;
        }

        Meeting.MeetingBuilder meeting = Meeting.builder();

        meeting.id( meetingDto.getId() );
        meeting.meetingAuthor( userDtoToUser( meetingDto.getMeetingAuthor() ) );
        meeting.meetingTopic( meetingDto.getMeetingTopic() );
        meeting.meetingStartTime( meetingDto.getMeetingStartTime() );
        meeting.meetingEndTime( meetingDto.getMeetingEndTime() );
        meeting.room( roomDtoToRoom( meetingDto.getRoom() ) );
        meeting.address( meetingDto.getAddress() );
        meeting.isVisible( meetingDto.getIsVisible() );

        return meeting.build();
    }
}
