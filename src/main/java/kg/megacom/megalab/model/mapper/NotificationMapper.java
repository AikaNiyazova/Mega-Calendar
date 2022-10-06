package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.NotificationDto;
import kg.megacom.megalab.model.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper extends BaseMapper<Notification, NotificationDto> {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

}
