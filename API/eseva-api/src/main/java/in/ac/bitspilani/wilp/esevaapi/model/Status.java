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
    private int statusId;
    @Column
    @Getter @Setter
    private String statusName;
}
