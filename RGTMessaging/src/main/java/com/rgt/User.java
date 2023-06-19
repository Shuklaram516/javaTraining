package com.rgt;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String name;
    private String bio;
    private List<String> followings;
    private List<String> followers;
    private List<Tweet> tweets;

    public User(String username, String password, String name, String bio) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.followings = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.tweets = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void follow(String username) {
        followings.add(username);
    }

    public void unfollow(String username) {
        followings.remove(username);
    }

    public void postTweet(String content) {
        long timestamp = System.currentTimeMillis();
        String tweetId = Long.toString(timestamp);
        Tweet tweet = new Tweet(tweetId, content, this.username);
        tweets.add(tweet);
    }

    public void deleteTweet(String tweetId) {
        tweets.removeIf(tweet -> tweet.getId().equals(tweetId));
    }

    public List<String> getFollowings() {
        return followings;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void displayProfile() {
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);
        System.out.println("Bio: " + bio);
        System.out.println("Followers: " + followers.size());
        System.out.println("Following: " + followings.size());
        System.out.println("Tweets: " + tweets.size());
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFollowings(List<String> followings) {
		this.followings = followings;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	
}
