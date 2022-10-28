package Foodify.Backend.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="FoodItems")
public class FoodItem {
    @Id
    private String id;
    private String name;
    private Double price;
    private String description;
    private String catId;
    private String resId;
    private Double calaries;
    

	private Double fat;
    private Double protein;
    private Double carbo;
    private Binary image;
    private String bImage;
    
    private int discount;

    private int total;

    public Double getCalaries() {
        return calaries;
    }

    public void setCalaries(Double calaries) {
        this.calaries = calaries;
    }
    
    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }
    
    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }
    
    public Double getCarbo() {
        return carbo;
    }

    public void setCarbo(Double carbo) {
        this.carbo = carbo;
    }

    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() { return Math.toIntExact(Math.round(price)); }

    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getcatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public Binary getImage() {
		return image;
	}
	public Binary setImage(Binary image) {
		return this.image = image;
	}

  public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public String getbImage() {
		return bImage;
	}

	public void setbImage(String bImage) {
		this.bImage = bImage;
	}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
