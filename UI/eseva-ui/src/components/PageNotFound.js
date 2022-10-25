import React from "react";
import { Card } from "react-bootstrap";

const PageNotFound=()=>{
    <>
        <Card style={{textAlign:"center"}}>
            <Card.Title>Page Not Found</Card.Title>
            <Card.Text>
                <p>Oops. Seems like you typed in a wrong url.</p>
                <p>Please check the url you typed</p> 
            </Card.Text>
        </Card>
    </>
}

export default PageNotFound;