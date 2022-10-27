import React from "react";
import Header from "./Header";
import {
    BrowserRouter,
    Route, Routes
  } from "react-router-dom";
import Home from "./Home";
import Footer from "./Footer";
import About from "./About";
import Services from "./Services";
import Contact from "./Contact";
import LoginRegister from "./LoginRegister";
import PreviousServiceRequests from "./PreviousServiceRequests";
import PageNotFound from "./PageNotFound";
import NewServiceRequest from "./NewServiceRequest";
import Admin from "./Admin";

const Main = ()=>{

    return (
        <>
        <BrowserRouter>
            <div><Header/></div>
            
            <div style={{height:'90vh'}}>
                    <Routes>
                        <Route path="/" element={<Home/>} exact />
                        <Route path="/home" element={<Home/>} exact />
                        <Route path="/about" element={<About/>} exact />
                        <Route path="/services" element={<Services/>} exact />
                        <Route path="/contact" element={<Contact/>} exact />
                        <Route path="/login-or-register" element={<LoginRegister/>} exact />
                        <Route path="/new-service-request" element={<NewServiceRequest/>} exact />
                        <Route path="/previous-service-requests" element={<PreviousServiceRequests/>} exact/>
                        <Route path="/admin" element={<Admin/>} exact />
                        <Route path="*" element={<PageNotFound/>} />
                    </Routes>
                
            </div>
            
            <div> <Footer/></div>
            </BrowserRouter>
        </>
    );
}

export default Main;