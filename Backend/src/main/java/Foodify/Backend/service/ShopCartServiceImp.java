package Foodify.Backend.service;


import Foodify.Backend.model.*;
import Foodify.Backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopCartServiceImp implements ShopCartService{

    @Autowired
    private ShoppingCart_Repository ShoppingCartRepo;

    @Autowired
    private FoodItem_Repository foodItem_repository;

    @Autowired
    private RestaurantRepository restaurantrepo;

    @Autowired
    private FoodCategoryRepo FoodCategory;

    @Autowired
    private FoodMenuRepo FoodMenu;


//    ------------------for set up shopping cart---------------------------
    @Override
    public ResponseEntity<?> setShoppingCart(String userName, OrderItem orderItem,int price) {

        ShoppingCart shoppingCart = ShoppingCartRepo.findByuserName(userName);

        ShoppingCart cart;

//        -----------------newly send food item---------------------
        OrderItem foodItem = orderItem;
        int price2 = 0;
        //int price3 = 0;

        if(shoppingCart == null){
            cart = new ShoppingCart();

            List<OrderItem> items = new ArrayList<OrderItem>();
            items.add(foodItem);
            cart.setItems(items);
            price2 = price;

            cart.setPrice(price2);
            cart.setUserName(userName);

            ShoppingCartRepo.save(cart);

        }else{
            cart = ShoppingCartRepo.findByuserName(userName);
//            -----------------item list of current shopping cart--------------
            List<OrderItem> items = cart.getItems();
//          get the current price of the cart
            price2 = cart.getPrice();

            items.add(foodItem);

            price2 = price2+foodItem.getPrice();
            cart.setItems(items);
            cart.setPrice(price2);
            cart.setUserName(userName);

            ShoppingCartRepo.save(cart);

        }


        System.out.println("Success");


        return null;
    }

    //---------------------to get the shop cart item list----------------------------
    @Override
    public Map setCartitems(String userName) {

        ShoppingCart shoppingCart = ShoppingCartRepo.findByuserName(userName);
//        assigning item list
        List<OrderItem> orderItems = shoppingCart.getItems();
        List<FoodItem> foodItemList = new ArrayList<FoodItem>();
        List<Integer> quantityList = new ArrayList<Integer>();


        for (OrderItem item : orderItems){

            String foodId = item.getFoodId();
            FoodItem foodItems = foodItem_repository.findByid(foodId);
            foodItemList.add(foodItems);

            int quantity = item.getQuantity();
            quantityList.add(quantity);
        }

        int price = shoppingCart.getPrice();

        Map mapFinal = new HashMap();
        mapFinal.put("foodItems",foodItemList);
        mapFinal.put("quantityList",quantityList);
        mapFinal.put("price",price);


        return mapFinal;
    }

//    --------------------update the shopping cart---------------------------------------
    @Override
    public Map deleteCartItem(String userName, int index1) {

//        old shopping cart
        ShoppingCart shoppingCart = ShoppingCartRepo.findByuserName(userName);
//        assigning item list
        List<OrderItem> orderItems = shoppingCart.getItems();

//        lists for assign items
        List<FoodItem> foodItemList = new ArrayList<FoodItem>();
        List<Integer> quantityList = new ArrayList<Integer>();

        int price1 = orderItems.get(index1).getPrice();
//        -------remove selected item--------------
        int num = index1;
        orderItems.remove(num);
        int price = shoppingCart.getPrice() - price1;

        shoppingCart.setItems(orderItems);
        shoppingCart.setPrice(price);
        ShoppingCartRepo.save(shoppingCart);

//        System.out.println(price1);

//        -------------updated shop cart--------------
        ShoppingCart shoppingCart1 = ShoppingCartRepo.findByuserName(userName);
//        assigning item list
        List<OrderItem> orderItems1 = shoppingCart1.getItems();

        for (OrderItem item : orderItems1){

            String foodId = item.getFoodId();
            FoodItem foodItems = foodItem_repository.findByid(foodId);
            foodItemList.add(foodItems);

            int quantity = item.getQuantity();
            quantityList.add(quantity);
        }

        int price2 = shoppingCart1.getPrice();
        Map mapFinal = new HashMap();
        mapFinal.put("foodItems",foodItemList);
        mapFinal.put("quantityList",quantityList);
        mapFinal.put("price",price2);


        return mapFinal;
    }
//----------------------------------------final checkout----------------------------------------------
    @Override
    public Map finalCheckout(String userName) {

        ShoppingCart shoppingCart = ShoppingCartRepo.findByuserName(userName);
//        assigning item list
        List<OrderItem> orderItems = shoppingCart.getItems();
        List<FoodItem> foodItemList = new ArrayList<FoodItem>();
        List<Integer> quantityList = new ArrayList<Integer>();
        List<String> restaurantDetails = new ArrayList<String>();

        String CatId = null;
        String Rid = null;

        for (OrderItem item : orderItems){

            //        to get the restaurant id
            Rid = item.getRestaurantId();

            String foodId = item.getFoodId();
            FoodItem foodItems = foodItem_repository.findByid(foodId);
            foodItemList.add(foodItems);

            int quantity = item.getQuantity();
            quantityList.add(quantity);
        }
//----------------need to check general offers and set the new price--------------

//--------------------------------------------------------------------------------
        int price = shoppingCart.getPrice();
//---------------setting restaurant details----------------------------
//        System.out.println(Rid);

        Restaurant restaurant1 = restaurantrepo.findByid(Rid);
        restaurantDetails.add(restaurant1.getRestaurantName());
        restaurantDetails.add(restaurant1.getAddress());
        restaurantDetails.add(restaurant1.getLocation());
//        System.out.println(restaurant1.getRestaurantName());

        Map mapFinal = new HashMap();
        mapFinal.put("foodItems",foodItemList);
        mapFinal.put("quantityList",quantityList);
        mapFinal.put("price",price);
        mapFinal.put("restaurantDetails",restaurantDetails);

        return mapFinal;
    }
}
