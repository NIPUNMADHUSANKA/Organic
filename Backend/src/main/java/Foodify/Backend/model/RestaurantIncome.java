package Foodify.Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection="restaurantsincome")
public class RestaurantIncome {

    @Id
	private String id;
	private String userName;
	private String restaurantName;
	private String date;
    private double items;
    private double total;
    
}
