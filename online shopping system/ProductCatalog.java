package org.rgt;

 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

 


public class ProductCatalog {
    private HashMap<String, Product> products;

 

    public ProductCatalog() {
        this.products = new HashMap<>();
    }

 

    public HashMap<String, Product> getAllProducts() {
        return products;
    }

 

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

 

    public Product getProduct(String name) {
        return products.get(name);
    }

 

    public void saveProducts(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Product product : products.values()) {
                writer.println(product.getName() + "," +
                        product.getDescription() + "," +
                        product.getPrice() + "," +
                        product.getQuantity());
            }
            System.out.println("Product file saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving product file: " + e.getMessage());
        }
    }

 

    public void loadProducts(String fileName) {
        products.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0];
                    String description = data[1];
                    double price = Double.parseDouble(data[2]);
                    int quantity = Integer.parseInt(data[3]);
                    Product product = new Product(name, description, price, quantity);
                    addProduct(product);
                }
            }
            System.out.println("Product file loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading product file: " + e.getMessage());
        }
          catch (NumberFormatException e) {
            System.out.println("Error loading product file: " + e.getMessage());
        }
    }
}