import React from 'react';
import { useEffect } from 'react';
import { Box, Tab, Tabs } from '@mui/material';

// to import necessacry components of the restuarant page
import RestaurantHome from '../../components/restaurant/profile/RestaurantHome';
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import DashboardIcon from '@mui/icons-material/Dashboard';
import HomeIcon from '@mui/icons-material/Home';
import RTabPanel from '../../components/restaurant/profile/RTabPannel';

// for scroll reveals
import Fade from 'react-reveal/Fade';
import { Colours } from '../../assets/theme/theme';
import styled from '@emotion/styled';
import RestaurantEditableBanner from '../../components/restaurant/profile/RestaurantEditableBanner';
import RestaurantDashboard from '../../components/restaurant/profile/Dashboard';
import RestaurantIncome from '../../components/restaurant/profile/Income';


import Navbar from './../../components/Navbar';

// styles for profile tabs----------
const mIcons = {
    color: Colours.yellow,
}

// ---------------------------------text fied css style-----------------------
const CustomTabs = styled(Tabs)({
    '& button': {
        color: Colours.yellow,
    },
    '& button:hover': {
        color: Colours.green,
        '& svg': {
            color: Colours.green,
        },
    },
    '& button:active': {
        color: Colours.grayWhite,
        '& svg': {
            color: Colours.grayWhite,
        },
    },
    '& button.Mui-selected': {
        color: Colours.green,
        '& svg': {
            color: Colours.green,
        },
    }

});
// ---------------------------------------------------------------------


const RestaurantProfile = () => {

    const [value, setValue] = React.useState(0);

    const changing = (event, newValue) => {
        setValue(newValue);
    };

    useEffect(() => {
        document.title = "Restaurant Profile";
    })

    return (
        <Box>

            <Fade top>
                <Navbar />
            </Fade>

            <Fade>
                <RestaurantEditableBanner />
            </Fade>

            {/* -------------------tabs of the proffile------------------ */}
           

            <Box>
                <RTabPanel value={value} index={0} >
                    <RestaurantHome />
                </RTabPanel>
                
            </Box>

        </Box>
    )
}

export default RestaurantProfile
