package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class NewServiceRequestRequest {
    @Getter @Setter
    private String requestedFor;
    @Getter @Setter
    private Integer serviceId;
    @Getter @Setter
    private Integer languageId;
    @Getter @Setter
    private String serviceRequestDescription;
    @Getter @Setter
    private MultipartFile document;
}
