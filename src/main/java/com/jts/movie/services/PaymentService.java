package com.jts.movie.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jts.movie.convertor.UserConvertor;
import com.jts.movie.convertor.PaymentConverter;
import com.jts.movie.entities.Payment;
import com.jts.movie.entities.Show;
import com.jts.movie.entities.User;
import com.jts.movie.exceptions.ShowDoesNotExists;
import com.jts.movie.exceptions.UserDoesNotExists;
import com.jts.movie.exceptions.UserExist;
import com.jts.movie.repositories.PaymentRepository;
import com.jts.movie.repositories.ShowRepository;
import com.jts.movie.repositories.UserRepository;
import com.jts.movie.request.PaymentRequest;
import com.jts.movie.request.UserRequest;
import com.jts.movie.response.PaymentResponse;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private UserRepository userRepository;

	public PaymentResponse addPayment(PaymentRequest paymentRequest) {
	  
		Optional<Show> showOpt = showRepository.findById(paymentRequest.getShowId());

		if (showOpt.isEmpty()) {
			throw new ShowDoesNotExists();
		}

		Optional<User> userOpt = userRepository.findById(paymentRequest.getUserId());

		if (userOpt.isEmpty()) {
			throw new UserDoesNotExists();
		}

		User user = userOpt.get();
		Show show = showOpt.get();

		
	    Payment newPayment = new Payment();
	    newPayment.setDate(paymentRequest.getDate());
	    newPayment.setPaymentMethod(paymentRequest.getPaymentMethod());
	    newPayment.setShow(show);
	    newPayment.setUser(user);
	    
	    paymentRepository.save(newPayment);


	    return PaymentConverter.returnPayment(show, user);
	}
}
