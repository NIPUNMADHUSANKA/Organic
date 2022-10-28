import React from 'react'
import { Box } from '@mui/material';
import Menubar from '../components/Menubar';
import Explore from '../components/explore/Explore.js';
import '../assets/css/Home.css';


const ExploreHome = () => {
    return (
        <Box className='body-Style' sx={{ margin:0}} >
            <Box >
                <Menubar/>
                <Explore/>
                    {/* <FoodCard/>
                    <Carousel /> */}
            </Box>
        </Box>
    )
}

export default ExploreHome
