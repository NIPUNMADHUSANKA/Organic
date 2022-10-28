import React from 'react';
import { useEffect } from 'react';
import { Box, Typography } from '@mui/material';

import PageTitle from '../components/User/PageTitle';
import authHeader from '../services/auth-header';

import Navbar from '../components/Navbar';
import Table from '../components/PurchaseHistory-user/table';
import axios from "axios";


const MainHeader = "Purchase History";

function IntakeChart() {
  const [details, setDetails] = React.useState({});

  useEffect((event) => {

    //to do:initialize the user id and pass to the url
    axios.get("http://localhost:8072/FoodiFy/Orders/pucheshistory",{ headers: authHeader()})
        .then(data => {
          // console.log(data);
          const details = data.data;
          setDetails({ ...details});
          // console.log(details);
        })
        .catch(error => {
          if (error.response.data) {
            console.log(error.response.data);
          }
        });

}, []);
  return (
    <Box>

      {/* <Fade top> */}
        <Navbar />
        <PageTitle MainHeader = {MainHeader}/>
        <Typography variant='normal' ml='4%' color='white' fontWeight='200'>Details about your previous orders. Complaining can be done only under 24h.</Typography>
        
        {/* <Grid container spacing={3}>
          <Grid item
              lg={8}
              md={12}
              xl={9}
              xs={12}
          > */}
            <Table data={details}/>
          {/* </Grid>
        </Grid> */}


      {/* </Fade> */}

    </Box>
  )
}

export default IntakeChart