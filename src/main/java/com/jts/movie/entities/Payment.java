package com.jts.movie.entities;
import java.sql.Date;

import com.jts.movie.enums.PaymentMethod;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "PAYMENTS")
@Data

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn
    private Show show;

    private Date date;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @ManyToOne
	@JoinColumn
	private User user;
}
