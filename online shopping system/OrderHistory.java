package org.example;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders;

 

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

 

    public List<Order> getAllOrders() {
        return orders;
    }

 

    public void addOrder(Order order) {
        orders.add(order);
    }

 

    public void saveOrderHistory(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(orders);
            System.out.println("Order history saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }

 

    public void loadOrderHistory(String fileName) {
        orders.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            orders = (List<Order>) ois.readObject();
            System.out.println("Order history loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading order history: " + e.getMessage());
        }
    }



	@Override
	public String toString() {
		return "OrderHistory [orders=" + orders + "]";
	}
    
    
}
