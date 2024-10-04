package com.jts.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jts.movie.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}

