package Foodify.Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Document(collection="Intake")
public class IntakeChart {

    @Id
	private String id;
    private String userName;
    private String lastUpdate;
   
    private Double calaries;
    private Double fat;
    private Double protein;
    private Double carbo;


}
