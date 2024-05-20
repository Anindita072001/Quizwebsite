package com.webquiz.dto;

import java.util.List;

public class UserDTO {

	private Integer userId;
	private String email;
	private String password;
	private String phoneNo;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", email=" + email + ", password=" + password + ", phoneNo=" + phoneNo
				+ "]";
	}
	
}
