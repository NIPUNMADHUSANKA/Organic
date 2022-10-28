import React, { useState } from 'react';
import { useEffect } from 'react';
import { Box } from '@mui/material';


import PageTitle from '../components/User/PageTitle';
import CardBar from '../components/User/CardBar';
import ComplainAdd from '../components/User/ComplainAdd';
import ComplaintHistory from '../components/User/ComplaintHistory';

import Total from '../assets/icons/Total.png';//exporting the image for about section
import Pending from '../assets/icons/Pending.png';
import Accept from '../assets/icons/Accept.png';
import Reject from '../assets/icons/Reject.png';
import axois from "axios";

// for scroll reveals
import Fade from 'react-reveal/Fade';
import Navbar from '../components/Navbar';
import authHeader from '../services/auth-header';

const MainHeader = "Complaints";

function Complaints() {

  useEffect(() => {
    document.title = "Complaints";
})


  const [Datapending, setData] = useState([]);


  useEffect((event) => {

    axois.get(`http://localhost:8072/FoodiFy/User/getCount/pending`, {headers: authHeader()})
        .then(data => {
            // this part if sucess
            setData(data.data)
            
        })
        .catch(error => {
            console.log("There is an error");
        });

}, []);


const [Dataaccepted, setData1] = useState([]);

useEffect((event) => {

  axois.get(`http://localhost:8072/FoodiFy/User/getCount/accepted`, {headers: authHeader()})
      .then(data => {
          // this part if sucess
          setData1(data.data)
          
      })
      .catch(error => {
          console.log("There is an error");
      });

}, []);



const [Datarejected, setData2] = useState([]);

useEffect((event) => {

  axois.get(`http://localhost:8072/FoodiFy/User/getCount/rejected`, {headers: authHeader()})
      .then(data => {
          // this part if sucess
          setData2(data.data)
          
      })
      .catch(error => {
          console.log("There is an error");
      });

}, []);


const sum = Datapending + Dataaccepted + Datarejected;


  const data = [

    [ "Total Complaints",  sum, Total ],
  
    [ "Pending Complaints", Datapending, Pending ],
  
    [ "Accepted Complaints", Dataaccepted, Accept ],
  
    [ "Rejected Complaints",  Datarejected, Reject ]
  
  ];
   
  return (

    
    <Box className='complainbg'>

        <Fade top>
            <Navbar />
            <PageTitle MainHeader = {MainHeader}/>
        </Fade>
        
        <Fade left>
          <Box sx={{mt:"3%", mb:"2%"}}>
            <CardBar details = {data} />
          </Box>
        </Fade>

         

        <Fade right>
            <Box display="flex"
                 justifyContent="center"
                alignItems="center">
                <ComplainAdd />
            </Box>
        </Fade>

        <Fade left>
            <Box display="flex"
                 justifyContent="center"
                alignItems="center">
                <ComplaintHistory />    
            </Box>
        </Fade>
      
    </Box> 
  )
}

export default Complaints