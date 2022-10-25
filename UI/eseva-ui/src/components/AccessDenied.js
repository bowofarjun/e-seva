import React from "react";
import { Card } from "react-bootstrap";

const AccessDenied =()=>{
    return (
        <>
            <Card style={{textAlign:"center"}}>
                <Card.Title>Access Denied</Card.Title>
                <Card.Text>
                    <p>You are not authorized to access this page.</p>
                    <p>Please login with a valid user to access the page.</p> 
                </Card.Text>
            </Card>
        </>
    )
}

export default AccessDenied;