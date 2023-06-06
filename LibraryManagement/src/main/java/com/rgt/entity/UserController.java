package com.rgt.entity;

import java.util.Scanner;

public class UserController {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		User user = new User();
		
		int choice = 0;
		
		do {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
            case 1:
                user.userRegistration();;
                break;
            case 2:
                user.userLogin();
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice! Try again.");
                break;
        }
    } while (choice != 3);

		scanner.close();
	}

}
