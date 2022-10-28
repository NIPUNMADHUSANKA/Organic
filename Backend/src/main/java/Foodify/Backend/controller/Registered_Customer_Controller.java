
package Foodify.Backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Foodify.Backend.repository.Order_Repository;
import Foodify.Backend.repository.Registered_Customer_Repository;
import Foodify.Backend.exception.Registered_Customer_Exception;
import Foodify.Backend.exception.fieldErrorResponse;
import Foodify.Backend.model.Complain;
import Foodify.Backend.model.FoodCategory;
import Foodify.Backend.model.FoodMenu;
import Foodify.Backend.model.Order;
import Foodify.Backend.model.Registered_Customer;
import Foodify.Backend.model.Restaurant;
import Foodify.Backend.service.Registered_Customer_Sev;

import java.util.List;

//using cross origin annotation to communicate with react.js and spring

@RestController
@CrossOrigin (origins = "http://localhost:3000")
public class Registered_Customer_Controller {

	@Autowired
	private Registered_Customer_Sev RegCusServ;
	
	@Autowired
	private Registered_Customer_Repository RegCusRepo;
	
	@Autowired
	private Order_Repository orderRepo;
	
	
	@GetMapping("/Foodify/{Username}/{Password}")
	public ResponseEntity<?> Login(@PathVariable("Username") String Username, @PathVariable("Password") String Password ){
	
		try {
			return new ResponseEntity<>(RegCusServ.Login(Username, Password), HttpStatus.OK);
		}
		catch(Registered_Customer_Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	fieldErrorResponse fieldErrorResponse = new fieldErrorResponse();
	
//	-----------------------------------------create method-------------------------------------------------------------------
//	----------to response entity, use response object----------
	@PostMapping("/Register/Signupuser")
	public ResponseEntity<?> createUser(@Valid @RequestBody Registered_Customer registeredCustomer) {
		
		ResponseEntity<Object> count = RegCusServ.validate("userName", "email",registeredCustomer.getuserName() , registeredCustomer.getEmail());
		
		String userName = registeredCustomer.getuserName();
		String email = registeredCustomer.getEmail();
		String password = registeredCustomer.getpassword();
		String accountState = registeredCustomer.getaccountState();


//		--------------------sending data to db if there is no errors--------------------------------------------
		if(count == null) {
			RegCusServ.passwordEncorder(userName, email, password, accountState);
//			RegCusRepo.save(registeredCustomer);
		}
//		RegCusRepo.find();
//		 System.out.println(data);
		return count;				
	}
//	----------------end of create method-----------------------------------------------------------------------------------------
	

//	-----------------------------------------create method-------------------------------------------------------------------
//	----------to response entity, use response object----------
	@PostMapping("/Register/Signuppremiumuser")
	public ResponseEntity<?> createPremiumUser(@Valid @RequestBody Registered_Customer registeredCustomer) {
		
//		RegCusRepo.save(registeredCustomer);
		
		
//		RegCusServ service = new RegCusServ();
		
		ResponseEntity<Object> count = RegCusServ.validate("userName", "email",registeredCustomer.getuserName() , registeredCustomer.getEmail());
		
		String userName = registeredCustomer.getuserName();
		String email = registeredCustomer.getEmail();
		String password = registeredCustomer.getpassword();
		String accountState = registeredCustomer.getaccountState();


//		--------------------sending data to db if there is no errors--------------------------------------------
		if(count == null) {
			RegCusServ.passwordEncorder(userName, email, password, accountState);
//			RegCusRepo.save(registeredCustomer);
		}
//		RegCusRepo.find();
//		 System.out.println(data);
		return count;				
	}
//	----------------end of create method-----------------------------------------------------------------------------------------

	@GetMapping("/FoodiFy/Admin/Users/All")
	public List<Registered_Customer> usersAll(){
		return RegCusRepo.findAll();
	}

//	@PostMapping("/Foodify/Admin/Block/{userId}")
//	public Boolean Block(@PathVariable ("userId") String userId){ return RegCusRepo.blockUser(userId); }

	





	


	
//	----------------------------de_activate method-------------------------------------------------------------------------------
//	@PostMapping("/user/deactivate/{id}")
//	public void deacivateUser(@PathVariable String id) {
//		
//	}
//	
////	show details method
//	@GetMapping
//	public void showUserDetails() {
//		
//	}
	
	@GetMapping("/FoodiFy/User/editprofile")
	public Registered_Customer showProfile() {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Registered_Customer regcustomer = RegCusRepo.findByuserName(userName);
		regcustomer.setuserName(regcustomer.getuserName());
		regcustomer.setEmail(regcustomer.getEmail());
		regcustomer.settelephone(regcustomer.gettelephone());
		regcustomer.setlocation(regcustomer.getlocation());
		regcustomer.setbImage(Base64.getEncoder().encodeToString(regcustomer.getImage().getData()));
		return regcustomer;

	}
	
	@PostMapping("/FoodiFy/User/addprofiledetails")
	public ResponseEntity<?> uploadprofiledetails(
    		@RequestParam("Image")MultipartFile file,
    		@RequestParam("Telephone_No") String name1,
    		@RequestParam("City") String name2) throws IOException {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//		System.out.println(complain);

		System.out.println(userName);
		Registered_Customer regcustomerdetails = RegCusRepo.findByuserName(userName);

		regcustomerdetails.settelephone(name1);
		regcustomerdetails.setlocation(name2);
		regcustomerdetails.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		RegCusRepo.save(regcustomerdetails);
		return null;

	}
	
	
	
	

	
	
}