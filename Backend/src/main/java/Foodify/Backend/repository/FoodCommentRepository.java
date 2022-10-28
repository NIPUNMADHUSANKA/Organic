package Foodify.Backend.repository;

import Foodify.Backend.model.RestaurantComments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Foodify.Backend.model.FoodComments;

import java.util.List;

@Repository
public interface FoodCommentRepository extends MongoRepository<FoodComments,String> {
    FoodComments findByid(String id);

    List<FoodComments> findByfoodId(String foodId);

    List<FoodComments> findByuserName(String userName);
}