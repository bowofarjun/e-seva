import React from "react";
import { Container,Card,Button } from "react-bootstrap";

const Home=(props)=>{
    return (
        <>
            <Container style={{textAlign:'center'}}>
            <Card style={{ width: '84vw', height:'60vh', boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px"}} >
                <Card.Img variant="bottom" src="/home-e-seva-main.jpg" style={{height:"50%", width:"50%", marginTop:"1rem"}} className="rounded mx-auto d-block" />
                <Card.Body>
                    <Card.Title>E-Seva: A BITS PILANI WILP PROJECT</Card.Title>
                    <Card.Text>
                    E-Seva will offer a one stop solution to citizens. Any citizen can go to the E-Seva website to resolve their issue. This is a web-based application and runs over the internet. Transactions at E-Seva will be done securely and confidentiality will be maintained to prevent outbreak of every individual's personal information. Citizens do not need to spend their time in queues.  Since this is an online portal, it will offer online services such as raise public issue requests, provide documentation services, e-filing, e-forms, and e-payments, etc.

                    </Card.Text>
                    <a href="/services"><Button variant="outline-primary">Explore Our Services</Button></a>
                </Card.Body>
            </Card>
            </Container>
        </>
    );
}

export default Home;