package com.shashank.foodstore.dao;

import java.util.List;

import com.shashank.foodstore.pojo.Food;

public interface FoodDao 
{
	boolean add(Food food);
	/*
	 	here boolean to single that respected Food is Add or not 
	 	if added then return true otherwise return false.
	*/
	boolean update(int foodId,Food food);
	boolean delete(int foodId);
	List<Food> all();
	Food getById(int foodId);
	
}
// All these method are abstract ie. it empty method
// to implement them we another class which name FoodDaoImpl
