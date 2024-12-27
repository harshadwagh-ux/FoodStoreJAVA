package com.shashank.foodstore.pojo;
/*
 	create database foodstore;
 	
 	use foodstore
 
 	Create table food(
 	foodId int primary key auto_increment,
 	foodName varchar(30)
 	foodPrice double(15,2),
 	foodType varchar(10)
 	);
 	
 */

public class Food 
{
	private int foodId;
	private String foodName;
	private double foodPrice;
	private String foodType;
	
	//Use to create Empty 
	public Food() {
	}
	
	// Create a Food object without Id 
	public Food(String foodName, double foodPrice, String foodType) {
		super();
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodType = foodType;
	}
	
	// To Create Food object with all details(i.e with id)
	public Food(int foodId, String foodName, double foodPrice, String foodType) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodType = foodType;
	}

	// getters and Setter 
	// It used access private data members outside class
	
	
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public double getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", foodName=" + foodName + ", foodPrice=" + foodPrice + ", foodType="
				+ foodType + "]";
	}
	
	
	
}
