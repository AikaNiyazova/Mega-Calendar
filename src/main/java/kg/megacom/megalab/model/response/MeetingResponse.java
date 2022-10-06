package kg.megacom.megalab.model.response;

import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.dto.RoomDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingResponse {

    MeetingDto meetingDto;
    List<LocalDate> dates;
    LocalTime meetingStartTime;
    LocalTime meetingEndTime;
    RoomDto roomDto;

//    Long id;
//    User meetingAuthor;
//    String meetingTopic;
//
//    List<LocalDate> meetingDates;
//
//    String roomName;
//    String roomLocation;
//
////    String address;
//
//    Boolean isVisible;

}
