package Foodify.Backend.controller;

import Foodify.Backend.model.RestaurantComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import Foodify.Backend.model.FoodComments;
import Foodify.Backend.repository.FoodCommentRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FoodCommentController {
	
	@Autowired
	private FoodCommentRepository foodCommentRepository;
	
	@PostMapping("/FoodiFy/User/addFoodComment")
	public FoodComments createComment(@RequestBody FoodComments Foodcomment) {
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(Foodcomment);

		System.out.println(userName);
//		String restauratId=commentRes.getRestaurantId();
//		String commentDescription=complain.getCommentDescription();
//		Date addedDate=complain.getAddedDate();
		
		Foodcomment.setUsername(userName);
		
		
		return foodCommentRepository.save(Foodcomment);
	}

	/* -------------------------------- Get Res Comments Customer view -------------------------------- */
	@GetMapping("/FoodiFy/Service/getFoodCommentC")
	public List<FoodComments> getFoodComments() {

//		Restaurant restaurant = restaurantrepo.findByid(id);
//		String userName = restaurant.getUserName();

		List<FoodComments> foodComments = foodCommentRepository.findAll();
//		List<RestaurantComments> restaurantCommentList = new ArrayList<RestaurantComments>();
//		--------------------setting relevant data for output------------------------
//		for(int i = 0; i<items.size();i++) {
//			RestaurantComments restaurantComment = new RestaurantComments();
//			restaurantComment.setUsername(items.get(i).getUsername());
//			restaurantComment.setCommentDescription(items.get(i).getCommentDescription());
//			restaurantComment.setId(items.get(i).getId());
//			restaurantCommentList.add(restaurantComment);
//		}
		return foodComments;
	}

	@GetMapping("/FoodiFy/AllUser/getFoodComment/{id}")
	public List<FoodComments> getFoodComments(@PathVariable(value="id") String id) {

//		FoodComments foodComments = foodCommentRepository.findByid(id);


		return foodCommentRepository.findByfoodId(id);
	}

}

