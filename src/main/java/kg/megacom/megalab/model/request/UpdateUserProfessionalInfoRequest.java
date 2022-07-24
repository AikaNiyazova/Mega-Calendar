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
public class UpdateUserProfessionalInfoRequest {

    @NotNull
    @Positive
    Long userId;

    @NotNull
    @Positive
    Long oldOrganizationId;

    @NotNull
    @Positive
    Long newOrganizationId;

    @NotNull
    @Positive
    Long oldDepartmentId;

    @NotNull
    @Positive
    Long newDepartmentId;

    @NotNull
    @Positive
    Long oldPositionId;

    @NotNull
    @Positive
    Long newPositionId;
}
