import React from 'react';
import { Box } from '@mui/material';
import '../assets/css/Profile.css';
import Pcarousel from '../components/profile/profileCarouselGood';
import Pnavbar from '../components/Navbar';

const ProfileD = () => {


  return (

    <Box className='Profile-body-Style' marginTop="0px" sx={{overflow:"visible"}}>
      <Pnavbar />

      <Pcarousel/>
     
    </Box>

    
  )
}

export default ProfileD
