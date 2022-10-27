import React from "react";
import { Card,Row,Col } from "react-bootstrap";

const ServiceRequest=(props)=>{

    const serviceReqData={
        "serviceRequestId": "1A6A2A8E-8270-4F79-B653-E782D7381FEE",
        "updatedBy": "testuser",
        "requestedBy": "testuser",
        "requestedFor": "testuser",
        "serviceName": "Fastag",
        "statusName": "PENDING",
        "languageName": "English",
        "serviceRequestDescription": "fffv fvfvfvn tgtg",
        "createdDate": "2022-10-27T03:49:23.483+00:00",
        "modifiedDate": "2022-10-27T03:49:23.483+00:00",
        "documentId": "DBF4BFED-BD7D-43EB-BDBE-4266A8274C1E"
    };

    return (
        <>
        <div style={{textAlign:'center'}}>
        <Card style={{width:'40vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem"}} className={"mx-auto"}>
            <Card.Title>
                {serviceReqData.serviceRequestId}
            </Card.Title>
            <Card.Text>
                <Row>
                    <Col><b>ServiceName</b></Col>
                    <Col>{serviceReqData.serviceName}</Col>
                </Row>
                <Row>
                    <Col><b>RequestedBy</b></Col>
                    <Col>{serviceReqData.requestedBy}</Col>
                </Row>
                <Row>
                    <Col><b>RequestedFor</b></Col>
                    <Col>{serviceReqData.requestedFor}</Col>
                </Row>
                <Row>
                    <Col><b>Language</b></Col>
                    <Col>{serviceReqData.languageName}</Col>
                </Row>
                <Row>
                    <Col><b>CreatedDate</b></Col>
                    <Col>{serviceReqData.createdDate}</Col>
                </Row>
                <Row>
                    <Col><b>ModifiedDate</b></Col>
                    <Col>{serviceReqData.modifiedDate}</Col>
                </Row>
                <Row>
                    <Col><b>Status</b></Col>
                    <Col>{serviceReqData.statusName}</Col>
                </Row>
            </Card.Text>
        </Card>   
        </div>
        
        </>
    )
};

export default ServiceRequest;