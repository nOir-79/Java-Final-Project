import java.io.Serializable;

class Food implements Serializable {
    String restaurantName;
    int RestaurantId;
    String category;
    String name;
    double price;

    public Food() {

    }

    public Food(int RestaurantID, String category, String name, double price) {
        this.RestaurantId = RestaurantID;
        this.category = category;
        this.name = name;
        this.price = price;
    }
}
