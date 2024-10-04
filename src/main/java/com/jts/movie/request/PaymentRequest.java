package com.jts.movie.request;

import java.sql.Date;
import com.jts.movie.enums.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequest {
	private Date date;
	private PaymentMethod paymentMethod;
    private Integer showId;
    private Integer userId;
}
