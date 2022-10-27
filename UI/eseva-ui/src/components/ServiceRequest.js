import React from "react";
import { Card,Row,Col } from "react-bootstrap";

const ServiceRequest=(item)=>{

    return (
        <>
        <div style={{textAlign:'center'}}>
        <Card style={{width:'40vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem"}} className={"mx-auto"}>
            <Card.Title>
                {item.serviceRequestId}
            </Card.Title>
            <Card.Text>
                <Row>
                    <Col><b>ServiceName</b></Col>
                    <Col>{item.serviceName}</Col>
                </Row>
                <Row>
                    <Col><b>RequestedBy</b></Col>
                    <Col>{item.requestedBy}</Col>
                </Row>
                <Row>
                    <Col><b>RequestedFor</b></Col>
                    <Col>{item.requestedFor}</Col>
                </Row>
                <Row>
                    <Col><b>Language</b></Col>
                    <Col>{item.languageName}</Col>
                </Row>
                <Row>
                    <Col><b>CreatedDate</b></Col>
                    <Col>{item.createdDate}</Col>
                </Row>
                <Row>
                    <Col><b>ModifiedDate</b></Col>
                    <Col>{item.modifiedDate}</Col>
                </Row>
                <Row>
                    <Col><b>Status</b></Col>
                    <Col><div style={item.statusName==='APPROVED'? {background:"green",color:"white"}:(item.statusName==='PENDING'?{background:"yellow"}:{background:"red",color:"white"})}>{item.statusName}</div></Col>
                </Row>
            </Card.Text>
        </Card>   
        </div>
        
        </>
    )
};

export default ServiceRequest;