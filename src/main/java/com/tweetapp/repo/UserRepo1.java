package com.tweetapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.User;

@Repository
public interface UserRepo1 extends JpaRepository<User, Integer> {

//	@Query("{ emailId : ?0,password: ?1 }")
	Optional<User> findByemailidAndPassword(String emailId, String password);

//	@Query("{ emailId : ?0}")
	Optional<User> findByEmailid(String userName);

}
