import React from "react";
import { Card, Container } from "react-bootstrap";

const About=(props)=>{
    return (
        <>
            <Container>
                <Card style={{padding:'1rem',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px"}}>
                <Card.Img variant="bottom" src="/about-us-eseva.jpg" style={{height:"20%", width:"30%", marginTop:"1rem"}} className="rounded mx-auto d-block" />
                    <Card.Text style={{textAlign:"center"}}>
                       <p>
                        <b>
                        THIS PROJECT IS DEVELOPED AS PART OF THE DATABASE DESIGN AND ANALYSIS COURSE OF BITS PILANI WORK INTEGRATED LEARNING PROGRAM.
                        </b>
                       </p>
                       <p>
                       e-Seva Technology aims to provide various online services, bringing financial and digital inclusion in Indian villages where the scope of online service in Rural India has never been so much necessary. But now with globalization and reach of internet, the need for reaching the hitherto unreached became the responsibility of a civilized society.
                       </p>
                       <p> 
e-Seva act as the vehicle of delivery for Government, private and social sector services to the rural, semi urban & urban citizens. e-Seva Kendra’s are developed as common platform for providing both social and private sector organizations to integrate their social and commercial goods and benefit the rural and urban population with IT as well as non-IT services.
                       </p>
                    </Card.Text>
                </Card>
            </Container>
        </>
    );
}

export default About;