package com.shashank.foodstore.dao;

import java.util.List;

import com.shashank.foodstore.pojo.Cart;

public interface CartDao 
{
	boolean addToCart(Cart cart);
	boolean removeItem(int itemId);
	boolean clearCart(int userId);
	List<Cart> viewCart(int userId);
}
