import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RestaurantClient {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("Restaurant");
            System.out.println("Enter Restaurant Name:");
            String name = scanner.nextLine();
            out.writeObject(name);
            RestaurantManager restaurantManager = (RestaurantManager) in.readObject();
            System.out.println("These are the orders sent to the Restaurant:");
            for (int i = 0; i < restaurantManager.restaurants.size(); i++) {
                if (restaurantManager.restaurants.get(i).name.equals(name)) {
                    for (String food : restaurantManager.restaurants.get(i).orderCount.keySet()) {
                        System.out.println(
                                food + ": " + restaurantManager.restaurants.get(i).orderCount.get(food));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
