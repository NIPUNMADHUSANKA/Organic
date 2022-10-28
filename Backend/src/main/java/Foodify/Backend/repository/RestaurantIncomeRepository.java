package Foodify.Backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import Foodify.Backend.model.RestaurantIncome;

@Repository
public interface RestaurantIncomeRepository extends MongoRepository<RestaurantIncome,String>{
    
    List<RestaurantIncome> findByuserName(String userName);

}
