package in.ac.bitspilani.wilp.esevaapi.repository;

import in.ac.bitspilani.wilp.esevaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Procedure(name="ADD_NEW_USER")
    Map<String,?> ADD_NEW_USER(@Param("UserId") String userId,
                                      @Param("RoleId") Integer roleId,
                                      @Param("UserName") String userName,
                                      @Param("LanguageId") Integer languageId,
                                      @Param("PhoneNumber") String phoneNumber,
                                      @Param("EmailId") String emailId,
                                      @Param("Password") String password,
                                      @Param("Document") Blob document
    );
}