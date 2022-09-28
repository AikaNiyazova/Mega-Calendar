package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddAdditionalPositionRequest {

    @NotNull
    @Positive
    Long userId;

    @NotNull
    @Positive
    Long organizationId;

    @NotNull
    @Positive
    Long departmentId;

    @NotNull
    @Positive
    Long positionId;
}
