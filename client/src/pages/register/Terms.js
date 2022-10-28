import React from 'react'
import { Box } from '@mui/material';
import TermsBanner from '../../components/Register/TermsBanner';
import '../../assets/css/Registeruser.css';
import Navbar from './../../components/Navbar';
import Fade from 'react-reveal/Fade';

const Terms = () => {
  return (
    <Box className='Register-body-Style' >
        <Fade top>
          <Navbar />
        </Fade>
        <TermsBanner />

    </Box>
    
  )
}

export default Terms