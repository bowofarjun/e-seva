package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;

public class UpdateServiceRequestStatusRequest {
    @Getter @Setter
    private String serviceRequestId;
    @Getter @Setter
    private Integer statusId;
}
