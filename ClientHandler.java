import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class ClientHandler extends Thread {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public String clientName;
    public String clientType;
    public RestaurantManager restaurantManager;

    public ClientHandler(Socket socket, RestaurantManager restaurantManager) {
        this.socket = socket;
        this.restaurantManager = restaurantManager;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            clientType = (String) in.readObject();
            clientName = (String) in.readObject();
            out.writeObject(restaurantManager);
            if (clientType.equals("Customer")) {
                Server.addCustomerHandler(clientName, this);
                int choice;
                choice = (Integer) in.readObject();
                if (choice == 1) {

                } else if (choice == 2) {

                } else if (choice == 3) {
                    try {
                        System.out.println("Inside choice 3");
                        while (true) {
                            int restaurantOrder = (int) in.readObject();
                            if (restaurantOrder != -1) {
                                String restaurantName = restaurantManager.restaurants.get(restaurantOrder).name;
                                while (true) {
                                    String orderFood = (String) in.readObject();
                                    if (orderFood.equals("Ordering Food")) {
                                        int foodChoice = (int) in.readObject();
                                        if (foodChoice != -1) {
                                            HashMap<String, Integer> foodOrder = (HashMap<String, Integer>) in
                                                    .readObject();
                                            for (int i = 0; i < restaurantManager.restaurants.size(); i++) {
                                                if (restaurantManager.restaurants.get(i).name
                                                        .equals(restaurantManager.restaurants
                                                                .get(restaurantOrder).name)) {
                                                    restaurantManager.restaurants.get(i).addOrder(clientName,
                                                            foodOrder);
                                                    break;
                                                }
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                break;
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } else if (clientType.equals("Restaurant")) {
                Server.addRestaurantHandler(clientName, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
