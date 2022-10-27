import React, { useEffect, useState } from "react";
import { Card, Col, Container, Row, Form, Button} from "react-bootstrap";
import AccessDenied from "./AccessDenied";
import axios from "axios";
import { ToastContainer,toast } from "react-toastify";

const Admin=()=>{

    useEffect(()=>{
        loadStatuses();
    },[])

    const [statusList, setStatusList]=useState([]);
    const [updateServiceRequestData, setUpdateServiceRequestData]=useState({serviceRequestId:null, statusId:0});
    const [updateUserStatusData,setUpdateUserStatusData]=useState({userId:null, statusId:0});

    const loadStatuses=()=>
    {
        let headers = {
            'Content-Type': 'application/json'
        };

        let url='/api/status';

        axios.get(url, {headers: headers}).then(
            (response)=>{
                console.log(response.data)
                setStatusList(response.data);
            },
            (error)=>{
                console.log(error)
            }
        );
    }

    const onClickUpdateServiceRequestStatus=(e)=>{
        e.preventDefault();

        let url='/api/service-request/status'

        let headers = {
            'Content-Type': 'application/json'
        };

        axios.put(url,updateServiceRequestData,{headers:headers}).then(
            (response)=>{
                console.log(response.data)
                notify("SERVICE REQUEST STATUS UPDATED SUCCESSFULLY!", true);
                setUpdateServiceRequestData({serviceRequestId:null, statusId:0});
                clearForm("update-svc-req-form")
            },
            (error)=>{
                notify("SOME ERROR OCURRED WHILE UPDATING SERVICE REQUEST STATUS", false)
            }
        )
    }

    const onClickUpdateUserStatus=(e)=>{
        e.preventDefault();

        let url='/api/user/status'

        let headers = {
            'Content-Type': 'application/json'
        };

        axios.put(url,updateUserStatusData,{headers:headers}).then(
            (response)=>{
                console.log(response.data)
                notify("SERVICE REQUEST STATUS UPDATED SUCCESSFULLY!", true);
                setUpdateUserStatusData({userId:null, statusId:0})
                clearForm("update-user-status-form")
            },
            (error)=>{
                notify("SOME ERROR OCURRED WHILE UPDATING SERVICE REQUEST STATUS", false);
            }
        )
    }

    const clearForm=(formId)=>{
        document.getElementById(formId).reset();
    }

    const notify =(message, flag)=>{
        flag===true? toast.success(message) : toast.error(message);
    }

    return(
        <>
            {localStorage.getItem('roleName')!=='ADMIN'? <AccessDenied/>:
            <div style={{textAlign:"center"}}>
                <ToastContainer className={".Toastify__progress-bar"} style={{background:'#222222'}}/>
                <Row>
                    <Col>
                        <Container>
                            <Card style={{width:'40vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem"}} className={"mx-auto"}>
                                <Card.Title>Update Service Request Status</Card.Title>
                                <Card.Text>
                                <Form id="update-svc-req-form">
                                <Form.Group className="mb-3" controlId="userId">
                                    <Form.Control type="text" placeholder="Please enter the Service Request Id" onChange={(e)=>{setUpdateServiceRequestData({...updateServiceRequestData,serviceRequestId:e.target.value})}} />
                                </Form.Group>
                                <Form.Select className="mb-3" aria-label="STATUS" style={{marginBottom:"1rem !important"}} onChange={(e)=>{setUpdateServiceRequestData({...updateServiceRequestData, statusId:e.target.value})}}>
                                    <option value="0">Select Status</option>
                                    {
                                        statusList.filter((status)=> (status.statusName==='APPROVED'|| status.statusName==='REJECTED'|| status.statusName==="PENDING")).map((status)=><option value={status.statusId}>{status.statusName}</option>)
                                    }
                                </Form.Select>
                                <Button variant="primary" type="submit" onClick={onClickUpdateServiceRequestStatus}>
                                    Update
                                </Button>
                            </Form>
                                </Card.Text>
                            </Card>
                            
                        </Container>
                    </Col>
                    <Col>
                        <Container>
                            <Card style={{width:'40vw',boxShadow:"rgb(38, 57, 77) 0px 20px 30px -10px",marginBottom:"1rem"}} className={"mx-auto"}>
                                <Card.Title>Update User Status</Card.Title>
                                <Card.Text>
                                <Form id="update-user-status-form">
                                <Form.Group className="mb-3" controlId="userId">
                                    <Form.Control type="text" placeholder="Please enter the User Id" onChange={(e)=>{setUpdateUserStatusData({...updateUserStatusData,userId:e.target.value})}} />
                                </Form.Group>
                                <Form.Select className="mb-3" aria-label="STATUS" style={{marginBottom:"1rem !important"}} onChange={(e)=>{setUpdateUserStatusData({...updateUserStatusData, statusId:e.target.value})}}>
                                    <option value="0">Select Status</option>
                                    {
                                        statusList.filter((status)=> (status.statusName==='ACTIVE'|| status.statusName==='INACTIVE'|| status.statusName==="PENDING")).map((status)=><option value={status.statusId}>{status.statusName}</option>)
                                    }
                                </Form.Select>
                                <Button variant="primary" type="submit" onClick={onClickUpdateUserStatus}>
                                    Update
                                </Button>
                            </Form>
                                </Card.Text>
                            </Card>         
                        </Container>
                    </Col>
                </Row>
            </div>
            }
        </>
    )
}

export default Admin;