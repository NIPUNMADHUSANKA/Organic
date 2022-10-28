package Foodify.Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Document(collection="IntakePending")
public class IntakePending {

    @Id
	private String id;
    private String userName;
    
    private String resturant;
    private String purches_time;
    private String purches_date;
    
    private Double calaries;
    private Double fat;
    private Double protein;
    private Double carbo;
    private Double price;


}
