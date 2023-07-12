package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ShoppingCart cart = new ShoppingCart();
		ProductCatalog productCatalog = new ProductCatalog();
		OrderHistory orderHistory = new OrderHistory();
		String productFileName = "product.txt";
		String orderFileName = "order.txt";
		while (true) {
			System.out.println("Welcome to Online System");
			System.out.println("1.create a new product file");
			System.out.println("2.Load a new product file");
			System.out.println("Enter your choice :");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				System.out.print("Enter a file name to save the product file: ");
				String fileName = scanner.nextLine();
				productCatalog.saveProducts(fileName);
				break;
			case 2:
				System.out.print("Enter the file name to load the product file: ");
				String fileNames = scanner.nextLine();
				productCatalog.loadProducts(fileNames);
				while (true) {
					System.out.println("\nMenu:");
					System.out.println("1. Browse products");
					System.out.println("2. Add product");
					System.out.println("3. Add a product to the cart");
					System.out.println("4. Remove a product from the cart");
					System.out.println("5. View cart items");
					System.out.println("6. Place an order");
					System.out.println("7. View Order History");
					System.out.println("8. Save order history");
					System.out.println("9. Load order history");
					System.out.println("10. Exit");
					System.out.print("Enter your choice: ");
					choice = scanner.nextInt();
					scanner.nextLine();
					switch (choice) {
					case 1:
						browseProducts(productCatalog);
						break;
					case 2:
						addProduct(scanner, productCatalog);
						break;
					case 3:
						addProductToCart(scanner, cart, productCatalog);
						break;
					case 4:
						removeProductToCart(scanner, cart, productCatalog);
						break;
					case 5:
						viewCart(cart);
						break;
					case 6:
						placeOrder(productCatalog, cart, orderHistory);
						break;
					case 7:
						viewOrderHistory(orderHistory);
						break;
					case 8:
						saveOrderHistory(scanner, orderHistory);
						break;
					case 9:
						loadOrderHistory(scanner, orderHistory);
						break;
					case 10:
						System.out.println("Exit");
						return;
					case 11:
						System.out.println("Thank you for using the Online Shopping System. Goodbye!");
						System.exit(0);
					default:
						System.out.println("Invalid choice. Please try again.");
					}
				}
			}
		}
	}

	private static void browseProducts(ProductCatalog productCatalog) {
		HashMap<String, Product> products = productCatalog.getAllProducts();
		System.out.println("Product Catalog:");
		for (Product product : products.values()) {
			System.out.println(product);
		}
	}

	private static void addProduct(Scanner scanner, ProductCatalog productCatalog) {
		System.out.println("enter product name");
		String name = scanner.next();
		System.out.println("enter description");
		String description = scanner.next();
		System.out.println("enter price");
		double price = scanner.nextDouble();
		System.out.println("enter quantity");
		int quantity = scanner.nextInt();
		try {
			Product product = new Product(name, description, price, quantity);
			productCatalog.addProduct(product);
			System.out.println("Product added successfully");
		} catch (Exception e) {
			System.out.println("Product is not added");
		}
	}

	private static void addProductToCart(Scanner scanner, ShoppingCart cart, ProductCatalog productCatalog) {
		System.out.print("Enter the name of the product to add: ");
		String productName = scanner.nextLine();
		Product products = productCatalog.getProduct(productName);
		if (products != null) {
			System.out.print("Enter the quantity: ");
			int quantity = scanner.nextInt();
			cart.addItem(products, quantity);
			System.out.println("Product added to the shopping cart.");
		} else {
			System.out.println("Product not found.");
		}
	}

	private static void removeProductToCart(Scanner scanner, ShoppingCart cart, ProductCatalog productCatalog) {
		HashMap<Product, Integer> items = cart.getItems();
		if (items.isEmpty()) {
			System.out.println("The cart is empty.");
			return;
		}
		System.out.print("\nEnter the name of the product to remove: ");
		String productName = scanner.nextLine();
		Product product = productCatalog.getProduct(productName);
		if (product != null) {
			cart.removeItem(product);
			System.out.println("Product removed from the shopping cart!");
		} else {
			System.out.println("Product not found in the shopping cart!");
		}
	}

	private static void viewCart(ShoppingCart shoppingCart) {
		HashMap<Product, Integer> items = shoppingCart.getItems();
		if (items.isEmpty()) {
			System.out.println("The cart is empty.");
		} else {
			System.out.println("Cart Items:");
			for (Map.Entry<Product, Integer> entry : items.entrySet()) {
				Product product = entry.getKey();
				int quantity = entry.getValue();
				System.out.println(product.getName() + " (Quantity: " + quantity + ")");
			}
			System.out.println("Total Price: $" + shoppingCart.getTotalPrice());
		}
	}

	private static void placeOrder(ProductCatalog productCatalog, ShoppingCart shoppingCart,
			OrderHistory orderHistory) {
		HashMap<Product, Integer> items = shoppingCart.getItems();
		if (items.isEmpty()) {
			System.out.println("The cart is empty. Please add products to the cart before placing an order.");
			return;
		}
		// Check if the quantities are still available in the product catalog
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			if (product.getQuantity() < quantity) {
				System.out.println("Insufficient quantity available for product: " + product.getName());
				return;
			}
		}
		double price = 0;
		String name = "as";
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			price += product.getPrice();
			int quantity = entry.getValue();
			name = entry.toString();
			product.setQuantity(product.getQuantity() - quantity);
		}
		Order order = new Order();
		order.setConfirmationNumber(name);
		order.setItems(items);
		order.setTotalPrice(price);
		orderHistory.addOrder(order);
		shoppingCart.getItems().clear();
		System.out.println("Order placed successfully!");
		System.out.println("Confirmation Number: " + order.getConfirmationNumber());
		System.out.println("Total Price: $" + order.getTotalPrice());
	}

	private static void viewOrderHistory(OrderHistory orderHistory) {
		List<Order> ordersList = orderHistory.getAllOrders();
		if (ordersList.isEmpty()) {
			System.out.println("Order details is empty");
		} else {
			System.out.println("Order history");
			for (Order orders : ordersList) {
				System.out.println(orders);
				System.out.println("------------------------------------------------------------");
			}
		}
	}

	private static void saveOrderHistory(Scanner scanner, OrderHistory orderHistory) {
		System.out.print("\nEnter a file name to save the order history: ");
		String fileName = scanner.nextLine();
		orderHistory.saveOrderHistory(fileName);

	}

	private static void loadOrderHistory(Scanner scanner, OrderHistory orderHistory) {
		System.out.print("\nEnter a file name to load the order history: ");
		String fileName = scanner.nextLine();
		orderHistory.loadOrderHistory(fileName);
	}
}