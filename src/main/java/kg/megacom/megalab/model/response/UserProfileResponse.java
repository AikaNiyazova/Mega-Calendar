package kg.megacom.megalab.model.response;

import java.util.List;

public interface UserProfileResponse {

    Long getId();
    String getPhoto_path();
    String getFull_name();
    String getEmail();
    String getMsisdn();
    String getOrganization_name();
    String getDepartment_name();
    String getPosition_name();
    String getStatus();

}
