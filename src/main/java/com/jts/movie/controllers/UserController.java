package com.jts.movie.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jts.movie.config.JWTService;
import com.jts.movie.entities.User;
import com.jts.movie.request.UserRequest;
import com.jts.movie.services.UserService;


import org.springframework.web.bind.annotation.GetMapping;
import com.jts.movie.response.ApiResponse;
@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	@GetMapping("/list")
	public List<User> list() {
		return userService.userlist();
	}
	
	@GetMapping("/profile")
	public ResponseEntity<ApiResponse<User>> profile(String emailId) {
		User user = userService.profile(emailId);
		try {
	        ApiResponse<User> response = new ApiResponse<>(
	                "success",
	                "User profile Found",
	                user
	            );

	       return new ResponseEntity<>(response, HttpStatus.CREATED);
	    } catch (Exception e) {
	        ApiResponse<User> response = new ApiResponse<>(
	                "failed",
	                e.getMessage(),
	                user
	            );
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	}
	
	@PostMapping("/addNew")
	public ResponseEntity<ApiResponse<User>> addNewUser(@RequestBody UserRequest userEntryDto) {
	    User user = userService.addUser(userEntryDto);
	    try {
	        ApiResponse<User> response = new ApiResponse<>(
	                "success",
	                "User Saved Successfully",
	                user
	            );

	       return new ResponseEntity<>(response, HttpStatus.CREATED);
	    } catch (Exception e) {
	        ApiResponse<User> response = new ApiResponse<>(
	                "failed",
	                e.getMessage(),
	                user
	            );
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}

	@PostMapping("/signin")
	public ApiResponse<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		System.out.println("Received AuthRequest: " + authRequest.getUsername());
		  
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		System.out.println("Authentication: " + authentication.isAuthenticated());
		
		if (authentication.isAuthenticated()) {	
			 ApiResponse<String> response = new ApiResponse<>(
		                "success",
		                "User profile Found",
		                jwtService.generateToken(authRequest.getUsername())
		            );
			return response;
		}
	   

		throw new UsernameNotFoundException("invalid user details.");
	}
	
	@PostMapping("/signup")
	public  ResponseEntity<ApiResponse<User>> signup(@RequestBody UserRequest userEntryDto) {
		return addNewUser(userEntryDto);
	}
	
}
