import React, { useState } from 'react';
import { useEffect } from 'react';
import { Box } from '@mui/material';
import theme, { Colours } from '../assets/theme/theme'; //to use theme provider,need to import this
import '../assets/css/Profile.css';
import navbar from '../components/Navbar';
import UserOrderBanner from '../components/user_order/user_order_upper';
import UserOrderForm from '../components/user_order/user_order_form';
import Backdrop from '@mui/material/Backdrop';
import CircularProgress from '@mui/material/CircularProgress';

// for scroll reveals
import Fade from 'react-reveal/Fade';
import Navbar from '../components/Navbar';
import axios from 'axios';
import authHeader from "../services/auth-header";


const UserOrder = () => {

  // -------------------------for the backdrop------------------------------
  const [open, setOpen] = React.useState(false);
  const handleClose = () => {
    setOpen(false);
  };
  const handleToggle = () => {
    setOpen(!open);
  };

  // ----------------------for store food response data----------------------
  const [details1, setDetails1] = React.useState({});

  // ----------------------for store quantity response data----------------------
  const [details2, setDetails2] = React.useState({});

  // ----------------------for store price response data----------------------
  const [price, setPrice] = React.useState(0);

  // ----------------------for store restaurant response data----------------------
  const [details3, setDetails3] = React.useState({});

  useEffect(() => {


    handleToggle()

    // -----------------------------------to getting food item details------------------------------------------
    const getShoppingCart = async () => {
      try {
        const respOffer = await axios.get(`http://localhost:8072/FooddiFy/Service/finalCheckout`, { headers: authHeader() });

        const details = respOffer.data;
        const foodItems = details.foodItems;
        const quantityList = details.quantityList;
        const restaurant = details.restaurantDetails;
        const price = details.price;

        console.log(details);
        console.log(foodItems);
        console.log(quantityList);
        console.log(restaurant);

        setDetails1([...foodItems]);
        setDetails2([...quantityList]);
        setDetails3([...restaurant]);
        setPrice(price);

        handleClose();

      } catch (err) {
        // Handle Error Here
        console.error(err);
      }
    };

    getShoppingCart();

    // --------calling items for cart---------------
  }, []);

  console.log(details1.length)
  return (
    <Box marginTop="0px">

      <Fade top>
        <Navbar />
      </Fade>

      {(() => {
        if (details1) {
          return (
            handleToggle
          );
        }
      }
      )()}
      {/* ----------------back drop------------------- */}
      <Backdrop
        sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={open}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
      {/* -------------------back drop---------------------- */}
      <Box className='order-body-Style'>
        <UserOrderBanner details3={details3}/>
      </Box>
      <UserOrderForm details1={details1} details2={details2} details3={details3} price={price}/>


    </Box>


  )
}

export default UserOrder
