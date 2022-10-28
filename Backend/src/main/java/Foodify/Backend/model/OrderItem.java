package Foodify.Backend.model;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="FoodItems")
public class OrderItem {

        public ObjectId item;
        public String foodItem;
        public Integer quantity;

        public String restaurantId;

        public int price;

        public int total;

        public int discount;

        public String foodId;

        public String preparedStatus;

        public String foodName;

        public Binary image;

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {

        this.foodName = foodName;
    }

    public String getPreparedStatus() {
        return preparedStatus;
    }

    public void setPreparedStatus(String preparedStatus) {
        this.preparedStatus = preparedStatus;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getPrice() {
            return price;
        }


    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public ObjectId getItem() {
        return item;
    }

    public void setItem(ObjectId item) {
        this.item = item;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem.toString();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }


    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
