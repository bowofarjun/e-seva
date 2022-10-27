import React, { useEffect, useState } from "react";
import { Button, Form, Container, FloatingLabel } from "react-bootstrap";
import AccessDenied from "./AccessDenied";
import axios from "axios";
import {ToastContainer, toast} from 'react-toastify';

const NewServiceRequest = ()=>{

    const [languageList,setLanguageList]=useState([]);
    const [serviceList,setServiceList]=useState([]);
    const [newServiceRequestData, setNewServiceRequestData]=useState({requestedFor:null,serviceId:0,languageId:0,document:null,serviceRequestDescription:null});

    useEffect(()=>{
        loadLanguages();  
        loadServices();  
    },[])

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
    };

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

    const onSubmitButton=(e)=>{
        e.preventDefault();
        
        console.log(newServiceRequestData);

        let headers={
            'Content-Type': 'multipart/form-data'
        };

        let url = 'api/service-request';

        axios.post(url,newServiceRequestData,{headers:headers}).then(
            (response)=>{
                console.log(response.data)
                clearForm("new-service-request-form");
                setNewServiceRequestData({requestedFor:null,serviceId:0,languageId:0,document:null,serviceRequestDescription:null})
                notify("YOUR SERVICE REQUEST WITH ID: "+response.data.serviceRequestId+" IS RAISED SUCCESSFULLY!", true)            
            },
            (error)=>{
                console.log(error);
                notify("ERROR OCURRED WHILE RAISING SERVICE REQUEST!", false)
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
            {localStorage.getItem('roleName')===null? <AccessDenied/>:
            <Container>
            <ToastContainer className={".Toastify__progress-bar"} style={{background:'#222222'}}/>
            <h2>Raise New Service Request</h2> 
                <Form id="new-service-request-form">
                    <Form.Group className="mb-3" controlId="userId">
                        <Form.Control type="text" placeholder="Requested For UserId" onChange={(e)=>{setNewServiceRequestData({...newServiceRequestData,requestedFor:e.target.value})}} />
                    </Form.Group>
                    <Form.Select className="mb-3" aria-label="SERVICE" style={{marginBottom:"1rem !important"}} onChange={(e)=>{setNewServiceRequestData({...newServiceRequestData, serviceId:e.target.value})}}>
                        <option value="0">Select Your Service</option>
                        {
                            serviceList.map((service)=><option value={service.serviceId}>{service.serviceName}</option>)
                        }
                    </Form.Select>
                    <Form.Select className="mb-3" aria-label="LANGUAGE" style={{marginBottom:"1rem !important"}} onChange={(e)=>{setNewServiceRequestData({...newServiceRequestData, languageId:e.target.value})}}>
                        <option value="0">Select Your Language</option>
                        {
                            languageList.map((language)=><option value={language.languageId}>{language.languageName}</option>)
                        }
                    </Form.Select>
                    <Form.Group controlId="formFile" className="mb-3" onChange={(e)=>{setNewServiceRequestData({...newServiceRequestData, document:e.target.files[0]})}}>
                                    <Form.Label>Upload Your Id Proof</Form.Label>
                                    <Form.Control type="file" />
                    </Form.Group>
                    <FloatingLabel controlId="floatingTextarea2" label="Provide Request Summary or Justification" >
                        <Form.Control
                            as="textarea"
                            placeholder="Leave a comment here"
                            style={{ height: '100px',marginBottom:"1rem"}}
                            onChange={(e)=>{setNewServiceRequestData({...newServiceRequestData, serviceRequestDescription:e.target.value})}}
                        />
                    </FloatingLabel>
                    <Button variant="primary" type="submit" onClick={onSubmitButton}>
                        Submit
                    </Button>
                </Form>
            </Container>
            }
        </>
    )
}

export default NewServiceRequest;