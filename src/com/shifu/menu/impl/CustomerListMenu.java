package com.shifu.menu.impl;


import com.shifu.configs.ApplicationContext;
import com.shifu.entities.User;
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
		printMenuHeader();

		User [] users = userManagementService.getUsers().toArray(new User[0]);

		if (users.length == 0) {
			System.out.println("There are no users");
		}else{
			for (User user : users) {
				System.out.println(user);
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** USERS *****");
	}

}
