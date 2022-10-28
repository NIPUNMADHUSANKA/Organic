package Foodify.Backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Foodify.Backend.model.IntakePendingItem;

@Repository
public interface IntakeItemPending extends MongoRepository<IntakePendingItem, String>{
    
    List <IntakePendingItem> findBypending(String pending);

}
