package com.shashank.foodstore.main;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.shashank.foodstore.dao.CartDao;
import com.shashank.foodstore.dao.FoodDao;
import com.shashank.foodstore.dao.UserDao;
import com.shashank.foodstore.dao.impl.CartDaoImpl;
import com.shashank.foodstore.dao.impl.FoodDaoImpl;
import com.shashank.foodstore.dao.impl.UserDaoImpl;
import com.shashank.foodstore.pojo.Cart;
import com.shashank.foodstore.pojo.Food;
import com.shashank.foodstore.pojo.User;

public class FoodStoreMainClass
{

	public static void main(String[] args) 
	{
		String repeat;
		FoodDao fooddao = new FoodDaoImpl();
		UserDao userdao = new UserDaoImpl();
		CartDao cartdao = new CartDaoImpl();
		
		Cart cart = null;
		Food food = null;
		User user = null;
		
		Scanner sc = new Scanner(System.in);
		int choice;
		String name,email,pass,role,address;

		List<Food> foodlist;
		List<Cart> cartlist;
		
		boolean flag;
		
		System.out.println("----------------Welcome To FoodStore----------------");
		System.out.println("1.Login ");
		System.out.println("2.Registration");
		System.out.println("Select Your Choice ? ");
		choice = sc.nextInt();
		switch(choice)
		{
			case 1:
				System.out.println("Login Here");
				System.out.println("Email Id:- ");
				email = sc.next();
				System.out.println("Passsword:- ");
				pass = sc.next();
				user = userdao.login(email, pass);
				if(user!=null) 
				{

					System.out.println("Hiii "+user.getUserName()+"...\n\n");
					
					System.out.println("____________________Food Menu____________________");
					foodlist = fooddao.all();
					if(!foodlist.isEmpty())
					{
						for(Food f : foodlist) 
						{
							System.out.println(f);
							System.out.println("----------------------------------------------------------------");
						}
						System.out.println("___________________________________________________________________");
					}else {
						System.out.println("No Food Found..");
					}
					
					if(user.getUserRole().equals("Admin"))
					{
						do 
						{
							System.out.println("Welcome to Admin Panel");
							adminMenu();
							
							
							System.out.println("Do want to repeat press y or Y");
							repeat = sc.next();
							
							System.out.println("____________________Food Menu____________________");
							foodlist = fooddao.all();
							if(!foodlist.isEmpty())
							{
								for(Food f : foodlist) 
								{
									System.out.println(f);
									System.out.println("----------------------------------------------------------------");
								}
								System.out.println("___________________________________________________________________");
							}else {
								System.out.println("No Food Found..");
							}
							
						}while(repeat.equalsIgnoreCase("y"));
						
						
					}
					else if (user.getUserRole().equals("Customer"))
					{
						
						System.out.println("Welcome to Customer Panel");
						do {
						customerMenu(user);
						
						
						System.out.println("Do want to repeat press y or Y");
						repeat = sc.next();
						}while(repeat.equalsIgnoreCase("y"));
						
					}
					
				}else 
				{
					System.out.println("Invalid Email Id or Password.");
				}
				break;
			case 2:
				System.out.println("Please Enter Your Data for Customer  Registraton");
				System.out.println("Enter Customer Full Name:- ");
				sc.nextLine();
				name = sc.nextLine();
				System.out.println("Enter Customer Email:- ");
				email = sc.next();
				System.out.println("Enter Customer Password:- ");
				pass = sc.next();
				System.out.println("Enter Customer Address:- ");
				sc.nextLine();
				address = sc.nextLine();
				user = new User(name,email,pass,address,"Customer");
				flag = userdao.register(user);
				if(flag==true)
				{
					System.out.println("Customer Registration is Successfully.");
				}else
				{
					System.out.println("Registration is Unsuccessfully.... please try again");
				}
				break;
			default:
				System.out.println("Invalid Choice...");
				break;
		}
		

	}// main method ended.
	
