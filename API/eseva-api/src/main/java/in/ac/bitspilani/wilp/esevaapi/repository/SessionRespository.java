package in.ac.bitspilani.wilp.esevaapi.repository;

import in.ac.bitspilani.wilp.esevaapi.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SessionRespository extends JpaRepository<Session, String> {

    @Procedure(name="VALIDATE_USER_SESSION")
    Map<String,?> VALIDATE_USER_SESSION(@Param("UserId") String userId, @Param("SessionId") String sessionId);
}
