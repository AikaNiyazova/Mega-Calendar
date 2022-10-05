package kg.megacom.megalab.model.request;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SendNotificationResult {

    @NonNull
    private String result;
}
