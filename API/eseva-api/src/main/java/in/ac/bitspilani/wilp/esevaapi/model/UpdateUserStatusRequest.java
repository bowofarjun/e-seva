package in.ac.bitspilani.wilp.esevaapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateUserStatusRequest {
    @Getter @Setter
    private String userId;
    @Getter @Setter
    private Integer statusId;
}
