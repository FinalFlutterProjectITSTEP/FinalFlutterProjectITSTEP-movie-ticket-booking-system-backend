package com.jts.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jts.movie.entities.Payment;
import com.jts.movie.entities.User;
import com.jts.movie.request.PaymentRequest;
import com.jts.movie.response.ApiResponse;
import com.jts.movie.response.PaymentResponse;
import com.jts.movie.services.PaymentService;


@RestController
@RequestMapping("api/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	
	@PostMapping("/addNew")
	public PaymentResponse addNewPayment(@RequestBody PaymentRequest PaymentEntryDto) {
	    PaymentResponse payment = paymentService.addPayment(PaymentEntryDto);
	
	    try {
	        ApiResponse<PaymentResponse> response = new ApiResponse<>(
	                "success",
	                "User Saved Successfully",
	                payment
	            );

	       return payment;
	    } catch (Exception e) {
	        ApiResponse<PaymentResponse> response = new ApiResponse<>(
	                "failed",
	                e.getMessage(),
	                payment
	            );
	        return payment;
	    }
	}
}
