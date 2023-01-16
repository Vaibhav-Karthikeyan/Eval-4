package com.masai.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.entity.Mail;
import com.masai.app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/masaimail")
public class MailController {
	@Autowired
	UserService ser;
	
	@PostMapping("/mail/{mail1}/{mail2}")
	public ResponseEntity<?> newUser(@Valid @RequestBody Mail mail,@PathVariable String mail1,@PathVariable String mail2) {
		return new ResponseEntity<Mail>(ser.sendMail(mail1, mail2, mail), HttpStatus.OK);
	}
	
	@PostMapping("/starred/{id}")
	public ResponseEntity<?> starMail(@PathVariable int id) {
		return new ResponseEntity<Mail>(ser.makeStarred(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMail(@PathVariable int id) {
		return new ResponseEntity<Boolean>(ser.deleteMail(id), HttpStatus.OK);
	}

}
