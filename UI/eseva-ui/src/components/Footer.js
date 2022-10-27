import React from "react";

const Footer=(props)=>{
    return (
        <>
            <div id="footer" style={{background:"black",color:"white",height:"5vh",width:'100vw',textAlign:"center",padding:"2px"}}>
                <span>
                    © {(new Date().getFullYear())} E-SEVA
                </span>
            </div>
        </>
    );
}

export default Footer;