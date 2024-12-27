package com.shashank.foodstore.dao;

import com.Harshad.foodstore.pojo.User;

public interface UserDao 
{
	boolean register(User user);
	User login(String userEmail,String userPassword);
	// if Email and password is match then return user data otherwise null;
	void updateProfile(int userId,User user);
	boolean deleteProfile(int userId);
}
