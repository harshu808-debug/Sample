package com.mamathadigital.entities;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.mamathadigital.util.idgenerators.UserIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("deprecation")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_table")
public class User {
	
	@Id
	@GenericGenerator(name = "user_id_generator", type = UserIdGenerator.class)
	@GeneratedValue(generator = "user_id_generator")
	@Column(name = "id")
	private String userId;

	@Column(name = "name")
	private String userName;

	@Column(name = "registration_date")
	private Date userRegistrationDate;

	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "ACTIVE")
	private Boolean active;

	@Column(name = "email")
	private String userEmail;

	@Column(name = "password")
	private String userPassword;

	@Column(name = "profile_image")
	private String userProfileImage;

	@Column(name = "gender")
	private String userGender;

	@Column(name = "phone")
	private Long userPhone;
	
	@Column(name = "terms&condition")
	private Boolean termsAndCondition;

}
