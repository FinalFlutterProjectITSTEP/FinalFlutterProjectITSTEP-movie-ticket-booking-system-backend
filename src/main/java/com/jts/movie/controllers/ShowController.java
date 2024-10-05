package com.jts.movie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jts.movie.entities.Show;
import com.jts.movie.entities.User;
import com.jts.movie.request.ShowRequest;
import com.jts.movie.request.ShowSeatRequest;
import com.jts.movie.services.ShowService;

@RestController
@RequestMapping("api/show")
public class ShowController {

	@Autowired
	private ShowService showService;

	@GetMapping("/list")
	public List<Show> list() {
		return showService.showlist();
	}
	
	@PostMapping("/addNew")
	public ResponseEntity<String> addShow(@RequestBody ShowRequest showRequest) {
		try {
			String result = showService.addShow(showRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/associateSeats")
	public ResponseEntity<String> associateShowSeats(@RequestBody ShowSeatRequest showSeatRequest) {
		try {
			String result = showService.associateShowSeats(showSeatRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
