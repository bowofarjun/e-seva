import React from "react";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const Header = (props) =>{
    return (
    <>
        <Navbar bg="dark" variant="dark" style={{marginBottom:'1rem'}}>
            <Container>
                <Navbar.Brand href="/home">
                    E-SEVA
                </Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/home">Home</Nav.Link>
                    <Nav.Link href="/services">Our Services</Nav.Link>
                    <Nav.Link href="/about">About Us</Nav.Link>
                    <Nav.Link href="/contact">Contact Us</Nav.Link>
                </Nav>
                <Navbar.Collapse className="justify-content-end">
                    <Navbar.Text>
                        <u>{props.username!=null && props.username.length!==0? "Hi, "+props.username:<Nav.Link href="/login-or-register">Login\Register</Nav.Link>}</u>
                    </Navbar.Text>
                </Navbar.Collapse>
            </Container>
        </Navbar>        
    </>
);
}

export default Header;