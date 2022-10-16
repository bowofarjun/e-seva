package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class RegistrationRequest {
    @Getter @Setter
    private String userId;

    @Getter @Setter
    private Integer roleId;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private Integer languageId;

    @Getter @Setter
    private String phoneNumber;

    @Getter @Setter
    private String emailId;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private MultipartFile document;
}
