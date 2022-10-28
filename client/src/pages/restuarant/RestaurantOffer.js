import { Box } from '@mui/system';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
// import authHeader from "../../services/auth-header";
import theme, { Colours } from '../../assets/theme/theme';

import Background from '../../assets/images/pv4WkDi.webp';

// when calling data, nned to take from there
import Offer from '../../assets/images/offer5.jpg';
import bgImage from '../../assets/images/offersbg.png';
import Rlogo from '../../assets/images/restaurant-logo.jpg';
import { Avatar, Button, IconButton, List, ListItem, ListItemText, Typography } from '@mui/material';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import { Link } from 'react-router-dom';
import { useLocation } from 'react-router-dom';

// for scroll reveals
import Fade from 'react-reveal/Fade';

// ----------------this is tem until data call--------
const details = {
    "detail": "Have a celebrity bartend from 4-8pm, and give all proceeds from pizzas sold during that time to your local food bank.Halloween costume contests featuring giveaways or gifts, a different offering for each of the twelve days of Christmas, a Mother’s Day trivia event",
}

var ROLE = null;

const RestaurantOffer = () => {


    {/*------------------------------START SET USERTOLE-------------------------------------------------*/ }
    {
        (() => {
            if (JSON.parse(localStorage.getItem('ROLE'))) {
                ROLE = JSON.parse(localStorage.getItem('ROLE'))[0].authority;
                // console.log(ROLE)
            }
        }
        )()
    }
    {/*------------------------------END SET USERTOLE-------------------------------------------------*/ }

    const [details, setDetails] = useState({});

    // ---------to set food items--------------------
    const [details2, setDetails2] = useState({});

    // --------------to get the id------------------
    const location = useLocation();

    const [itemData, setItemData] = useState(null);

    var offer1 = null;

    const image1 = details.tempImage;

    // -----------------------to set items--------------------------------
    const [items, setItems] = React.useState([]);

    // -----------------adding items to array--------------------
    const itemlisthandle = (item) => {

        var items = item;
        // console.log(items);

        const sendGetRequest2 = async () => {
            try {

                const itemData = new FormData();
                itemData.append('items', items);
                setItemData(itemData);

                const resp2 = await axios.post(`http://localhost:8072/FoodiFy/Service/getOfferItems`, itemData);

                const details2 = resp2.data;
                setDetails2({ ...details2 });


            } catch (err) {
                // Handle Error Here
                console.error(err);
            }
        };

        sendGetRequest2();

    };

    // console.log(details2);
    // ---------------------------getting customer view-----------------------------------
    useEffect((event) => {

        // ---------------------for the restaurant view-------------------------------
        const sendGetRequest = async () => {
            try {
                const resp = await axios.get(`http://localhost:8072/FoodiFy/Service/getOffer/${location.state.id}`);

                const details = resp.data;

                const items1 = resp.data.items;
                itemlisthandle(items1)

                setDetails({ ...details });
                // console.log(details);
                // setItems([...items1]);

                if (image1 !== null) {
                    offer1 = `data:image/jpeg;base64,${image1}`;
                } else {
                    offer1 = Offer;
                }
            } catch (err) {
                // Handle Error Here
                console.error(err);
            }
        };

        sendGetRequest();

        // console.log(items);

    }, []);

    var ROLE = null;

    {/*------------------------------START SET USERTOLE-------------------------------------------------*/ }
    {
        (() => {
            if (JSON.parse(localStorage.getItem('ROLE'))) {
                ROLE = JSON.parse(localStorage.getItem('ROLE'))[0].authority;
                // console.log(ROLE)
            }
        }
        )()
    }
    {/*------------------------------END SET USERTOLE-------------------------------------------------*/ }

    // -------------------------------------getting the end date and convert into date format----------------------------
    var todayDate = new Date(); //Today Date    
    const Edate1 = details.endDate;
    const eDate1 = new Date(Edate1);

    // console.log(date.toString());
// 
    return (

        // ------main box----------------

        <Box sx={{
            margin: 0,
            padding: 0,
            width: "100%",
            height: "100vh",
            display: "flex",
            flexDirection: "row",
            background: `url(${bgImage})`,
            backgroundRepeat: "no-repeat",
            backgroundSize: "cover",
            [theme.breakpoints.down('sm')]: {
                flexDirection: "column-reverse",
            },
        }}>
            {/* --------------------description area-------------------- */}
            <Box sx={{
                width: "70%",
                display: "flex",
                flexDirection: "column",
                margin: "auto",
                // marginBottom: "2rem",
                [theme.breakpoints.down('sm')]: {
                    width: "100%",
                },
            }}>

                {/* --------------beginin of the description area---------------- */}
                <Box sx={{
                    display: "flex",
                    flexDirection: "column",
                }}>
                    {/* ------------topic area--------------------- */}
                    <Fade top cascade>
                        <Box sx={{
                            display: "flex",
                            flexDirection: "row",
                            padding: "1rem",
                        }}>
                            {(() => {
                                if (ROLE !== "restaurant") {
                                    return (
                                        <IconButton component={Link} to={"/Restaurant"}>
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
                            {(() => {
                                if (ROLE === "restaurant") {
                                    return (
                                        <IconButton component={Link} to={"/RestaurantProfile"}>
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
                                Seasonal Offers
                            </Typography>
                        </Box>
                    </Fade>
                    {/* ------------end of topic area--------------- */}

                    {/* -------------description area--------------- */}
                    <Fade left cascade>
                        <Typography sx={{
                            color: Colours.green,
                            marginLeft: "2rem",
                        }}>
                            Description
                        </Typography>

                        <Typography variant="body1" gutterBottom sx={{
                            color: Colours.grayWhite,
                            justifyContent: 'center',
                            textAlign: 'center',
                            fontSize: '1rem',
                            padding: '1%',
                            width: "80%",
                            margin: "auto",
                            marginRight: "15%",
                            transition: 'transform .2s', '&:hover': {
                                transform: 'scale(1.04)',
                                opacity: 4,
                            },
                            [theme.breakpoints.down('sm')]: {
                                fontSize: '14px',
                                marginRight: "10%",
                            }
                        }}>
                            {details.description}
                            <br />
                            <br />
                            Food Items

                            {/* ---------------showing the food items--------------------- */}
                            <List dense={true} sx={{
                                justifyContent: 'center',
                                textAlign: 'center',
                                margin: 'auto',
                                width: '20%',
                                marginLeft: '42%',
                                textAlign: 'center',
                                [theme.breakpoints.down('sm')]: {
                                    fontSize: '14px',
                                    marginLeft: '28%',
                                    width: '50%',
                                }
                            }}>
                                {Object.keys(details2).map((keyName) => (
                                    // console.log(details2[keyName]),
                                    <ListItem key={details2[keyName].id} >
                                        <ListItemText sx={{ color: Colours.green, }}
                                            primary={details2[keyName]}
                                        />
                                    </ListItem>
                                ))}
                            </List>
                            <br />
                            {(() => {
                                if (todayDate < eDate1) {
                                    return (
                                        <b>From:{details.startDate} to:{details.endDate}</b>
                                    );
                                }
                            }
                            )()}
                            {(() => {
                            if (todayDate > eDate1) {
                                return (
                                    <Typography variant="body1" gutterBottom sx={{
                                        color: '#ff0000',
                                        justifyContent: 'center',
                                        textAlign: 'center',
                                        fontSize: '1rem',
                                        padding: '1%',
                                        width: "80%",
                                        margin: "auto",
                                        transition: 'transform .2s', '&:hover': {
                                            transform: 'scale(1.04)',
                                            opacity: 4,
                                        },
                                        [theme.breakpoints.down('sm')]: {
                                            fontSize: '14px',
                                            marginRight: "10%",
                                        }
                                    }}>Expired</Typography>
                                );
                            }
                        }
                        )()}
                        </Typography>


                    </Fade>

                </Box>
                {/* --------------end of the description area---------------- */}

                {/* ---------------------edit and delete area---------------------- */}
                {(() => {
                    if (ROLE === "restaurant") {
                        return (
                            <Box sx={{
                                width: '80%',
                                margin: 'auto',
                                display: 'flex',
                                flexDirection: 'row'
                            }}>
                                <Button size="small" component={Link} to={"/RestaurantProfile/Offer/UpdateOffer"} state={{id:details.id}} sx={{
                                    margin: '6px',
                                    background: Colours.green, '&:hover': {
                                        backgroundColor: Colours.yellow,
                                    },
                                    color: Colours.dark,
                                    fontSize: '0.8rem',
                                    [theme.breakpoints.down('sm')]: {
                                        fontSize: '8px',
                                        padding: '2px',
                                    },
                                }}>
                                    Edit
                                </Button>

                                <Button size="small" sx={{
                                    margin: '6px',
                                    background: Colours.darkgray, '&:hover': {
                                        backgroundColor: Colours.cardBlack,
                                    },
                                    color: Colours.grayWhite,
                                    fontSize: '0.8rem',
                                    [theme.breakpoints.down('sm')]: {
                                        fontSize: '8px',
                                        padding: '2px',
                                    },
                                }}>
                                    Delete
                                </Button>
                            </Box>
                        );
                    }
                }
                )()}

            </Box>
            {/* -----------------------end of description area----------------------- */}

            {/* -----------------------image area------------------- */}
            <Box sx={{
                width: "30%",
                background: `url(${Background})`,
                borderRadius: "360px 0px 0px 360px",
                border: "2px solid #EFEAEA",
                opacity: 0.8,
                [theme.breakpoints.down('sm')]: {
                    width: "98%",
                    margin: 0,
                    padding: 0,
                    height: "25vh",
                    borderRadius: "0px 0px 360px 360px",
                },
            }}>
                {/* ------------------for the offer image--------------- */}
                <Box sx={{
                    width: "25%",
                    height: "50%",
                    margin: "auto",
                    position: "absolute",
                    top: "25%",
                    right: "15%",
                    background: `url(${image1 !== null ? `data:image/jpeg;base64,${image1}` : Offer})`,
                    backgroundRepeat: "no-repeat",
                    backgroundSize: "cover",
                    opacity: 4,
                    transition: 'transform .2s', '&:hover': {
                        transform: 'scale(1.04)',
                        opacity: 4,
                    },
                    [theme.breakpoints.down('sm')]: {
                        width: "40%",
                        height: "18%",
                        top: "15%",
                        right: "30%",
                    },
                }}>

                </Box>

            </Box>
            {/* ---------------end of image area----------------------- */}

        </Box >
    )
}

export default RestaurantOffer
