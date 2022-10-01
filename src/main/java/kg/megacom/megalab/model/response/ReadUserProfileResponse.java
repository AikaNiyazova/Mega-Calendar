package kg.megacom.megalab.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

@Getter
@Setter
@SqlResultSetMapping(
        name = "userDto",
        entities = {
                @EntityResult(
                        entityClass = ReadUserProfileResponse.class, // имя текущего класса
                        fields = {
                                @FieldResult(name = "photoPath", column = "photo_path"),
                                @FieldResult(name = "fullName", column = "full_name"),
//                                @FieldResult(name = "firstName", column = "first_name"),
//                                @FieldResult(name = "patronymic", column = "patronymic"),
                                @FieldResult(name = "email", column = "email"),
                                @FieldResult(name = "msisdn", column = "msisdn"),
                                @FieldResult(name = "organizations", column = "organization_name"),
                                @FieldResult(name = "departments", column = "department_name"),
                                @FieldResult(name = "positions", column = "position_name"),
                                @FieldResult(name = "status", column = "status"),
                        }
                )
        }
)
public class ReadUserProfileResponse {

    String photoPath;
    String fullName;
//    String firstName;
//    String patronymic;
    String email;
    String msisdn;
    List<String> organizations;
    List<String> departments;
    List<String> positions;
    String status;

}
