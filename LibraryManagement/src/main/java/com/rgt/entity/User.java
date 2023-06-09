package com.rgt.entity;

import java.util.Scanner;

/**
 * @author RamShukla
 *User Account class
 */
public class User {

	Scanner scanner = new Scanner(System.in);
	private String[][] userArray;
	private int numberOfUsers;
	
	private static final int max_users = 50;

	public User() {
		userArray = new String[max_users][2];
		numberOfUsers = 0;
	}
	
	
	/**
	 * user registration 
	 */
	public void userRegistration() {
		System.out.print("Enter User Name");
		String username = scanner.nextLine();
		
		System.out.println("Enter password");
		String password = scanner.nextLine();
	
		userArray[numberOfUsers][0] = username;
		userArray[numberOfUsers][1] = password;
		
		numberOfUsers++;
		System.out.println("Registration Successful !!");
	}
	
	/**
	 * user login
	 */
	public void userLogin() {
		System.out.println("Enter User Name : ");
		String username = scanner.nextLine();
		
		System.out.println("Enter password");
		String password = scanner.nextLine();
		
		for (String[] user : userArray) {
			if (user[0] != null && user[0].equals(username)) {
				if (user[1].equals(password)) {
					System.out.println("Login Successful !!");
				} else {
	                System.out.println("Incorrect password!");
	                return;
	            }
			}
		}
		System.out.println("Username not found !!");
	}
}
