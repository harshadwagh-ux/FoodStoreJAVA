package com.shashank.foodstore.pojo;

/*
 	Create table users(
 	userId int primary key auto_increment,
 	userName varchar(100),
 	userEmail varchar(30) unique key,
 	userPassword varchar(20),
 	userAddress varchar(100),
 	userRole varchar(10)
 	);  
 
 */

public class User 
{
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userAddress;
	private String userRole; //Admin /Customer
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String userEmail, String userPassword, String userAddress, String userRole) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.userRole = userRole;
	}
	public User(int userId, String userName, String userEmail, String userPassword, String userAddress,
			String userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.userRole = userRole;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userAddress=" + userAddress + ", userRole=" + userRole + "]";
	}
	
	
	
}
