package com.rgt.practice.bestone;

import java.util.ArrayList;

class Library {
    private ArrayList<User> users;
    private ArrayList<Book> books;
    private User currentUser;

    public Library() {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.currentUser = null;
    }

    public void registerUser(String username, String password) {
        users.add(new User(username, password));
        System.out.println("User registration successful!");
    }

    public void loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful!");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    public void removeBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                books.remove(book);
                System.out.println("Book removed successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Available books:");
            for (Book book : books) {
                if (book.isAvailable()) {
                    System.out.println(book.getTitle() + " by " + book.getAuthor());
                }
            }
        }
    }

    public void searchBook(String query) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching the query.");
        }
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logged out successfully!");
    }
}