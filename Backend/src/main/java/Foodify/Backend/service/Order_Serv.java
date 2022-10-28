package Foodify.Backend.service;
import Foodify.Backend.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface Order_Serv {

	List<Order> findByUser(String UserId);
	List<Order> findByRes(String RestaurantId);

	List<Order> getDetailedOrders(String UserId);

	List<Order> getDetailedOrders();

	public String setOrder(Order order,String userName);

	List<Order> callOrder(String userName1);

	String updateOrderItem(String itemId,String orderId);

}
