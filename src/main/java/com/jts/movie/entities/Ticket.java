package com.jts.movie.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;

@Entity
@Table(name = "TICKETS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketId;

	private Integer totalTicketsPrice;

	private String bookedSeats;

	@CreationTimestamp
	private Date bookedAt;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Show show;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private User user;

}
