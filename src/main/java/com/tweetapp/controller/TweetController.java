package com.tweetapp.controller;

import static com.tweetapp.util.TweetConstant.ROOT_URL;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.Tweet;
import com.tweetapp.request.TweetRequest;
import com.tweetapp.service.TweetService;


import io.micrometer.core.annotation.Timed;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = ROOT_URL)
@RestController
@Slf4j
@Generated
public class TweetController {

	@Autowired
	TweetService tweetService;

	@PostMapping("/add/{username}")
	@Timed(value = "postTweet.time", description = "Time taken to return postTweet")
	public ResponseEntity<String> postTweet(@PathVariable("username") String username,
			@RequestBody Tweet tweet) {
	
		return tweetService.postTweet(username, tweet);
	}

	@GetMapping("/all")
	@Timed(value = "all.time", description = "Time taken to return allTweet")
	public ResponseEntity<List<Tweet>> getAllTweet() {
	
		return tweetService.getAllTweet();
	}

	@GetMapping("/{userName}")
	@Timed(value = "getAllUserTweet.time", description = "Time taken to return getAllUserTweet")
	public ResponseEntity<List<Tweet>> getAllUserTweet(@PathVariable String userName) {
		return tweetService.getAllUserTweet(userName);
	}

	@PutMapping("/{userName}/update/{tweetId}")
	@Timed(value = "updateTweet.time", description = "Time taken to return updateTweet")
	public ResponseEntity<String> updateTweet(@PathVariable("userName") String userName,
			@PathVariable("tweetId") int tweetId, @RequestBody Tweet tweet) {
		return tweetService.updateTweet(userName, tweetId, tweet);
	}

	@DeleteMapping("/{userName}/delete/{tweetId}")
	@Timed(value = "deleteTweet.time", description = "Time taken to return deleteTweet")
	public ResponseEntity<String> deleteTweet(@PathVariable("userName") String userName,
			@PathVariable("tweetId") int tweetId) {
		
		return tweetService.deleteTweet(userName, tweetId);
	}

	@PutMapping("/{userName}/like/{tweetId}")
	@Timed(value = "likeTweet.time", description = "Time taken to return likeTweet")
	public ResponseEntity<String> likeTweet(@PathVariable("userName") String userName,
			@PathVariable("tweetId") int tweetId) {
		return tweetService.likeTweet(userName, tweetId);
	}

	@PostMapping("/{userName}/reply/{tweetId}/{reply}")
	@Timed(value = "replyTweet.time", description = "Time taken to return replyTweet")
	public ResponseEntity<String> replyTweet(@PathVariable("userName") String userName,
			@PathVariable("tweetId") int tweetId, @PathVariable("reply") String reply) {
			return tweetService.replyTweet(userName, tweetId, reply);
	}

}
