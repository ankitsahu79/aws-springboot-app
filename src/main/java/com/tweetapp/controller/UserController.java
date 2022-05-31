package com.tweetapp.controller;

import static com.tweetapp.util.TweetConstant.ROOT_URL;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.exception.TweetAppException;
import com.tweetapp.model.User;
import com.tweetapp.service.UserService;
import com.tweetapp.util.Envelope;

import io.micrometer.core.annotation.Timed;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = ROOT_URL)
@RestController
@Slf4j
@Generated
public class UserController {

	@Autowired
	UserService userService;

	@CrossOrigin
	@PostMapping(value = "/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		System.out.println("inside register user ..");
		
		return userService.register(user);
	}

	@GetMapping(value = "/login")
	public ResponseEntity<String> login(@RequestParam("emailid") String emailId,
			@RequestParam("password") String password) throws TweetAppException {
		
		return userService.login(emailId, password);
	}

	@GetMapping(value = "/forgot")
	@Timed(value = "forgotPassword.time", description = "Time taken to return forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestParam("username") String userName,
			@RequestParam("newpassword") String Password) {
		

		return userService.forgotPassword(userName, Password);
	}

	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> users() {
	
		return userService.getAllusers();
	}

	@GetMapping(value = "/users/search")
	public ResponseEntity<User> searchUserName(@RequestParam("username") String username) {
	
		return userService.username(username);
	}

}
