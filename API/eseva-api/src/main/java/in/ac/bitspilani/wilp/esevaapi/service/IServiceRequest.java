package in.ac.bitspilani.wilp.esevaapi.service;

import in.ac.bitspilani.wilp.esevaapi.model.*;

import java.util.List;

public interface IServiceRequest {
    List<ServiceRequest> getAllServiceRequestsForAUser(String userId);
    NewServiceRequestResponse newServiceRequest(String userId, NewServiceRequestRequest newServiceRequestRequest);
    UpdateServiceRequestStatusResponse updateServiceRequestStatus(UpdateServiceRequestStatusRequest updateServiceRequestStatusRequest);
}
