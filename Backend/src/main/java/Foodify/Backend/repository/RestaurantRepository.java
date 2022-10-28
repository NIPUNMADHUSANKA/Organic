package Foodify.Backend.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Foodify.Backend.model.Restaurant;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String>{
	
//	@Query(sort= "{about:-1}")
//	public List<Restaurant> findlast();
	
	Restaurant findByuserName(String userName);
	Restaurant findByid(String id);

}
