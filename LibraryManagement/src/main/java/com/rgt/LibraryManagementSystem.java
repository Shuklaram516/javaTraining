package com.rgt;
import java.util.Scanner;

public class LibraryManagementSystem {
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nLibrary Management System");
            System.out.println("-------------------------");
            System.out.println("1. Add a book");
            System.out.println("2. Add a patron");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    library.addBook(scanner);
                    break;
                case 2:
                    library.addPatron(scanner);
                    break;
                case 3:
                    library.borrowBook(scanner);
                    break;
                case 4:
                    library.returnBook(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the Library Management System!");
    }
}
