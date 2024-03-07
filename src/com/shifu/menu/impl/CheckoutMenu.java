package com.shifu.menu.impl;

import com.shifu.configs.ApplicationContext;
import com.shifu.entities.Order;
import com.shifu.entities.impl.DefaultOrder;
import com.shifu.menu.Menu;
import com.shifu.services.OrderManagementService;
import com.shifu.services.impl.DefaultOrderManagementService;

import java.util.Scanner;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;
	
	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}
	
	@Override
	public void start() {
		while(true){
			printMenuHeader();
			Scanner sc = new Scanner(System.in);
			String userInput = sc.next();
			if(!createOrder(userInput)){
				continue;
			}
			context.getSessionCart().clear();
			break;
		}
		System.out.println("Thanks alot for your order full details have been send to your email address");

	}

	private boolean createOrder(String creditCardNumber) {
		Order order = new DefaultOrder();
		if(!order.isCreditCardNumberValid(creditCardNumber)){
			return false;
		}
		order.setCreditCardNumber(creditCardNumber);
		order.setProducts(context.getSessionCart().getProducts());
		order.setCustomerId(context.getLoggedInUser().getId());
		orderManagementService.addOrder(order);
		return true;
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** CHECKOUT *****");
		System.out.print("Enter your credit card number without spaces and press enter to confirm your purchase: ");
	}

}
