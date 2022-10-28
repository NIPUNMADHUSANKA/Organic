import React from 'react';
import { Box } from '@mui/material';
import '../assets/css/Profile.css';
import AddressFormW from '../components/profile/AddressForm';
import '../assets/css/Profile.css';

// for scroll reveals
import Fade from 'react-reveal/Fade';
import Navbar from '../components/Navbar';

const Profile = () => {


  return (
    <Box className='Profile2-body-Style' marginTop="0px">
      
      <Fade top>
            <Navbar />
      </Fade>

      <AddressFormW/>



    </Box>

    
  )
}

export default Profile
