package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Language {
    @Id
    @Column
    @Getter @Setter
    private int languageId;
    @Column
    @Getter @Setter
    private String languageName;
}
