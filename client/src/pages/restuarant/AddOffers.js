import { Box } from '@mui/system';
import React from 'react';
import theme, { Colours } from '../../assets/theme/theme';

// when calling data, nned to take from there
import bgImage from '../../assets/images/offersbg.png';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import AddOfferForm from '../../components/restaurant/AddOfferForm';
import { Link } from 'react-router-dom';
// for scroll reveals
import Fade from 'react-reveal/Fade';
import Rlogo from '../../assets/images/restaurant-logo.jpg';
import { Avatar, IconButton, Typography } from '@mui/material';
import Navbar from './../../components/Navbar';

// ------------------for get user roles-----------------
var ROLE = null;

const AddOffers = () => {

    return (

        // ------main box----------------

        <Box sx={{
            margin: 0,
            padding: 0,
            width: "100%",
            height: "100vh",
            display: "flex",
            flexDirection: "column",
            background: `url(${bgImage})`,
            backgroundRepeat: "no-repeat",
            backgroundSize: "cover",
            justifyContent: "center",
            [theme.breakpoints.down('sm')]: {
                flexDirection: "column",
            },
        }}>

            <Fade top>
                <Navbar />
            </Fade>

            {/* ------------topic area--------------------- */}
            <Fade top cascade>
                <Box sx={{
                    display: "flex",
                    flexDirection: "row",
                    padding: "1rem",
                }}>
                    {/*------------------------------START SET USERTOLE-------------------------------------------------*/}
                    {(() => {
                        if (JSON.parse(localStorage.getItem('ROLE'))) {
                            ROLE = JSON.parse(localStorage.getItem('ROLE'))[0].authority;
                            console.log(ROLE)
                        }
                    }
                    )()}
                    {/*------------------------------END SET USERTOLE-------------------------------------------------*/}

                    {(() => {
                        if (ROLE === "restaurant") {
                            return (
                                <IconButton component={Link} to={"/Restaurantprofile"}>
                                    <ArrowBackIosIcon sx={{
                                        color: Colours.green, '&:hover': {
                                            color: Colours.yellow,
                                            [theme.breakpoints.down('sm')]: {
                                                height: "80%",
                                                marginTop: "30px",
                                            },
                                        },
                                        fontSize: "2rem",
                                    }} />
                                </IconButton>
                            );
                        }
                    }
                    )()}

                    <Avatar
                        alt="food image"
                        src={Rlogo}
                        sx={{
                            width: "10%",
                            height: "100%",
                            margin: "1rem",
                            border: "2px solid #EFEAEA",
                            [theme.breakpoints.down('sm')]: {
                                height: "80%",
                            },
                        }} />

                    <Typography variant='h4' sx={{
                        color: Colours.green,
                        marginTop: "4%",
                        [theme.breakpoints.down('sm')]: {
                            fontSize: '20px',
                            marginRight: "10%",
                            marginTop: "30px",
                        }
                    }}>
                        Add Seasonal Offers
                    </Typography>
                </Box>
            </Fade>
            {/* ------------end of topic area--------------- */}

            <Box scroll='paper' sx={{
                width: "90%",
                margin: "auto",
                height: "85vh",
                padding: "auto",
                overflow: "scroll",
                marginBottom: "2%",
                borderRadius: "0.8rem",
                [theme.breakpoints.down('sm')]: {
                    padding: 0,
                    width: "auto",
                    overflow: "auto",

                    hieght: "auto"
                },
            }}>
                <AddOfferForm />
            </Box>
        </Box >
    )
}

export default AddOffers
