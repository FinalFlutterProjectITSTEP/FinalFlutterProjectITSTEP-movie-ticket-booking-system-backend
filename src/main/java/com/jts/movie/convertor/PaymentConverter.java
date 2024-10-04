package com.jts.movie.convertor;

import java.util.Optional;
import java.util.stream.Collectors;

import com.jts.movie.entities.Payment;
import com.jts.movie.entities.Show;
import com.jts.movie.entities.Ticket;
import com.jts.movie.entities.User;
import com.jts.movie.enums.PaymentMethod;
import com.jts.movie.response.PaymentResponse;


public class PaymentConverter {
	 public static PaymentResponse returnPayment(Show show, User user) {
		 Integer totalPrice = show.getTicketList().stream()
                 .map(Ticket::getTotalTicketsPrice)   // Get the price of each ticket
                 .reduce(0, Integer::sum);
		 
		 String bookedSeats = show.getTicketList().stream()
				    .map(Ticket::getBookedSeats) // Get the bookedSeats from each Ticket
				    .collect(Collectors.joining(" ")); // Concatenate with ", " separator
		 
		 
		 PaymentMethod paymentMethod = show.getPaymentList().stream()
				    .map(Payment::getPaymentMethod) // Get the PaymentMethod from each Payment
				    .findFirst()
				    .orElse(null); // Default 
		 
		 PaymentResponse paymentResponseDto = PaymentResponse.builder()
	                .theaterName(show.getTheater().getName())
	                .address(show.getTheater().getAddress())
	                .bookedSeats(bookedSeats)
	                .paymentMethod(paymentMethod)
	                .movieName(show.getMovie().getMovieName())
	                .customerName(user.getName())
	                .mobileNo(user.getMobileNo())
	                .date(show.getDate())
	                .totalPrice(totalPrice)
	                .build();

	        return paymentResponseDto;
	    }
}
