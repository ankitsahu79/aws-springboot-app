package com.tweetapp.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;


@Entity
@Table(name = "Tweet")
public class Tweet {
	@Id
	private int tweetid;
	private String username;
	private String tweet;
	private Date created;
	private int likes;
	private String replies;
	public int getTweetid() {
		return tweetid;
	}
	public void setTweetid(int tweetid) {
		this.tweetid = tweetid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getReplies() {
		return replies;
	}
	public void setReplies(String replies) {
		this.replies = replies;
	}
	@Override
	public String toString() {
		return "Tweet [tweetid=" + tweetid + ", username=" + username + ", tweet=" + tweet + ", created=" + created
				+ ", likes=" + likes + ", replies=" + replies + "]";
	}
	public Tweet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tweet(int tweetid, String username, String tweet, Date created, int likes, String replies) {
		super();
		this.tweetid = tweetid;
		this.username = username;
		this.tweet = tweet;
		this.created = created;
		this.likes = likes;
		this.replies = replies;
	}
	
	
	

}
