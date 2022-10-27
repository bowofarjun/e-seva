import React, { useEffect } from "react";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import axios from "axios";

const Header = () =>{

    useEffect(()=>{
        let headers = {
            'Content-Type': 'application/json'
        };

        let url='/api/user/whoami';

        axios.get(url, {headers: headers}).then(
            (response)=>{
                localStorage.setItem('userId', response.data.userId)
                localStorage.setItem('userName', response.data.userName)
                localStorage.setItem('roleName', response.data.roleName)
            },
            (error)=>{
                console.log(error)
                localStorage.removeItem('userId');
                localStorage.removeItem('userName');
                localStorage.removeItem('roleName');
            }
        );
    },[]);

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
                        <u>{localStorage.getItem('userName')!=null? "Hi, "+localStorage.getItem('userName'):<Nav.Link href="/login-or-register">Login\Register</Nav.Link>}</u>
                    </Navbar.Text>
                </Navbar.Collapse>
            </Container>
        </Navbar>        
    </>
);
}

export default Header;