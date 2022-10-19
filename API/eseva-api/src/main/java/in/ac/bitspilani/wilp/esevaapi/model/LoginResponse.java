package in.ac.bitspilani.wilp.esevaapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginResponse extends ErrorResponse {
    @Getter @Setter
    private String userName;
    @Getter @Setter
    private String accessToken;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private Integer pendingLoginAttempts;
}
