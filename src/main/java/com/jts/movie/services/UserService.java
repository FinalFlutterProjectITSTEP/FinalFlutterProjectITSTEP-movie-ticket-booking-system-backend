package com.jts.movie.services;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jts.movie.config.JWTService;
import com.jts.movie.config.UserInfoUserDetailsService;
import com.jts.movie.convertor.UserConvertor;
import com.jts.movie.entities.User;
import com.jts.movie.exceptions.InvalidToken;
import com.jts.movie.exceptions.UserDoesNotExists;
import com.jts.movie.exceptions.UserExist;
import com.jts.movie.exceptions.UserUnAuthorized;
import com.jts.movie.repositories.UserRepository;
import com.jts.movie.request.UserRequest;
import com.jts.movie.response.ApiResponse;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;


@Service
public class UserService {
	@Autowired
	UserInfoUserDetailsService userInfoUserDetailsService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	HttpServletRequest request;
	
	public List<User> userlist(){
		return userRepository.findAll();
	}
	
	public User profile(String emailId) {
		 // Extract the Authorization header from the request

	    Optional<User> user = userRepository.findByEmailId(emailId);
	    
	    if (!user.isPresent()) {
	        throw new UserDoesNotExists();
	    }
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    // Retrieve the token from the authentication object
		 if (authentication != null && authentication.isAuthenticated()) {
			 try {
			 if(authentication.getName().equals(emailId))
				 return user.get();
			 }catch(Exception ex) {
				 throw new InvalidToken(ex.getMessage());
			 }
	        // The user is authenticated and the email matches
		 }	
	 
	     throw new UserUnAuthorized("User is not authorized to access its profile");
	}

	
	public User addUser(UserRequest userRequest) {
	    Optional<User> user = userRepository.findByEmailId(userRequest.getEmailId());

	    if (user.isPresent()) {
	        throw new UserExist();
	    }

	    User newUser = UserConvertor.userDtoToUser(userRequest, passwordEncoder.encode("1234"));
	    userRepository.save(newUser);


	    return newUser;
	}
	
	
	
}
