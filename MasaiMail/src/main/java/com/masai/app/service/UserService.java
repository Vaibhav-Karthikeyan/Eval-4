package com.masai.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.entity.Mail;
import com.masai.app.entity.User;
import com.masai.app.repository.MailRepository;
import com.masai.app.repository.UserRepository;
import com.masai.app.request.Login;

@Service
public class UserService {
	@Autowired
	UserRepository rep;
	
	@Autowired
	MailRepository mail;
	
	public User newUser(User user) {
		return rep.save(user);
	}
	
	public String postUser(Login login) {
		String password=rep.validateUser(login.getUserName());
		System.out.println(password+' '+login.getPassword());
		if (password.equals(login.getPassword()))
			return "Logged in";
		else
			return "Incorrect username or password";
	}
	public User updateUser(User user) {
		return rep.save(user);
	}
	
	public List<Mail> findEmail(String email) {
		return rep.findEmail(email);
	}
	
	public List<Mail> findStarredEmail(String email) {
		return rep.findStarredEmail(email,1);
	}
	
	public User findUser(String email) {
		return rep.findById(email);
	}
	public Mail sendMail(String email1,String email2,Mail mail) {
		User u1=findUser(email1);
		User u2=findUser(email2);
		u1.getMailTo().add(u2);
		u2.setMailFrom(u1);
		u2.getMails().add(mail);
		mail.setUser(u2);
		rep.save(u1);
		rep.save(u2);
		return mail;
	}
	
	public Mail makeStarred(int id) {
		Mail m=mail.findById(id).get();
		m.setIsStarred(1);
		return mail.save(m);
	}
	
	public Boolean deleteMail(int id) {
		mail.deleteById(id);
		return true;
	}
}
