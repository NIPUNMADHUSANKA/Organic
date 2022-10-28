package Foodify.Backend.controller;

import Foodify.Backend.model.FoodItem;
import Foodify.Backend.repository.FoodItem_Repository;
//import Foodify.Backend.service.FoodItem_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodItemController {

    @Autowired
    private FoodItem_Repository foodItem_repository;

/*     @Autowired(required = false)
    private FoodItem_Service foodItem_service;
*/
    @GetMapping("/FoodItems/All")
    public List<FoodItem> foodItemsAll(){
        List<FoodItem> Foods = foodItem_repository.findAll();
        return Foods;
    }

    @GetMapping("/FoodItemName/{id}")
    public String foodItemName(@PathVariable(value="id") String id) {
    
        return foodItem_repository.findById(id).get().getName();
    }


}
