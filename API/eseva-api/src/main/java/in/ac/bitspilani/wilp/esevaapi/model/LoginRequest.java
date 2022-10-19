package in.ac.bitspilani.wilp.esevaapi.model;


import lombok.Getter;
import lombok.Setter;

public class LoginRequest {
    @Getter @Setter
    private String userId;

    @Getter @Setter
    private String password;
}
