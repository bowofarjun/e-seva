package in.ac.bitspilani.wilp.esevaapi.service.impl;

import in.ac.bitspilani.wilp.esevaapi.model.*;
import in.ac.bitspilani.wilp.esevaapi.repository.ServiceRequestRepository;
import in.ac.bitspilani.wilp.esevaapi.service.IServiceRequest;
import in.ac.bitspilani.wilp.esevaapi.util.EsevaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import static in.ac.bitspilani.wilp.esevaapi.util.EsevaUtil.validateNullStringInMap;

@Service
public class ServiceRequestImpl implements IServiceRequest {

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Override
    public List<ServiceRequest> getAllServiceRequestsForAUser(String userId) {

        return serviceRequestRepository.GET_ALL_SERVICE_REQUESTS_FOR_A_USER(userId);
    }

    @Override
    public NewServiceRequestResponse newServiceRequest(String userId, NewServiceRequestRequest newServiceRequestRequest) {
        NewServiceRequestResponse newServiceRequestResponse= new NewServiceRequestResponse();

        Blob blob;

        try
        {
            blob = EsevaUtil.convertToBlobFromMultiPart(newServiceRequestRequest.getDocument());
        }
        catch(Exception exception)
        {
            newServiceRequestResponse.setErrorMessage(exception.getMessage());
            newServiceRequestResponse.setHttpStatusCode(500);
            newServiceRequestResponse.setErrorCode(500);

            return newServiceRequestResponse;
        }

        Map<String,?> map = serviceRequestRepository.NEW_SERVICE_REQUEST(userId,
                newServiceRequestRequest.getRequestedFor(),
                newServiceRequestRequest.getServiceId(),
                newServiceRequestRequest.getLanguageId(),
                newServiceRequestRequest.getServiceRequestDescription(),
                blob);

        Integer errorCode = Integer.parseInt(validateNullStringInMap(map.get("errorCode")));
        String errorMessage = EsevaUtil.validateNullStringInMap(map.get("errorMessage"));

        if(errorCode==0 && errorMessage==null)
        {
            newServiceRequestResponse.setServiceRequestId(EsevaUtil.validateNullStringInMap(map.get("ServiceRequestId")));
            newServiceRequestResponse.setDocumentId(EsevaUtil.validateNullStringInMap(map.get("DocumentId")));
            newServiceRequestResponse.setHttpStatusCode(200);
        }
        else
        {
            newServiceRequestResponse.setErrorMessage(errorMessage);
            newServiceRequestResponse.setHttpStatusCode(500);
            newServiceRequestResponse.setErrorCode(errorCode);
        }

        return newServiceRequestResponse;
    }

    @Override
    public UpdateServiceRequestStatusResponse updateServiceRequestStatus(UpdateServiceRequestStatusRequest updateServiceRequestStatusRequest) {
        Map<String,?> map = serviceRequestRepository.UPDATE_SERVICE_REQUEST_STATUS(updateServiceRequestStatusRequest.getServiceRequestId(), updateServiceRequestStatusRequest.getStatusId());

        Integer errorCode = Integer.parseInt(validateNullStringInMap(map.get("errorCode")));
        String errorMessage = validateNullStringInMap(map.get("errorMessage"));

        UpdateServiceRequestStatusResponse response= new UpdateServiceRequestStatusResponse();

        if(errorCode==0 && errorMessage==null)
        {
            response.setHttpStatusCode(200);
        }
        else
        {
            response.setErrorCode(errorCode);
            response.setHttpStatusCode(500);
            response.setErrorMessage(errorMessage);
        }

        return response;

    }
}
