package Foodify.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Foodify.Backend.model.Restaurant_About;
import Foodify.Backend.repository.RestaurantAboutRepository;

@RestController
@RequestMapping("/Restaurant")
@CrossOrigin (origins = "http://localhost:3000")
public class RestaurantAboutController {
	
	@Autowired
	private RestaurantAboutRepository Res_about_repo;
	
	@PostMapping("/addAbout")
	public String saveAbout(@RequestBody Restaurant_About restaurant_about ) {
		Res_about_repo.save(restaurant_about);
        
        return "Added Successfully";
	}
	
//	@GetMapping("/viewAbout/{id}")
//	public List<Restaurant_About> getAbout(@PathVariable String id) {
//		
//	}
	
	//update existing student record by id
//	@PutMapping("/{student_id}")
	
	
	
	@DeleteMapping("/delete/{id}")
	public String deleteAbout(@PathVariable String id) {
		Res_about_repo.deleteById(id);
		return "Deleted Successfully";
	}
	

}
