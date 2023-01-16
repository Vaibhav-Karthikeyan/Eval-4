package com.masai.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","mailFrom"})
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String Email;
	@Pattern(regexp="^[A-Z][a-z]*",message = "{firstName.invalid}")
	private String firstName;
	@Pattern(regexp="^[A-Z][a-z]*",message = "{lastName.invalid}")
	private String lastName;
	@Size(min=10,max=10,message = "{mobileNumber.invalid}")
	private String mobileNumber;
	@Past
	private LocalDate dateOfBirth;
	@Pattern(regexp="^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{6,12}$",message = "{password.invalid}")
	private String password;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private List<Mail> mails=new ArrayList<>();
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="mailFrom_Id")
	private User mailFrom;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="mailFrom")
	private List<User> mailTo=new ArrayList<>();
}
