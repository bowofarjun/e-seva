import React from "react";
import { Card, Col, Container, Row } from "react-bootstrap";
import { AiOutlineMail, AiOutlinePhone, AiOutlineWhatsApp,AiOutlineFacebook,AiOutlineLinkedin,AiOutlineInstagram } from "react-icons/ai";

const Contact=(props)=>{
    return (
        <>
            <Container style={{textAlign:"center"}}>
                <Row>
                    <Col>
                        <Card style={{width:'20vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem", padding:'1rem'}} className={"mx-auto"}>
                            <Card.Title>
                                <h1><AiOutlineMail/></h1>
                            </Card.Title>
                            <Card.Text>
                                <h4>
                                    <a href="mailto:bowofarjun@gmail.com">bowofarjun@gmail.com</a>
                                </h4>
                            </Card.Text>
                        </Card>   
                    </Col>
                    <Col>
                        <Card style={{width:'20vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem", padding:'1rem'}} className={"mx-auto"}>
                            <Card.Title>
                                <h1><AiOutlinePhone/></h1>
                            </Card.Title>
                            <Card.Text>
                                <h4>+91-9110605377</h4>
                            </Card.Text>
                        </Card>
                    </Col>
                    <Col>
                        <Card style={{width:'20vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem", padding:'1rem'}} className={"mx-auto"}>
                            <Card.Title>
                                <h1><AiOutlineWhatsApp/></h1>
                            </Card.Title>
                            <Card.Text>
                                <h4>+91-9457922233</h4>
                            </Card.Text>
                        </Card>
                    </Col>
                </Row>
                <Row>
                <Col>
                    <Card style={{width:'20vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem", padding:'1rem'}} className={"mx-auto"}>
                        <Card.Title>
                            <h1><AiOutlineFacebook/></h1>
                        </Card.Title>
                            <Card.Text>
                                <h4><a href="https://fb.com/ArjunMishra93">ArjunMishra93</a></h4>
                            </Card.Text>
                        </Card>
                    </Col>
                    <Col>
                        <Card style={{width:'20vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem", padding:'1rem'}} className={"mx-auto"}>
                            <Card.Title>
                                <h1><AiOutlineLinkedin/></h1>
                            </Card.Title>
                            <Card.Text>
                                <h4><a href="https://linkedin.com/in/arjunmishra13">arjunmishra13</a></h4>
                            </Card.Text>
                        </Card>
                    </Col>
                    <Col>
                        <Card style={{width:'20vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem", padding:'1rem'}} className={"mx-auto"}>
                            <Card.Title>
                                <h1><AiOutlineInstagram/></h1>
                            </Card.Title>
                            <Card.Text>
                                <h4>arjunmishra1393</h4>
                            </Card.Text>
                        </Card>
                    </Col>  
                </Row>
            </Container>
        </>
    );
}

export default Contact;