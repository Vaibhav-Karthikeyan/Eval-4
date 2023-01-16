package com.masai.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.app.entity.Mail;
import com.masai.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("Select password from User where Email=?1")
	public String validateUser(String username);
	
	@Query("from User where Email=?1")
	public User findById(String email);
	
	@Query("Select mails from User where Email=?1")
	public List<Mail> findEmail(String email);

	@Query("SELECT mails FROM User u JOIN u.mails m WHERE u.Email =?1 AND m.isStarred=?2")
	public List<Mail> findStarredEmail(String email, int no);
}