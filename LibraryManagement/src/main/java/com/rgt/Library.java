package com.rgt;

import java.util.Scanner;

public class Library {

	// Constants
    private static final int MAX_BOOKS = 100;
    private static final int MAX_PATRONS = 50;

    // Arrays to store books and patrons
    private static String[] books = new String[MAX_BOOKS];
    private static String[] authors = new String[MAX_BOOKS];
    private static String[] patrons = new String[MAX_PATRONS];
    private static String[] borrowedBooks = new String[MAX_PATRONS];

    // Track the number of books and patrons currently stored
    private static int numBooks = 0;
    private static int numPatrons = 0;
    
	public void addBook(Scanner scanner) {
        if (numBooks >= MAX_BOOKS) {
            System.out.println("Maximum number of books reached.");
            return;
        }

        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author name: ");
        String author = scanner.nextLine();

        books[numBooks] = title;
        authors[numBooks] = author;
        numBooks++;

        System.out.println("Book added successfully.");
    }

    public void addPatron(Scanner scanner) {
        if (numPatrons >= MAX_PATRONS) {
            System.out.println("Maximum number of patrons reached.");
            return;
        }

        System.out.print("Enter the patron name: ");
        String name = scanner.nextLine();

        patrons[numPatrons] = name;
        numPatrons++;

        System.out.println("Patron added successfully.");
    }

    public void borrowBook(Scanner scanner) {
        System.out.print("Enter the patron name: ");
        String patronName = scanner.nextLine();

        int patronIndex = findPatronIndex(patronName);
        if (patronIndex == -1) {
            System.out.println("Patron not found.");
            return;
        }

        System.out.print("Enter the book title: ");
        String bookTitle = scanner.nextLine();

        int bookIndex = findBookIndex(bookTitle);
        if (bookIndex == -1) {
            System.out.println("Book not found.");
            return;
        }

        if (borrowedBooks[bookIndex] != null) {
            System.out.println("Book is already borrowed by another patron.");
            return;
        }

        borrowedBooks[bookIndex] = patronName;
        System.out.println("Book borrowed successfully.");
    }

    public void returnBook(Scanner scanner) {
        System.out.print("Enter the book title: ");
        String bookTitle = scanner.nextLine();

        int bookIndex = findBookIndex(bookTitle);
        if (bookIndex == -1) {
            System.out.println("Book not found.");
            return;
        }

        if (borrowedBooks[bookIndex] == null) {
            System.out.println("Book is not currently borrowed.");
            return;
        }

        borrowedBooks[bookIndex] = null;
        System.out.println("Book returned successfully.");
    }

    private static int findBookIndex(String bookTitle) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].equalsIgnoreCase(bookTitle)) {
                return i;
            }
        }
        return -1;
    }

    private static int findPatronIndex(String patronName) {
        for (int i = 0; i < numPatrons; i++) {
            if (patrons[i].equalsIgnoreCase(patronName)) {
                return i;
            }
        }
        return -1;
    }
}
