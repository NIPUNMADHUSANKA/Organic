import React from 'react';
import { Box } from '@mui/material';

import FoodBanner from '../components/FoodufyBanner';
import AboutUs from '../components/AboutUs2';
import ContactUs from '../components/ContactUs';

import FOODIFY_ABOUTUS from '../assets/images/golden-cutlery.jpg';

// for scroll reveals
import Navbar from '../components/Navbar';
import Fade from 'react-reveal/Fade';

const details = {
  "detail1": "Most of the people do not have a proper understanding about the nutritional value of what they eat. ",
  "detail2": "Today, the consumption of unhealthy foods such as fast food, snacks and alcohol has led to an increase in the prevalence of chronic diseases such as diabetes and high cholesterol.",
  "detail3": "Foodify - assist people in locating their local favorite dishes and suggest a variety of others based on their preferences and diet plan.",
}

function Landing() {
  return (

    <div className='landingbg'>

      <Fade top>

        <Navbar />
        <FoodBanner />

      </Fade>


      <Fade right>
        <Box>
          <ContactUs />
        </Box>
      </Fade>



    </div>

  )
}

export default Landing