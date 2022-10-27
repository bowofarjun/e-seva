package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Service {
    @Id
    @Column
    @Getter @Setter
    private int serviceId;

    @Column
    @Getter @Setter
    private String serviceName;

    @Column
    @Getter @Setter
    private String serviceDescription;

    @Column
    @Getter @Setter
    private String serviceImgLoc;
}
