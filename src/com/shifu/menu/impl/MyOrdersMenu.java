package com.shifu.menu.impl;


import com.shifu.configs.ApplicationContext;
import com.shifu.entities.Order;
import com.shifu.menu.Menu;
import com.shifu.services.OrderManagementService;
import com.shifu.services.impl.DefaultOrderManagementService;

public class MyOrdersMenu implements Menu {

	private final ApplicationContext context;
	private final OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		if(context.getLoggedInUser() == null){
			System.out.println("Please, log in or create new account to see list of your orders");
			new MainMenu().start();
        }else{
			printUserOrdersToConsole();
		}
	}

	private void printUserOrdersToConsole(){
		Order[] loggedInUserOrders = orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());
		if(loggedInUserOrders == null || loggedInUserOrders.length == 0){
			System.out.println("Unfortunately, you don't have any orders yet. "
					+ "Navigate back to main menu to place a new order");
		}else{
			for(Order order : loggedInUserOrders){
				System.out.println(order);
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MY ORDERS *****");
	}

}
