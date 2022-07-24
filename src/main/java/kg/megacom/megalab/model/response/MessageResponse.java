package kg.megacom.megalab.model.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageResponse {

    String message;

    public static MessageResponse of(String message) {
        return new MessageResponse(message);
    }
}
