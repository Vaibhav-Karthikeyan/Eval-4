package com.masai.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.app.entity.Mail;

public interface MailRepository extends JpaRepository<Mail,Integer> {

}
