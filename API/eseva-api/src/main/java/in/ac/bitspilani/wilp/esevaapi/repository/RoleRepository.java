package in.ac.bitspilani.wilp.esevaapi.repository;

import in.ac.bitspilani.wilp.esevaapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Procedure
    List<Role> GET_ALL_ROLES();
}
