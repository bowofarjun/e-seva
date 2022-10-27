package in.ac.bitspilani.wilp.esevaapi.repository;

import in.ac.bitspilani.wilp.esevaapi.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, String> {

    @Procedure(name="GET_ALL_SERVICE_REQUESTS_FOR_A_USER")
    List<ServiceRequest> GET_ALL_SERVICE_REQUESTS_FOR_A_USER(@Param("UserId") String userId);

    @Procedure(name = "NEW_SERVICE_REQUEST")
    Map<String,?> NEW_SERVICE_REQUEST(@Param("RequestedBy") String requestedBy,
                                      @Param("RequestedFor") String requestedFor,
                                      @Param("ServiceId") Integer serviceId,
                                      @Param("LanguageId") Integer languageId,
                                      @Param("ServiceRequestDescription") String serviceRequestDescription,
                                      @Param("Document") Blob document
                                      );
}
