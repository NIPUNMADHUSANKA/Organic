import React from 'react'
import { Box } from '@mui/material';
import RegisterBanner1 from '../../components/Register/RegisterBanner1';
import '../../assets/css/Registeruser.css';
import Navbar from './../../components/Navbar';
import Fade from 'react-reveal/Fade';


const UserPackage = () => {
  return (
    <Box className='Registeruserpackage-body-Style' >
      
        <Fade top>
          <Navbar />
        </Fade>

        <RegisterBanner1 />

    </Box>
    
  )
}

export default UserPackage