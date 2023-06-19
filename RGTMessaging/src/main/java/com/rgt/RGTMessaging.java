package com.rgt;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RGTMessaging {
    private HashMap<String, User> users;
    private ArrayList<Tweet> tweets;
    private User currentUser;

    public RGTMessaging() {
        this.users = new HashMap<>();
        this.tweets = new ArrayList<>();
        this.currentUser = null;
    }

    public void registerUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter bio:");
        String bio = scanner.nextLine();

        User user = new User(username, password, name, bio);
        users.put(username, user);
        System.out.println("Registration successful.");
    }

    public void login(Scanner scanner) {
        if (currentUser != null) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("Username does not exist. Please register or enter a valid username.");
            return;
        }

        User user = users.get(username);

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password. Please try again.");
            return;
        }

        currentUser = user;
        System.out.println("Login successful.");
    }

    public void followUser(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("You are not logged in. Please log in first.");
            return;
        }

        System.out.println("Enter username of the user you want to follow:");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("User does not exist. Please enter a valid username.");
            return;
        }

        User user = users.get(username);
        currentUser.follow(username);
        user.getFollowers().add(currentUser.getUsername());

        System.out.println("You are now following @" + username);
    }


    public void unfollowUser(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("You are not logged in. Please log in first.");
            return;
        }

        System.out.println("Enter username of the user you want to unfollow:");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("User does not exist. Please enter a valid username.");
            return;
        }

        User user = users.get(username);
        currentUser.unfollow(username);
        user.getFollowers().remove(currentUser.getUsername());

        System.out.println("You have unfollowed @" + username);
    }


    public void postTweet(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("You are not logged in. Please log in first.");
            return;
        }

        System.out.println("Enter tweet content:");
        String content = scanner.nextLine();

        currentUser.postTweet(content);

        System.out.println("Tweet posted successfully.");
    }

    public void deleteTweet(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("You are not logged in. Please log in first.");
            return;
        }

        System.out.println("Enter tweet ID:");
        String tweetId = scanner.nextLine();

        currentUser.deleteTweet(tweetId);

        System.out.println("Tweet deleted successfully.");
    }

    public void searchUser(Scanner scanner) {
        System.out.println("Enter username to search:");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            User user = users.get(username);
            user.displayProfile();
        } else {
            System.out.println("User not found.");
        }
    }

    public void searchTweet(Scanner scanner) {
        System.out.println("Enter a keyword to search for tweets:");
        String keyword = scanner.nextLine();

        List<Tweet> matchedTweets = new ArrayList<>();

        for (User user : users.values()) {
            List<Tweet> userTweets = user.getTweets();
            for (Tweet tweet : userTweets) {
                if (tweet.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                    matchedTweets.add(tweet);
                }
            }
        }

        if (matchedTweets.isEmpty()) {
            System.out.println("No tweets found matching the keyword: " + keyword);
        } else {
            System.out.println("Tweets matching the keyword: " + keyword);
            for (Tweet tweet : matchedTweets) {
            	Long timestamp = tweet.getTimestamp();
            	Timestamp timestamp2 = new Timestamp(timestamp);
                System.out.println("Tweet ID: " + tweet.getId());
                System.out.println("Author: @" + tweet.getAuthor());
                System.out.println("Content: " + tweet.getContent());
                System.out.println("Timestamp: " + timestamp2);
                System.out.println();
            }
        }
    }

    public void viewTimeline() {
        if (currentUser == null) {
            System.out.println("You are not logged in. Please log in first.");
            return;
        }

        List<Tweet> timelineTweets = new ArrayList<>();

        for (String username : currentUser.getFollowings()) {
            User user = users.get(username);
            timelineTweets.addAll(user.getTweets());
        }

        timelineTweets.addAll(currentUser.getTweets());

        timelineTweets.sort((t1, t2) -> Long.compare(t2.getTimestamp(), t1.getTimestamp()));

        if (timelineTweets.isEmpty()) {
            System.out.println("Your timeline is empty.");
        } else {
            System.out.println("Your timeline:");

            for (Tweet tweet : timelineTweets) {
                System.out.println("Tweet ID: " + tweet.getId());
                System.out.println("Author: @" + tweet.getAuthor());
                System.out.println("Content: " + tweet.getContent());
                System.out.println("Timestamp: " + tweet.getTimestamp());
                System.out.println();
            }
        }
    }

    public void viewProfile() {
        if (currentUser == null) {
            System.out.println("You are not logged in. Please log in first.");
            return;
        }

        currentUser.displayProfile();
    }

    public void logout() {
        if (currentUser == null) {
            System.out.println("You are not logged in.");
        } else {
            currentUser = null;
            System.out.println("Logged out successfully.");
        }
    }

    public void saveData(String fileName) {
        // Implementation to save data to a file
        DataStore.saveData(users, tweets, fileName);
    }

    public void loadData(String fileName) {
        // Implementation to load data from a file
        DataStore.loadData(users, tweets, fileName);
    }
}
