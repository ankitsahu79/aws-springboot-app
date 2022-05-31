package com.tweetapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweet;

@Repository
public interface TweetRepo1 extends JpaRepository<Tweet, Integer> {

	//@Query("{ userName : ?0}")
	List<Tweet> findByUsername(String username);
	Tweet findByTweetid(int tweetid);

}
