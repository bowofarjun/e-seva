import React from "react";

const Footer=(props)=>{
    return (
        <>
            <div style={{background:"black",color:"white",height:"5vh",textAlign:"center",padding:"2px"}}>
                <span>
                    © {(new Date().getFullYear())} E-SEVA
                </span>
            </div>
        </>
    );
}

export default Footer;