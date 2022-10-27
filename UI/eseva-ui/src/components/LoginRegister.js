import {React, useState, useEffect} from "react";
import { Col, Container, Row, Form, Button } from "react-bootstrap";
import axios from "axios";
import {ToastContainer, toast} from 'react-toastify';

const LoginRegister=()=>{

    const [roleList,setRoleList]=useState([]);
    const [languageList, setLanguageList] =useState([]);
    const [loginData, setLoginData]=useState({userId:null,password:null});
    const [registerData, setRegisterData]=useState({userId:null,roleId:0,userName:null,languageId:0,phoneNumber:null,password:null,document:null,emailId:null});

    useEffect(()=>{
        loadRoles();
        loadLanguages();
    },[]);
    
    const loadRoles=()=>
    {
        let headers = {
            'Content-Type': 'application/json'
        };

        let url='/api/role';

        axios.get(url, {headers: headers}).then(
            (response)=>{
                console.log(response.data)
                setRoleList(response.data);
            },
            (error)=>{
                console.log(error)
            }
        );
    };

    const loadLanguages=()=>{
        let headers = {
            'Content-Type': 'application/json'
        };

        let url='/api/language';

        axios.get(url, {headers: headers}).then(
            (response)=>{
                console.log(response.data)
                setLanguageList(response.data);
            },
            (error)=>{
                console.log(error)
            }
        );
    }

    const onSignInButtonSubmit=(e)=>{
        e.preventDefault();

        let headers = {
            'Content-Type': 'application/json'
        };

        let url='/api/user/sign-in'

        axios.post(url,loginData,{headers:headers}).then(
            (response)=>{
                console.log(response.data)
                localStorage.setItem('userId',loginData.userId)
                clearForm("signin-form");
                setLoginData({userId:null,password:null})
                notify("LOGIN SUCCESSFUL", true)
                window.location.replace("/");
                
                
            },
            (error)=>{
                console.log(error);
                notify("ERROR OCURRED WHILE LOGGING IN", false)
            }
        );          
    }

    const onSignUpButtonSubmit=(e)=>{
        e.preventDefault();

        let headers={
            'Content-Type': 'multipart/form-data'
        };

        let url='/api/user/sign-up';

        axios.post(url,registerData,{headers:headers}).then(
            (response)=>{
                console.log(response.data)
                clearForm("signup-form");
                setRegisterData({userId:null,roleId:0,userName:null,languageId:0,phoneNumber:null,password:null,document:null,emailId:null});
                notify("USER REGISTERATION SUCCESSFUL WITH PENDING STATUS. YOU WILL BE ABLE TO LOGIN ONCE ADMIN APPROVES.", true)            
            },
            (error)=>{
                console.log(error);
                notify("ERROR OCURRED WHILE USER REGISTRATION", false)
            }
        );   
    }

    const clearForm=(formId)=>{
        document.getElementById(formId).reset();
    }

    const notify =(message, flag)=>{
        flag===true? toast.success(message) : toast.error(message);
    }
    
    return (
        <>
            <Container style={{align:"center", height:"85vh", width:"60vw"}}>
                <ToastContainer className={".Toastify__progress-bar"} style={{background:'#222222'}}/>
                <Row style={{height:"70vh"}}>
                    <Col style={{background:"white", textAlign:"center", margin:"1vh 4vw 48vh 4vw", boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px"}}>
                        <h6>Already have an account?</h6>
                        <h2>Sign-In</h2>
                        <hr></hr>
                        <Container>
                            <Form id="signin-form">
                                <Form.Group className="mb-3" controlId="userId">
                                    <Form.Control type="text" placeholder="Please enter your user id" onChange={(e)=>{setLoginData({...loginData,userId:e.target.value})}} />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Control type="password" placeholder="Please enter your password" onChange={(e)=>{setLoginData({...loginData,password:e.target.value})}} />
                                    </Form.Group>
                                <Button variant="primary" type="submit" onClick={onSignInButtonSubmit}>
                                    Sign In
                                </Button>
                            </Form>
                        </Container>
                    </Col>
                    <Col style={{background:"white", height:"82vh", textAlign:"center",  margin:"1vh 4vw 1vh 4vw", boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px"}}>
                        <h6>Are you a new user?</h6>
                        <h2>Sign-Up</h2>
                        <hr></hr>
                        <Container>
                            <Form id="signup-form">
                                <Form.Group className="mb-3" controlId="userId">
                                    <Form.Control type="text" placeholder="Please Enter Your User Id" onChange={(e)=>{setRegisterData({...registerData, userId:e.target.value})}}/>
                                </Form.Group>
                                <Form.Select className="mb-3" aria-label="ROLE" style={{marginBottom:"1rem !important"}} onChange={(e)=>{setRegisterData({...registerData, roleId:e.target.value})}}>
                                    <option value="0">Select Your Role</option>
                                    {
                                        roleList.map((role)=><option value={role.roleId}>{role.roleName}</option>)
                                    }
                                </Form.Select>
                                <Form.Group className="mb-3" controlId="userName">
                                    <Form.Control type="text" placeholder="Please Enter Your Name" onChange={(e)=>{setRegisterData({...registerData, userName:e.target.value})}}/>
                                </Form.Group>
                                <Form.Select className="mb-3" aria-label="LANGUAGE" style={{marginBottom:"1rem !important"}} onChange={(e)=>{setRegisterData({...registerData, languageId:e.target.value})}}>
                                    <option value="0">Select Your Language</option>
                                    {
                                        languageList.map((language)=><option value={language.languageId}>{language.languageName}</option>)
                                    }
                                </Form.Select>
                                <Form.Group className="mb-3" controlId="phoneNumber">
                                    <Form.Control type="text" placeholder="Please Enter Your Phone Number" onChange={(e)=>{setRegisterData({...registerData, phoneNumber:e.target.value})}} />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="emailId">
                                    <Form.Control type="email" placeholder="Please Enter Your Email Id" onChange={(e)=>{setRegisterData({...registerData, emailId:e.target.value})}}/>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Control type="password" placeholder="Please Enter Your Password" onChange={(e)=>{setRegisterData({...registerData, password:e.target.value})}}/>
                                </Form.Group>
                                <Form.Group controlId="formFile" className="mb-3" onChange={(e)=>{setRegisterData({...registerData, document:e.target.files[0]})}}>
                                    <Form.Label>Upload Your Id Proof</Form.Label>
                                    <Form.Control type="file" />
                                </Form.Group>
                                <Button variant="secondary" type="submit" onClick={onSignUpButtonSubmit}>
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