	// i need to method directly without object
	public static void adminMenu() 
	{
		int choice ;
		int foodid;
		Scanner sc = new Scanner(System.in);
		
		String foodName,foodDescription;
		double foodPrice;
		
		String name,email,pass,address;
		
		Food food;
		FoodDao fooddao = new FoodDaoImpl();
		boolean flag;
		
		
		User user ;
		UserDao userdao = new UserDaoImpl();
		
		System.out.println("1.Add Food ");
		System.out.println("2.Update Food ");
		System.out.println("3.Delete Food ");
		System.out.println("4.Add Admin ");
		System.out.println("Select you choice ");
		choice  = sc.nextInt();
		switch(choice)
		{
			case 1: 
				System.out.println("Enter Food Data");
				System.out.println("Name:- ");
				sc.nextLine();
				foodName= sc.nextLine();
				System.out.println("Price:- ");
				foodPrice= sc.nextDouble();
				System.out.println("Description :- ");
				sc.nextLine();
				foodDescription= sc.nextLine();
				
				food = new Food(foodName, foodPrice, foodDescription);
				flag = fooddao.add(food);
				if(flag)
				{
					System.out.println("Food is Added Successfully....");
				}else{
					System.out.println("Food is Not Added Try again....");
				}
				break;
			case 2:
				System.out.println("Enter Food id  to udpate ");
				foodid = sc.nextInt();
				
				food = fooddao.getById(foodid);
				if(food!=null) 
				{
					System.out.println("Old Food data");
					System.out.println(food);
					System.out.println("Enter Food Data for update ");
					System.out.println("Name:- ");
					sc.nextLine();
					foodName= sc.nextLine();
					System.out.println("Price:- ");
					foodPrice= sc.nextDouble();
					System.out.println("Description :- ");
					sc.nextLine();
					foodDescription= sc.nextLine();
					
					food = new Food(foodName, foodPrice, foodDescription);
					
					flag = fooddao.update(foodid, food);
					if(flag) {
						System.out.println("Food is Updated.");
					}
					else {
						System.out.println("Food is Not Updated..");
					}
				}else {
					System.out.println("Food is Not Found...");
					
				}
					break;
			case 3:
				//for Delete food
				break;
			case 4:
				System.out.println("Please Enter Your Data for Admin  Registraton");
				System.out.println("Enter Admin Full Name:- ");
				sc.nextLine();
				name = sc.nextLine();
				System.out.println("Enter Admin Email:- ");
				email = sc.next();
				System.out.println("Enter Admin Password:- ");
				pass = sc.next();
				System.out.println("Enter Admin Address:- ");
				sc.nextLine();
				address = sc.nextLine();
				user = new User(name,email,pass,address,"Admin");
				flag = userdao.register(user);
				if(flag==true)
				{
					System.out.println("Admin Registration is Successfully.");
				}else
				{
					System.out.println("Registration is Unsuccessfully.... please try again");
				}
				break;
				
		}
				
		
	}
	
	public static void customerMenu(User user)
	{
		int choice ;
		int foodId;
		int quantity;
		Food food;
		Cart cart;
		Scanner sc = new Scanner(System.in);
		
		FoodDaoImpl fooddao = new FoodDaoImpl();
		CartDaoImpl cartdao = new CartDaoImpl();
		
		List<Cart> cartlist;
		boolean flag;
		
		System.out.println("1.Add Food To Cart");
		System.out.println("2.View Cart");
		System.out.println("3.Clear Cart");
		System.out.println("4.Remove Item Form Cart");
		System.out.println("5.Update Profile");
		System.out.println("6.Delete Profile");
		
		System.out.println("Select your Choice ");
		choice = sc.nextInt();
		
		switch(choice)
		{
			case 1:
				System.out.println("Enter a food id Which orderd");
				foodId = sc.nextInt();
				food = fooddao.getById(foodId);
				System.out.println(food);
				System.out.println("Enter the Quantity of food ");
				quantity = sc.nextInt();
				
				cart = new Cart(foodId,quantity,user.getUserId());
				
				flag = cartdao.addToCart(cart);
				if(flag)
				{
					System.out.println("Food is added into Cart");
				}else {
					System.out.println("Food is Not added..");
				}
				break;
	
			case 2:
				cartlist = cartdao.viewCart(user.getUserId());
				System.out.println("-------------Cart Item List---------------");
				if(cartlist!=null && !cartlist.isEmpty())
				{
					for (Cart c : cartlist) {
						System.out.println(c);
					}
					
				}else {
					System.out.println("Cart is Empty...");
				}
				break;
			case 3:
				cartdao.clearCart(user.getUserId());
				System.out.println("Dear Customer "+user.getUserName());
				System.out.println("Your cart has been Cleared Successfully... ");
				break;
		
		}
		
	}
	
}
