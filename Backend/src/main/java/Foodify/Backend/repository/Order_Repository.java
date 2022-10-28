
package Foodify.Backend.repository;

import Foodify.Backend.model.Offers;
import Foodify.Backend.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Order_Repository extends MongoRepository<Order, String> {

	@Query(value ="{user: ObjectId(?0)}")
	List<Order> findByUser(String UserId);

//	@Query(value ="{restaurant: ?0}")
//	List<Order> findByRes(ObjectId RestaurantId);

//	Offers findByid(String id);

	List<Order> findByuserName1(String user);

	List<Order> findByresId(String restId);

	Order findByid(String orderId);

//	Optional<Order> findById(String restId);
}



