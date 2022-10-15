package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Status {
    @Id
    @Column
    @Getter @Setter
    int StatusId;
    @Column
    @Getter @Setter
    String StatusName;
}
