package com.Harshad.foodstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Harshad.foodstore.dao.FoodDao;
import com.Harshad.foodstore.pojo.Food;
import com.Harshad.foodstore.utility.DBConnection;

public class FoodDaoImpl implements FoodDao
{
	Connection con;
	PreparedStatement  ps;
	ResultSet rs;
	
	String sqlQuery;
	

	@Override
	public boolean add(Food food) 
	{
		con = DBConnection.openConnection();
		
		sqlQuery="insert into food(foodName,foodPrice,foodType) value(?,?,?)";
		
		try
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1,food.getFoodName());
			ps.setDouble(2, food.getFoodPrice());
			ps.setString(3, food.getFoodType());
			
			int i = ps.executeUpdate();
			// execute the SQL query on the database.
			if(i>0)
			{
				System.out.println(i+"Rows inserted...");
				return true;
			}
			
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		finally 
		{
			DBConnection.closeConnection();
		}
		

		return false;
	}

	@Override
	public boolean update(int foodId, Food food) 
	{
		con = DBConnection.openConnection();	
		sqlQuery="update food set foodName=?, foodPrice=?, foodType=? where foodId=?";
		try
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1,food.getFoodName());
			ps.setDouble(2, food.getFoodPrice());
			ps.setString(3, food.getFoodType());
			ps.setInt(4, foodId);
			int i = ps.executeUpdate();
			// execute the SQL query on the database.
			if(i>0)
			{
				System.out.println(i+"Rows Updated...");
				return true;
			}	
		} 
		catch (SQLException e)
		{	
			e.printStackTrace();
		}
		finally 
		{
			DBConnection.closeConnection();
		}
		return false;
	}

	@Override
	public boolean delete(int foodId) 
	{
		con = DBConnection.openConnection();	
		sqlQuery="delete from food where foodId=?";
		try
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, foodId);
			int i = ps.executeUpdate();
			// execute the SQL query on the database.
			if(i>0)
			{
				System.out.println(i+"Rows Deleted...");
				return true;
			}	
		} 
		catch (SQLException e)
		{	
			e.printStackTrace();
		}
		finally 
		{
			DBConnection.closeConnection();
		}
		return false;
	}

	@Override
	public List<Food> all() 
	{
		List<Food> foodlist = new ArrayList<>();
		
		con  = DBConnection.openConnection();
		sqlQuery = "select * from food";
		try 
		{
			ps = con.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while(rs.next()) // it one by one row from ResultSet
			{
				Food f = new Food(rs.getInt("foodId"),
						rs.getString("foodName"),
						rs.getDouble("foodPrice"),
						rs.getString("foodType"));
				
				foodlist.add(f);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeConnection();
		}
		return foodlist;
	}

	@Override
	public Food getById(int foodId) 
	{
		con = DBConnection.openConnection();		
		sqlQuery="select * From food where foodId=?";		
		try 
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, foodId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				 Food f = new Food(rs.getInt("foodId"),
							rs.getString("foodName"),
							rs.getDouble("foodPrice"),
							rs.getString("foodType"));
				
				 return f;
			}
		} 
		catch (SQLException e)
		{	
			e.printStackTrace();
			
		}finally 
		{
			//DBConnection.closeConnection();
		}
		return null;
	}
	
	public static void main(String[] args) 
	{
		FoodDaoImpl fooddao =new FoodDaoImpl();
		
//Testing for insert
		/*
		Food f1 = new Food("Pizza", 250.75, "Nice Food.");
		fooddao.add(f1);
		
		Food f2 = new Food("Burger",175.85,"Testy Food");
		fooddao.add(f2);
		
		Food f3 = new Food("VadaPav",15.15,"Testy Food");
		fooddao.add(f3);
		*/
//Testing for update
		/*
		Food f3 = new Food("Burger", 275.55, "Testy Food"); 
		fooddao.update(2, f3);
		*/
//Testing for delete
		//fooddao.delete(2);
		
//Testing to all the data.
		/*
		List<Food> list = fooddao.all();
		System.out.println(list);
		*/
//Testing for to get single food by id

		Food f = fooddao.getById(2);
		System.out.println(f);
		Food f2 = fooddao.getById(3);
		System.out.println(f2);

	}

}
