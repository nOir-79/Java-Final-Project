import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

class Restaurant implements Serializable {
    int Id;
    String name;
    double score;
    String price;
    int ZipCode;
    List<String> categories = new ArrayList<>();
    HashMap<String, Integer> orderCount = new HashMap<>();

    public Restaurant() {

    }

    public Restaurant(int Id, String name, double score, String price, int ZipCode, List<String> categories) {
        this.Id = Id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.ZipCode = ZipCode;
        this.categories = categories;
    }

    public void addOrder(String clientName, HashMap<String, Integer> order) {
        for (String foodName : order.keySet()) {
            if (orderCount.containsKey(foodName)) {
                orderCount.put(foodName, orderCount.get(foodName) + order.get(foodName));
            } else {
                orderCount.put(foodName, order.get(foodName));
            }
        }
    }
}
