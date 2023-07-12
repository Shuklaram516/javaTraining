package org.rgt;

 

import java.util.HashMap;

 

public class ShoppingCart {
    private HashMap<Product, Integer> items;

 

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

 

    public HashMap<Product, Integer> getItems() {
        return items;
    }

 

    public void addItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            int existingQuantity = items.get(product);
            items.put(product, existingQuantity + quantity);
        } else {
            items.put(product, quantity);
        }
    }

 

    public void removeItem(Product product) {
        items.remove(product);
    }

 

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : items.keySet()) {
            int quantity = items.get(product);
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }
}
