package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateDepartmentRequest {

    @NotNull
    @Positive
    Long departmentId;

    @NotBlank(message = "Department name cannot be blank")
    String departmentName;

    @NotNull
    @Positive
    Long headId;
}
