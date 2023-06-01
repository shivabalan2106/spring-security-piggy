package com.piggybank.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="user_details")
public class PiggyUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_details_seq", allocationSize = 1)
    @Column(name = "user_id")
	private Long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	private String password;
	
	

	public PiggyUser() {
	}

	public PiggyUser(String userName, String mobileNumber, String password) {
		super();
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
