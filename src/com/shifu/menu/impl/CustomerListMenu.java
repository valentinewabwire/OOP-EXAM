package com.shifu.menu.impl;


import com.shifu.configs.ApplicationContext;
import com.shifu.menu.Menu;
import com.shifu.services.impl.DefaultUserManagementService;
import com.shifu.services.UserManagementService;

public class CustomerListMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;
	
	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		// <write your code here>
	}

	@Override
	public void printMenuHeader() {
		// <write your code here>	
	}

}
