import React, { useEffect,useState } from "react";
import { Col, Row, Button, Card, Container } from "react-bootstrap";
import axios from "axios";

const Services=()=>{
    const [serviceList, setServiceList] = useState([]);
    const loadServices=()=>{
        
        let headers = {
            'Content-Type': 'application/json'
        };

        let url='/api/service';

        axios.get(url, {headers: headers}).then(
            (response)=>{
                console.log(response.data)
                setServiceList(response.data);
            },
            (error)=>{
                console.log(error)
            }
        );
    };


    useEffect(()=>{
        loadServices();
    },[])

    

    return (
        <div style={{textAlign:'center'}}>
        <Container style={{textAlign:'center'}}>
            <Row>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===1)?.serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===1)?.serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===1)?.serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===2)?.serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===2)?.serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===2)?.serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===3)?.serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===3)?.serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===3)?.serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
            <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===4)?.serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===4)?.serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===4)?.serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===5)?.serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===5)?.serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===5)?.serviceDescription}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{width:'20vw', height:'30vh',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px", marginBottom:'1rem'}}>
                        <Card.Img variant="top" src={serviceList.find(obj=>obj.serviceId===6)?.serviceImgLoc} className="rounded mx-auto d-block" style={{width:"60px"}} />
                        <Card.Body>
                            <Card.Title>{serviceList.find(obj=>obj.serviceId===6)?.serviceName}</Card.Title>
                            <Card.Text>
                                {serviceList.find(obj=>obj.serviceId===6)?.serviceDescription}
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