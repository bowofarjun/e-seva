package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    @Column
    @Getter @Setter
    private int roleId;
    @Column
    @Getter @Setter
    private String roleName;
}
