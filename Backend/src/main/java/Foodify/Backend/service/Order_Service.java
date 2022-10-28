package Foodify.Backend.service;

import Foodify.Backend.model.*;
import Foodify.Backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;



@Service
public class Order_Service implements Order_Serv{

	@Autowired
	private Order_Repository order_repository;

	@Autowired
	private FoodItem_Repository foodItem_repository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private Registered_Customer_Repository registered_customer_repository;

	@Autowired
	private ShoppingCart_Repository ShoppingCartRepo;

	@Override
	public List<Order> findByUser(String UserId){
		return order_repository.findByUser(UserId);
	}

	@Override
	public List<Order> findByRes(String RestaurantId){
		return order_repository.findByUser(RestaurantId);
	}
	@Override
	public List<Order> getDetailedOrders(String userId){
		List<Order> undetailedOrders = order_repository.findByUser(userId);

		for (Order order: undetailedOrders) {
			ArrayList details = order.getDetails();
			double price = 0;
			Restaurant res = restaurantRepository.findByid(order.getResId());
			order.setResId(res.getRestaurantName());

			for (int i = 0; i < details.size(); i++){
				LinkedHashMap x = (LinkedHashMap) details.get(i);
				FoodItem item = foodItem_repository.findByid(x.get("item").toString());
				x.put("name",item.getName());
				x.put("price",item.getPrice());
				x.put("discount",item.getDiscount());
				price += (((double)x.get("price")) * ((int)x.get("quantity"))) - (int)x.get("discount");
			}
			order.setPrice((float) price);
		}

		return undetailedOrders;
	}

	@Override
	public List<Order> getDetailedOrders(){
		List<Order> undetailedOrders = order_repository.findAll();
		for (Order order: undetailedOrders) {
			ArrayList details = order.getDetails();
			double price = 0;
			Restaurant res = restaurantRepository.findByid(order.getResId());
			order.setResId(res.getRestaurantName());
			Optional<Registered_Customer> user = registered_customer_repository.findById(order.getUser());
			order.setUser(user.get().getuserName());
			for (int i = 0; i < details.size(); i++){
				LinkedHashMap x = (LinkedHashMap) details.get(i);
				FoodItem item = foodItem_repository.findByid(x.get("item").toString());
				x.put("name",item.getName());
				x.put("price",item.getPrice());
				x.put("discount",item.getDiscount());
				x.put("res", user);
				price += (((double)x.get("price")) * ((int)x.get("quantity"))) - (int)x.get("discount");
			}
			order.setPrice((float) price);
		}
		return undetailedOrders;
	}

	//	------------------------detailed order set------------------------------------
	public List<Order> detailedOrders(){

//		getting the order list
		List<Order> orders = order_repository.findAll();

		List<OrderItem> items = new ArrayList<>();

		int amount = 0;

//		loop orders
		for (Order order : orders){

//			System.out.println(order.getResId()+"second");
			List<OrderItem> items1 = order.getItems();
			//get res name
			String resId = order.getResId();
			Restaurant restaurant = restaurantRepository.findByid(resId);
			order.setResId(restaurant.getRestaurantName());

//			looping items
			for (OrderItem item : items1){

//				setting names of order items
				String foodId = item.getFoodId();
//				System.out.println("FoodId :"+ foodId);

//				taking the food item and assigning the name
				if(foodId != null){
					FoodItem foodItem1 = foodItem_repository.findByid(foodId);
					item.setFoodName(foodItem1.getName());
					item.setPrice(foodItem1.getPrice());
					item.setDiscount(foodItem1.getDiscount());
					item.setTotal(Math.round(item.getQuantity()* item.getPrice()- item.getDiscount()));
					amount += item.getTotal();
//					item.setFoodName(foodItem1.getName());
				}
//
			}

//			setting updated order items
			order.setItems(items1);
			order.setPrice(amount);
		}
		return orders;
	}

//	------------------------setting up order------------------------------------
	@Override
	public String setOrder(Order order, String userName) {

//		-----------------get the shopping cart and get the items----------------------
		ShoppingCart shoppingCart = ShoppingCartRepo.findByuserName(userName);
		List<OrderItem> items = shoppingCart.getItems();

		List<OrderItem> foodItmes = new ArrayList<>();
		String restaurantId = null;

		System.out.println(order.getOrderDate());
		System.out.println(order.getOrderTime());

//		----------setting up RID----------------
		for(OrderItem item : items){
			restaurantId = item.getRestaurantId();
			item.setPreparedStatus("Queued");
		}

//		--------setting up order details-----------------
		order.setItems(items);
		order.setResId(restaurantId);
		order.setPrice(shoppingCart.getPrice());
		order.setUserName1(userName);
		order.setPreparedState("Queued");

		order_repository.save(order);
		items.clear();
		shoppingCart.setItems(items);
		shoppingCart.setPrice(0);
		ShoppingCartRepo.save(shoppingCart);

//		offers.setStartDate(LocalDate.parse(Bdate));
//		offers.setEndDate(LocalDate.parse(Edate));
		return null;
	}

//	----------------------------to call relevant orders of the restaurant--------------------------
	@Override
	public List<Order> callOrder(String userName1) {

		Restaurant restaurant = restaurantRepository.findByuserName(userName1);
//		System.out.println(restaurant.getId());
//		getting the order list
		List<Order> orders = order_repository.findByresId(restaurant.getId());

		List<OrderItem> items = new ArrayList<>();

//		loop orders
		for (Order order : orders){
//			System.out.println(order.getResId()+"second");
			List<OrderItem> items1 = order.getItems();

//			looping items
			for (OrderItem item : items1){

//				setting names of order items
				String foodId = item.getFoodId();

//				taking the food item and assigning the name
				FoodItem foodItem1 = foodItem_repository.findByid(foodId);
				item.setFoodName(foodItem1.getName());
				item.setImage(foodItem1.getImage());
//				System.out.println(item.getFoodName());
			}

//			setting updated order items
			order.setItems(items1);
		}
		return orders;
	}

	@Override
	public String updateOrderItem(String itemId, String orderId) {

		System.out.println("order service");
		System.out.println(orderId);

		Order orders = order_repository.findByid(orderId);

		System.out.println(orders);
		List<OrderItem> items = orders.getItems();

//		for(Order order : orders)

		int count = 0;
		for(OrderItem item : items){
			System.out.println(item.getFoodId()+"order service");
			System.out.println(itemId+"order service");

			if(Objects.equals(item.getFoodId(), itemId)){

				if(Objects.equals(item.getPreparedStatus(), "Queued")){item.setPreparedStatus("Preparing");}
				else if(Objects.equals(item.getPreparedStatus(), "Preparing")){item.setPreparedStatus("Finished");}
			}

			if(Objects.equals(item.getPreparedStatus(), "Preparing")){

				System.out.println("order service");
				orders.setPreparedState("Preparing");
			}
			else if(Objects.equals(item.getPreparedStatus(), "Finished")){

				System.out.println("order service");
				count++;
				if(count == items.size()){
					orders.setPreparedState("Finished");
				}

			}
		}

		orders.setItems(items);
		order_repository.save(orders);
		return null;
	}
}
