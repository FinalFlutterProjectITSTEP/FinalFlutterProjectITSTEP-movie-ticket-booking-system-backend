package com.jts.movie.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


import com.jts.movie.entities.Show;
import com.jts.movie.entities.User;
import com.jts.movie.enums.PaymentMethod;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private Date date;
    private PaymentMethod paymentMethod;
    private String theaterName;
    private String movieName;
    private String customerName;
    private String mobileNo;
    private String address;
    private String bookedSeats;
    private Integer totalPrice;
   
}
