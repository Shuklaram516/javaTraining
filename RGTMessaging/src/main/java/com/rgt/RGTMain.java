package com.rgt;

import java.util.Scanner;

public class RGTMain {
    private static final String DATA_FILE = "data.json"; // Data file name

    public static void main(String[] args) {
        RGTMessaging rgtMessaging = new RGTMessaging();

        // Load data from file
        rgtMessaging.loadData(DATA_FILE);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to RGTMessaging!");

        boolean isLoggedIn = false;

        while (true) {
            if (!isLoggedIn) {
                System.out.println("Please choose an option:");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        rgtMessaging.login(scanner);
                        isLoggedIn = true;
                        break;
                    case 2:
                        rgtMessaging.registerUser(scanner);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                while (running) {
                    System.out.println("Please choose an option:");
                    System.out.println("1. Follow user");
                    System.out.println("2. Unfollow user");
                    System.out.println("3. Post tweet");
                    System.out.println("4. Delete tweet");
                    System.out.println("5. Search user");
                    System.out.println("6. Search tweet");
                    System.out.println("7. View timeline");
                    System.out.println("8. View profile");
                    System.out.println("9. Logout");
                    System.out.println("10. Exit");

                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    switch (option) {
                        case 1:
                            rgtMessaging.followUser(scanner);
                            break;
                        case 2:
                            rgtMessaging.unfollowUser(scanner);
                            break;
                        case 3:
                            rgtMessaging.postTweet(scanner);
                            break;
                        case 4:
                            rgtMessaging.deleteTweet(scanner);
                            break;
                        case 5:
                            rgtMessaging.searchUser(scanner);
                            break;
                        case 6:
                            rgtMessaging.searchTweet(scanner);
                            break;
                        case 7:
                            rgtMessaging.viewTimeline();
                            break;
                        case 8:
                            rgtMessaging.viewProfile();
                            break;
                        case 9:
                            rgtMessaging.logout();
                            isLoggedIn = false;
                            break;
                        case 10:
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }

                // Save data to file
                rgtMessaging.saveData(DATA_FILE);

                System.out.println("Thank you for using RGTMessaging!");
                break;
            }
        }
    }
}
