package in.ac.bitspilani.wilp.esevaapi.repository;

import in.ac.bitspilani.wilp.esevaapi.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

        @Procedure
        List<Service> GET_ALL_SERVICES();
}
