package Foodify.Backend.controller;

import Foodify.Backend.model.FoodItem;
import Foodify.Backend.model.Offers;
import Foodify.Backend.model.Order;
import Foodify.Backend.model.OrderItem;
import Foodify.Backend.model.Restaurant;
import Foodify.Backend.repository.FoodItem_Repository;
import Foodify.Backend.repository.Order_Repository;
import Foodify.Backend.repository.RestaurantRepository;
//import Foodify.Backend.repository.Order_Repository;
import Foodify.Backend.service.Order_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrderController {

    @Autowired(required = false) // error debugged by false
    private Order_Service order_service;

    @Autowired
    private Order_Repository order_Repository;

    @Autowired
    private FoodItem_Repository foodItem_repository;

    @Autowired
    private RestaurantRepository resRepo;

    @GetMapping("/FoodiFy/Orders/pucheshistory")
    public List<Order> ordersByUser() {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Order> orders = order_Repository.findByuserName1(userName);

        for (Order order : orders) {

            //System.out.println(order.getId());
            String ID = order.getResId();


            Restaurant resturantname = resRepo.findByid(ID);


            order.setResName(resturantname.getRestaurantName());
            
            List<OrderItem> items1 = order.getItems();
            // System.out.println(items1);

            for (OrderItem item : items1) {
                // setting names of order items
                String foodId = item.getFoodId();
                
                // taking the food item and assigning the name
                try {
                    FoodItem foodItem1 = foodItem_repository.findByid(foodId);
                    item.setFoodName(foodItem1.getName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                //
            }
            order.setItems(items1);

        }

        return orders;
    }

    @GetMapping("/FoodiFy/Admin/Orders/All")
    public ResponseEntity<?> ordersAll(){
        try {

            ResponseEntity<List<Order>> detailed = new ResponseEntity<>(order_service.detailedOrders(), HttpStatus.OK);
            return detailed;

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    // ----------------------to make the order--------------------------------
    @PostMapping("/FoodiFy/User/setOrder")
    public ResponseEntity<?> getOrderFood(@RequestBody Order order) {

        // String foodId = null;
        // FoodItem food = foodItems.findByid(foodId);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        try {

            return new ResponseEntity<>(order_service.setOrder(order, userName), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        // return null;
    }

    // ----------------------to make the order--------------------------------
    @GetMapping("/FoodiFy/Restaurant/callOrder")
    public ResponseEntity<?> callOrders() {

        // String foodId = null;
        // FoodItem food = foodItems.findByid(foodId);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //System.out.println(userName);
        try {

            return new ResponseEntity<>(order_service.callOrder(userName), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        // return null;
    }

    //    ----------------------to make the order--------------------------------
    @PutMapping("/FoodiFy/Restaurant/updateOrderItem")
    public ResponseEntity<?> updateOrderItem(@RequestParam("itemId") String itemId,
                                             @RequestParam("orderId") String orderId) {

//		String foodId = null;
//		FoodItem food = foodItems.findByid(foodId);

        System.out.println(itemId+"Controller");
        System.out.println(orderId+"Controller");
        try {
            System.out.println("Controller");
            return new ResponseEntity<>(order_service.updateOrderItem(itemId,orderId), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

//		return null;
    }


}
