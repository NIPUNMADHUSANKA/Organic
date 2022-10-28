package Foodify.Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Document(collection="IntakePendingItem")
public class IntakePendingItem {

    @Id
	private String id;
    private String pending;

    private String item;
    private Double quantity;

    private Double calaries;
    private Double fat;
    private Double protein;
    private Double carbo;
    private Double price;
    
}
