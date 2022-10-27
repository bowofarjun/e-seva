package in.ac.bitspilani.wilp.esevaapi.service;

import in.ac.bitspilani.wilp.esevaapi.model.NewServiceRequestRequest;
import in.ac.bitspilani.wilp.esevaapi.model.NewServiceRequestResponse;
import in.ac.bitspilani.wilp.esevaapi.model.ServiceRequest;

import java.util.List;

public interface IServiceRequest {
    List<ServiceRequest> getAllServiceRequestsForAUser(String userId);
    NewServiceRequestResponse newServiceRequest(String userId, NewServiceRequestRequest newServiceRequestRequest);
}
