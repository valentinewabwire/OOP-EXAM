package com.shifu.menu.impl;

import com.shifu.configs.ApplicationContext;
import com.shifu.menu.Menu;
import com.shifu.services.UserManagementService;
import com.shifu.services.impl.DefaultUserManagementService;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
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
