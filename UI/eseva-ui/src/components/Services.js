import React from "react";
import { Col, Row, Button, Card, Container } from "react-bootstrap";

const Services=(props)=>{

    const serviceList = [
        {
            "serviceId": 1,
            "serviceName": "Fastag",
            "serviceDescription": "We are authorized Corporate Partner for FASTag product in India. We have the facility of FASTag Retail Agent for distributing.",
            "serviceImgLoc": "/service-fastag.png"
        },
        {
            "serviceId": 2,
            "serviceName": "Pan Card",
            "serviceDescription": "We are leading PAN card agency provider and with us you can start an online / authorized PAN Card Center of the UTIITSL.",
            "serviceImgLoc": "/service-pan-card.png"
        },
        {
            "serviceId": 3,
            "serviceName": "Digital Signature",
            "serviceDescription": "e-Seva is designed to help partners offer DSC services without large capital associated with building and maintaining.",
            "serviceImgLoc": "/service-digital-signature.jpg"
        },
        {
            "serviceId": 4,
            "serviceName": "Ayushman Card",
            "serviceDescription": "The Pradhan Mantri Jan Arogya Yojana (PMJAY) popularly known as Ayushman Bharat Yojana Scheme.",
            "serviceImgLoc": "/service-ayushman-yojna.jpeg"
        },
        {
            "serviceId": 5,
            "serviceName": "ITR Service",
            "serviceDescription": "Income Tax Return (ITR) is a form in which the taxpayers file information about his income earned and tax applicable to.",
            "serviceImgLoc": "/service-itr_service.png"
        },
        {
            "serviceId": 6,
            "serviceName": "GST Suvidha",
            "serviceDescription": "GST Suvidha- GST Suvidha is considered as an enabler or authorised intermediary for businesses to access GST portal services.",
            "serviceImgLoc": "/service-gst.png"
        },
    ];
    
    return (
        <div style={{textAlign:'center'}}>
        <Container style={{textAlign:'center'}}>
            <Row>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===1).serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===1).serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===1).serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===2).serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===2).serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===2).serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===3).serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===3).serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===3).serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
            <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===4).serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===4).serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===4).serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===5).serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===5).serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===5).serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===6).serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===6).serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===6).serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            </Container> 
            <Container style={{textAlign:'center', marginRight:"9rem"}}>
                <Row>
                    <Card>
                        <a href="/new-service-request"><Button variant="outline-primary">Raise New Service Request</Button></a>
                    </Card>
                </Row>
                <Row>
                    <Card>
                        <a href="/previous-service-requests"><Button variant="outline-secondary">Your Previous Service Requests</Button></a>
                    </Card>
                </Row>
            </Container>
        </div>
    );
}

export default Services;