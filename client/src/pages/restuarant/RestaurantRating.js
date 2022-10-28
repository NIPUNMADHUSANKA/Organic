import { Box } from '@mui/system';
import React from 'react';
import theme, { Colours } from '../../assets/theme/theme';

import Background from '../../assets/images/pv4WkDi.webp';
import RestaurantRatingForm from '../../components/restaurant/RestaurantRatingForm';

// when calling data, nned to take from there
import RImage from '../../assets/images/restaurant-logo.jpg';
import { Avatar } from '@mui/material';

import Navbar from './../../components/Navbar';
import Fade from 'react-reveal/Fade';

const RestaurantRating = () => {
    return (

        // ------main box----------------

        <div>

            <Fade top>
                <Navbar />
            </Fade>

            <Box sx={{
                margin: 0,
                padding: 0,
                background: Colours.primary,
                width: "100%",
                display: "flex",
                flexDirection: "row",
                [theme.breakpoints.down('sm')]: {
                    flexDirection: "column-reverse",
                },
            }}>
                {/* --------------------form area-------------------- */}
                <Box sx={{
                    width: "70%",
                    display: "flex",
                    flexDirection: "column",
                    margin: "auto",
                    marginBottom: "2rem",
                    [theme.breakpoints.down('sm')]: {
                        width: "100%",
                    },
                }}>

                    {/* --------------beginin of the form area---------------- */}
                    <RestaurantRatingForm />
                </Box>
                {/* -----------------------end of form area----------------------- */}

                {/* -----------------------image area------------------- */}
                <Box sx={{
                    width: "30%",
                    background: `url(${Background})`,
                    borderRadius: "360px 0px 0px 360px",
                    border: "2px solid #EFEAEA",
                    [theme.breakpoints.down('sm')]: {
                        width: "98%",
                        margin: 0,
                        padding: 0,
                        height: "25vh",
                        borderRadius: "0px 0px 360px 360px",
                    },
                }}>
                    {/* ------------------for the food image--------------- */}
                    <Box sx={{
                        width: "25%",
                        height: "50%",
                        margin: "auto",
                        position: "absolute",
                        top: "25%",
                        right: "15%",
                        [theme.breakpoints.down('sm')]: {
                            width: "40%",
                            height: "18%",
                            top: "15%",
                            right: "30%",
                        },
                    }}>

                        <Avatar
                            alt="food image"
                            src={RImage}
                            sx={{
                                width: "100%",
                                height: "100%",
                                border: "2px solid #EFEAEA",
                                [theme.breakpoints.down('sm')]: {

                                },
                            }} />
                    </Box>

                </Box>
                {/* ---------------end of image area----------------------- */}

            </Box>
        </div>


    )
}

export default RestaurantRating
