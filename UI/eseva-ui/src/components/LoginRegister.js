import {React} from "react";
import { Col, Container, Row, Form, Button } from "react-bootstrap";

const LoginRegister=(props)=>{

    let roleList = [
        {
            "roleId": 1,
            "roleName": "CITIZEN"
        },
        {
            "roleId": 2,
            "roleName": "VENDOR"
        },
        {
            "roleId": 3,
            "roleName": "ADMIN"
        }
    ];

    let languageList = [
        {
            "languageId": 1,
            "languageName": "Hindi"
        },
        {
            "languageId": 2,
            "languageName": "English"
        },
        {
            "languageId": 3,
            "languageName": "Bengali"
        },
        {
            "languageId": 4,
            "languageName": "Marathi"
        },
        {
            "languageId": 5,
            "languageName": "Telugu"
        },
        {
            "languageId": 6,
            "languageName": "Tamil"
        },
        {
            "languageId": 7,
            "languageName": "Gujarati"
        },
        {
            "languageId": 8,
            "languageName": "Urdu"
        },
        {
            "languageId": 9,
            "languageName": "Kannada"
        },
        {
            "languageId": 10,
            "languageName": "Odia"
        },
        {
            "languageId": 11,
            "languageName": "Malayalam"
        },
        {
            "languageId": 12,
            "languageName": "Punjabi"
        },
        {
            "languageId": 13,
            "languageName": "Assamese"
        },
        {
            "languageId": 14,
            "languageName": "Maithili"
        },
        {
            "languageId": 15,
            "languageName": "Meitei"
        },
        {
            "languageId": 16,
            "languageName": "Sanskrit"
        }
    ];

    return (
        <>
            <Container style={{align:"center", height:"85vh", width:"60vw"}}>
                <Row style={{height:"70vh"}}>
                    <Col style={{background:"white", textAlign:"center", margin:"4vh 4vw 48vh 4vw", boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px"}}>
                        <h5>Already have an account?</h5>
                        <h2>Sign-In</h2>
                        <hr></hr>
                        <Container>
                            <Form>
                                <Form.Group className="mb-3" controlId="userId">
                                    <Form.Control type="text" placeholder="Please enter your user id" />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Control type="password" placeholder="Please enter your password" />
                                    </Form.Group>
                                <Button variant="primary" type="submit">
                                    Sign In
                                </Button>
                            </Form>
                        </Container>
                    </Col>
                    <Col style={{background:"white", height:"82vh", textAlign:"center",  margin:"4vh 4vw 1vh 4vw", boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px"}}>
                        <h5>Are you a new user?</h5>
                        <h2>Sign-Up</h2>
                        <hr></hr>
                        <Container>
                            <Form>
                                <Form.Group className="mb-3" controlId="userId">
                                    <Form.Control type="text" placeholder="Please Enter Your User Id" />
                                </Form.Group>
                                <Form.Select className="mb-3" aria-label="ROLE" style={{marginBottom:"1rem !important;"}}>
                                    <option value="0">Select Your Role</option>
                                    {
                                        roleList.map((role)=><option value={role.roleId}>{role.roleName}</option>)
                                    }
                                </Form.Select>
                                <Form.Group className="mb-3" controlId="userName">
                                    <Form.Control type="text" placeholder="Please Enter Your Name" />
                                </Form.Group>
                                <Form.Select className="mb-3" aria-label="LANGUAGE" style={{marginBottom:"1rem !important;"}}>
                                    <option value="0">Select Your Language</option>
                                    {
                                        languageList.map((language)=><option value={language.languageId}>{language.languageName}</option>)
                                    }
                                </Form.Select>
                                <Form.Group className="mb-3" controlId="phoneNumber">
                                    <Form.Control type="text" placeholder="Please Enter Your Phone Number" />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="emailId">
                                    <Form.Control type="email" placeholder="Please Enter Your Email Id" />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Control type="password" placeholder="Please Enter Your Password" />
                                </Form.Group>
                                <Form.Group controlId="formFile" className="mb-3">
                                    <Form.Label>Upload Your Id Proof</Form.Label>
                                    <Form.Control type="file" />
                                </Form.Group>
                                <Button variant="secondary" type="submit">
                                    Sign-Up
                                </Button>
                            </Form>
                        </Container>
                    </Col>
                </Row>
            </Container>
        </>
    );
}

export default LoginRegister;