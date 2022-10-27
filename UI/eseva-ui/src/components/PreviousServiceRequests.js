import React, { useEffect, useState } from "react";
import AccessDenied from "./AccessDenied";
import ServiceRequest from "./ServiceRequest";
import axios from "axios";

const PreviousServiceRequests =()=>{

    const [previousServiceRequestData,setPreviousServiceRequestData]=useState([]);

    useEffect(()=>{
        loadPreviousServiceRequests();
    },[]);

    const loadPreviousServiceRequests =()=>{
        let url ='/api/service-request';

        let headers = {
            'Content-Type': 'application/json'
        };

        axios.get(url, {headers: headers}).then(
            (response)=>{
                console.log(response.data)
                setPreviousServiceRequestData(response.data);
            },
            (error)=>{
                console.log(error)
            }
        );

    }

    return (
        <>
            {localStorage.getItem('roleName')===null? <AccessDenied/>: 
            <div style={{textAlign:"center"}}>
                <h2 style={{marginBottom:'2rem'}}>Service Request History</h2>

                {previousServiceRequestData.map((item)=>
                <ServiceRequest props={item} />)
                }
                
            </div>
            }
        </>
    )
}

export default PreviousServiceRequests;