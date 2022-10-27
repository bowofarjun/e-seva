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
                <ServiceRequest serviceRequestId={item.serviceRequestId} 
                serviceName={item.serviceName}
                requestedBy={item.requestedBy}
                requestedFor={item.requestedFor}
                languageName={item.languageName}
                createdDate={item.createdDate}
                modifiedDate={item.modifiedDate}
                statusName={item.statusName}
                 />)
                }

            </div>
            }
        </>
    )
}

export default PreviousServiceRequests;