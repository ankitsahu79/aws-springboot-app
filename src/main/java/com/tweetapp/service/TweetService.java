package com.tweetapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.TweetAppException;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.repo.TweetRepo1;
import com.tweetapp.repo.UserRepo1;
import com.tweetapp.request.TweetRequest;
import com.tweetapp.util.Envelope;
import com.tweetapp.util.TweetConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TweetService {

	@Autowired
	TweetRepo1 tweetRepository;

	@Autowired
	UserRepo1 userRepository;


	public ResponseEntity<String> postTweet(String userName, Tweet tweet) {
		
		tweetRepository.save(tweet);
		
		ResponseEntity<String> result = new ResponseEntity<String>("Tweet Added",HttpStatus.OK);
		
		return result;
	}

	public ResponseEntity<List<Tweet>> getAllTweet() {
		
		ResponseEntity<List<Tweet>> result  = null;
		
		List<Tweet> findAll = tweetRepository.findAll();
		
		result = new ResponseEntity<List<Tweet>>(findAll, HttpStatus.OK);
		
		return result;
	}

	public ResponseEntity<List<Tweet>> getAllUserTweet(String userName) {
		
		ResponseEntity<List<Tweet>> result  = null;
		
		List<Tweet> findByUserName = tweetRepository.findByUsername(userName);
			
		result = new ResponseEntity<List<Tweet>>(findByUserName, HttpStatus.OK);
		
		return result;
	}
	

	public ResponseEntity<String> updateTweet(String userName, int tweetId, Tweet tweet) {
		
	/*	ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.OK);
		
		tweetAndUserValidation(userName, tweetId);
		Tweet tweet = new Tweet(tweetRequest.getTweetId(), tweetRequest.getUserName(), tweetRequest.getTweet(),
				new Date(System.currentTimeMillis()), null, null);
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(tweetRequest.getUserName()));
		Update update = new Update();
		update.set(TweetConstant.TWEET, tweet.getTweet());
		tweet = mongoOperations.findAndModify(query, update, Tweet.class);
		if (tweet == null)
			throw new TweetAppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
					"Error While Updating Tweet");
		producer.sendMessage("Updated Tweet :: " + tweet.toString().concat(" by ::" + userName));
		
		result = new ResponseEntity<String>("Tweet Updated", HttpStatus.OK);
		
		return result;
		*/
		
		ResponseEntity<String> result = null;
		
		Tweet tweet1 = tweetRepository.findByTweetid(tweetId);
		
		if(tweet1!=null) {
			tweetRepository.save(tweet);
			result = new ResponseEntity<String>("Tweet Updated", HttpStatus.OK);
		}else {
			result = new ResponseEntity<String>("Tweet Not Found", HttpStatus.OK);
		}
		
		return result;
	}

	public ResponseEntity<String> deleteTweet(String userName, int tweetId) {
		
		                                                                              
		ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.OK);    
	//	tweetAndUserValidation(userName, tweetId);
		try {
		tweetRepository.deleteById(tweetId);
		result = new ResponseEntity<String>("Tweet Deleted", HttpStatus.OK);
		}catch(Exception e) {
			result = new ResponseEntity<String>("Tweet not found", HttpStatus.OK);
		}
		
		
		return result;
				
	}

	public ResponseEntity<String> likeTweet(String userName, int tweetId) {
	/*
		tweetAndUserValidation(userName, tweetId);
		Optional<Tweet> findById = tweetRepository.findById(tweetId);
		Tweet tweet = findById.get();
		Map<String, Integer> OldlikesMap = tweet.getLikes();
		Map<String, Integer> updatedLikesMap = new HashMap<>();
		if (OldlikesMap != null)
			updatedLikesMap.putAll(OldlikesMap);
		updatedLikesMap.put(userName, 1);
		tweet.setLikes(updatedLikesMap);
		Query query = new Query();
		query.addCriteria(Criteria.where(TweetConstant.TWEET_ID).is(tweetId));
		Update update = new Update();
		update.set(TweetConstant.LIKES, tweet.getLikes());
		
		tweet = mongoOperations.findAndModify(query, update, Tweet.class);
		if (tweet == null)
			throw new TweetAppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
					"Error While Liking");
		
		ResponseEntity<String> result = new ResponseEntity<String>("Tweet Liked",HttpStatus.OK);    
		
		return result;
		
		*/
		
       ResponseEntity<String> result = null;
		
		Tweet tweet1 = tweetRepository.findByTweetid(tweetId);
		
		if(tweet1!=null) {
			int likes = tweet1.getLikes();
			likes = likes+1;
			tweet1.setLikes(likes);
			tweetRepository.save(tweet1);
			result = new ResponseEntity<String>("Tweet Liked", HttpStatus.OK);
		}else {
			result = new ResponseEntity<String>("Tweet Not Found", HttpStatus.OK);
		}
		
		return result;
		
	}

	public ResponseEntity<String> replyTweet(String userName, int tweetId, String reply) {
	/*	
		tweetAndUserValidation(userName, tweetId);
		Optional<Tweet> findById = tweetRepository.findById(tweetId);
		Tweet tweet = findById.get();
		Map<String, List<String>> newReplyList = new HashMap<>();
		Map<String, List<String>> oldReplies = tweet.getReplies();
		if (oldReplies == null) {
			newReplyList.put(userName, Arrays.asList(reply));
		} else {
			if (oldReplies.containsKey(userName)) {
				List<String> list = new ArrayList<>(oldReplies.get(userName));
				list.add(reply);
				newReplyList.putAll(oldReplies);
				newReplyList.put(userName, list);
			} else {
				newReplyList.putAll(oldReplies);
				newReplyList.put(userName, Arrays.asList(reply));
			}
		}
		tweet.setReplies(newReplyList);
		tweet.setReplies(newReplyList);
		Query query = new Query();
		query.addCriteria(Criteria.where(TweetConstant.TWEET_ID).is(tweetId));
		Update update = new Update();
		update.set(TweetConstant.REPLIES, newReplyList);

		tweet = mongoOperations.findAndModify(query, update, Tweet.class);
		
		if (tweet == null)
			throw new TweetAppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
					"Error While replying");
		
          ResponseEntity<String> result = new ResponseEntity<String>("Tweet Replied",HttpStatus.OK);    
		
		return result;
		*/
		 ResponseEntity<String> result = null;
			
			Tweet tweet1 = tweetRepository.findByTweetid(tweetId);
			
			if(tweet1!=null) {
			
				tweet1.setReplies(reply);
				tweetRepository.save(tweet1);
				result = new ResponseEntity<String>("Tweet replied", HttpStatus.OK);
			}else {
				result = new ResponseEntity<String>("Tweet Not Found", HttpStatus.OK);
			}
			
			return result;
	}

	private void tweetAndUserValidation(String userName, int tweetId) {
	
		Optional<Tweet> findById = tweetRepository.findById(tweetId);
		Optional<User> findByEmailIdName = userRepository.findByEmailid(userName);
		
		
	}

}
