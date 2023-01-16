package com.masai.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.entity.Mail;
import com.masai.app.entity.User;
import com.masai.app.request.Login;
import com.masai.app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/masaimail")
public class UserController {
	@Autowired
	UserService ser;

	@PostMapping("/register")
	public ResponseEntity<?> newUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(ser.newUser(user), HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody Login login) {
		return new ResponseEntity<String>(ser.postUser(login), HttpStatus.OK); 
	}
	@GetMapping("/{mailId}/mail")
	public ResponseEntity<?> allMail(@Valid @PathVariable String mailId) {
		return new ResponseEntity<List<Mail>>(ser.findEmail(mailId), HttpStatus.OK);
	}

	@GetMapping("/{mailId}/starred")
	public ResponseEntity<?> allStarredMail(@Valid @PathVariable String mailId) {
		return new ResponseEntity<List<Mail>>(ser.findStarredEmail(mailId), HttpStatus.OK);
	}

	@PutMapping("/{mailId}/user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(ser.updateUser(user), HttpStatus.CREATED);
	}
}
