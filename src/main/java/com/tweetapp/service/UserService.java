package com.tweetapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.TweetAppException;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.repo.UserRepo1;
import com.tweetapp.util.Envelope;
import com.tweetapp.util.TweetConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepo1 userRepository;

	public ResponseEntity<String> login(String userName, String password) throws TweetAppException {

		Optional<User> isValid = userRepository.findByemailidAndPassword(userName, password);
		String userValid = isValid.isPresent() ? TweetConstant.LOGIN_SUCCESS : TweetConstant.LOGIN_FAILED;
	
		ResponseEntity<String> result = new ResponseEntity<String>(userValid, HttpStatus.OK);

		return result;
	}

	public ResponseEntity<String> register(User user) {

		ResponseEntity<String> result = null;
		try {
			System.out.println("inside register method :" + user);

			userRepository.save(user);

			result = new ResponseEntity<String>(user.getEmailid() + " " + user, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public ResponseEntity<String> forgotPassword(String username, String password) {
		/*
		 * Optional<User> findByEmailIdName =
		 * userRepository.findByEmailIdName(userName); if
		 * (!findByEmailIdName.isPresent()) { throw new
		 * TweetAppException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
		 * TweetConstant.USER_NAME_NOT_PRESENT); }
		 * producer.sendMessage("Forgot Password for :: " + userName.concat(" " +
		 * password)); Query query = new Query();
		 * query.addCriteria(Criteria.where(TweetConstant.EMAIL_ID).is(userName));
		 * 
		 * Update update = new Update(); update.set(TweetConstant.PASSWORD, password);
		 * 
		 * User user = mongoperation.findAndModify(query, update, User.class);
		 * 
		 * if (user == null) throw new
		 * TweetAppException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
		 * HttpStatus.INTERNAL_SERVER_ERROR,
		 * TweetConstant.ERROR_WHILE_UPDATING_PASSWORD);
		 * 
		 * 
		 * ResponseEntity<String> result = new
		 * ResponseEntity<String>(TweetConstant.PASSWORD_UPDATED,HttpStatus.OK);
		 * 
		 * return result;
		 */
		ResponseEntity<String> result = null;
		 Optional<User> existingUser = userRepository.findByEmailid(username);
		 
		
		 
		 if(existingUser.isPresent()) {
			 User user = existingUser.get();
			 user.setPassword(password);
			 userRepository.save(user);
			 result = new ResponseEntity<String>(TweetConstant.PASSWORD_UPDATED,HttpStatus.OK);
		 }else {
			 result = new ResponseEntity<String>(TweetConstant.NO_USERS_FOUND,HttpStatus.OK);
		 }
		
		 
		  return result;
	}

	public ResponseEntity<List<User>> getAllusers() {

		List<User> findAll = userRepository.findAll();

		ResponseEntity<List<User>> result = new ResponseEntity<List<User>>(findAll, HttpStatus.OK);

		return result;
	}

	public ResponseEntity<User> username(String userName) {

		Optional<User> userPresent = userRepository.findByEmailid(userName);

		ResponseEntity<User> result = new ResponseEntity<User>(userPresent.get(), HttpStatus.OK);

		return result;

	}
}
