import React, { useState, useEffect } from "react";
import { ThemeProvider, Box, Typography, Tab, Tabs } from '@mui/material';

import ExploreF from '../components/explore/Explore.js';
// import Tabs from '../components/explore/Tabs';
import FoodScroll from '../components/explore/FoodScroll';
import RestaurantScroll from '../components/explore/RestaurantScroll';
import TabPanel from '../components/TabPanel';

import theme from '../assets/theme/theme';
import AuthService from '../services/auth-service';
import axois from "axios";

// for scroll reveals
import Fade from 'react-reveal/Fade';
import Navbar from '../components/Navbar';


import '../assets/css/Home.css';

const Explore = () => {

    useEffect(() => {
        document.title = "Explore";
    })

    // ----------store restaurant values--------
    const [details, setDetails] = React.useState({});
    const [fooddetails, setfoodDetails] = React.useState({});


    const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    ///-- Get Token UserName--///
    const currentUser = AuthService.getCurrentUser();


    const [content, setContent] = useState("");

    useEffect((event) => {

        axois.get("http://localhost:8072/FoodiFy/Service/ShowRestaurant")
            .then(data => {
                // this part if sucess
                console.log(data);
                console.log(currentUser);

                const details = data.data;

                setDetails({ ...details});
            })
            .catch(error => {

            });

    }, []);

    useEffect((event) => {

        axois.get("http://localhost:8072/FoodiFy/Service/Showfoods")
            .then(data => {
                // this part if sucess
                console.log(data);
                console.log(currentUser);

                const fooddetails = data.data;

                setfoodDetails({ ...fooddetails});
            })
            .catch(error => {

            });

    }, []);



    return (
        <Box className='bg' sx={{ display: "flex", flexDirection: 'column', margin: 0 }}>

            <Typography sx={{ color: "#fff" }}>{content.userName}</Typography>

            <Fade top>
                <Navbar />
            </Fade>

            <ExploreF />




            <Box sx={{ display: "flex", flexDirection: 'row', alignItems: 'baseline', [theme.breakpoints.down('sm')]: { flexDirection: 'column-reverse', } }}>
                <ThemeProvider theme={theme}>
                    <Tabs
                        value={value}
                        onChange={handleChange}
                        textColor="primary"
                        indicatorColor="secondary"
                        sx={{
                            color: "primary",
                            mb: "1%",
                            ml: "3%",
                        }}
                    >
                        <Tab value={0} label={
                            <Typography color="primary" fontWeight="200" fontSize="18px">Food</Typography>
                        } />
                        <Tab value={1} label={
                            <Typography color="primary" fontWeight="200" fontSize="18px">Restaurant</Typography>
                        } />
                    </Tabs>
                  
                </ThemeProvider>
            </Box >
            <Box minHeight="50vh">
                <TabPanel value={value} index={0}>


                    <FoodScroll fooddetails={fooddetails} />

                </TabPanel>
                <TabPanel value={value} index={1}>

                    {/* {console.log(details)} */}
                    <RestaurantScroll details={details} />


                </TabPanel>
            </Box>
        </Box >

    )
}

export default Explore
