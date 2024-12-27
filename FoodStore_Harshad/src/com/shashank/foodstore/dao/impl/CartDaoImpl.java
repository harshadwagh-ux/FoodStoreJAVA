package com.shashank.foodstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.shashank.foodstore.dao.CartDao;
import com.shashank.foodstore.dao.UserDao;
import com.shashank.foodstore.pojo.Cart;
import com.shashank.foodstore.pojo.Food;
import com.shashank.foodstore.pojo.User;
import com.shashank.foodstore.utility.DBConnection;

public class CartDaoImpl implements CartDao 
{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String sqlQuery;
	
	
	FoodDaoImpl fooddao = new FoodDaoImpl();

	@Override
	public boolean addToCart(Cart cart) 
	{
		con = DBConnection.openConnection();
		sqlQuery = "insert into cart (foodId,itemQuantity,userId) values(?,?,?)";
		try
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1,cart.getFoodId());
			ps.setInt(2, cart.getItemQuantity());
			ps.setInt(3, cart.getUserId());
			
			int i = ps.executeUpdate();
			
			if(i>0)
			{
				System.out.println(i+" Inserted...");
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
	public boolean removeItem(int itemId)
	{
		con = DBConnection.openConnection();
		sqlQuery="delete from cart where itemId=?";
		
		try
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, itemId);
			
			int i = ps.executeUpdate();
			if(i>0) {
				System.out.println(i+" Rows Deleted..");
				return true;
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection();
		}
		
		
		return false;
	}

	@Override
	public boolean clearCart(int userId) 
	{
		con = DBConnection.openConnection();
		sqlQuery="delete from cart where userId=?";
		try
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			
			int i = ps.executeUpdate();
			if(i>0) {
				System.out.println(i+" Rows Deleted..");
				return true;
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection();
		}
		
		
		return false;
	}

	@Override
	public List<Cart> viewCart(int userId) 
	{
		List<Cart> cartlist = new ArrayList<Cart>();
		con = DBConnection.openConnection();
		sqlQuery = "select * From cart where userId=?";
		try
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1,userId);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				Food food = fooddao.getById(rs.getInt("foodId"));
				
				Cart cart = new Cart();
				cart.setItemId(rs.getInt("itemId"));
				cart.setFoodId(rs.getInt("foodId"));
				cart.setFood(food);
				cart.setItemQuantity(rs.getInt("itemQuantity"));
				cart.setUserId(rs.getInt("userId"));
	
				cartlist.add(cart);
			}
			return cartlist;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			DBConnection.closeConnection();
		}
		return null;
	}
	
	
	public static void main(String[] args)
	{
//Add to cart Testing 
		UserDaoImpl userdao = new UserDaoImpl();
		CartDaoImpl cartdao = new CartDaoImpl();
		User u = userdao.login("jay@gmail.com", "123");
		/*
		Cart c = new Cart(2,3,u.getUserId());
		
		cartdao.addToCart(c);
		*/
//View Cart Testing 	
		List<Cart> cartlist = cartdao.viewCart(u.getUserId());
		for(Cart item:cartlist ) 
		{
			System.out.println(item);
		}
		
	
	}

}
