import React from 'react';
import { useEffect } from 'react';
import Carousel from 'react-elastic-carousel';

import { Box } from '@mui/material';

import PageTitle from '../components/User/PageTitle';
import Nutrition_Card from '../components/User/Nutrition_Card';
import Nutrition_Card_Summary from '../components/User/Nutrition_Card_Summary';

// for scroll reveals
import Fade from 'react-reveal/Fade';
import Navbar from '../components/Navbar';

const MainHeader = "Nutritions";

function Nutrition_Summary() {

    useEffect(() => {
        document.title = "Nutritions";
    })

  return (
    <Box className='image_upload_body'>
      
      <Fade top>
        <Navbar />
        <PageTitle MainHeader = {MainHeader}/>
      </Fade>
        
        <Carousel pagination={false} disableArrowsOnEnd={true} >
            <Nutrition_Card />
            <Nutrition_Card_Summary />
        </Carousel>


    </Box>
  )
}

export default Nutrition_Summary