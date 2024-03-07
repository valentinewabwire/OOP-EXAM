package com.shifu.menu.impl;

import com.shifu.configs.ApplicationContext;
import com.shifu.entities.User;
import com.shifu.menu.Menu;
import com.shifu.services.UserManagementService;
import com.shifu.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please, enter your email: ");
		String userEmail = sc.next();

		System.out.println("Please, enter password: ");
		String userPassword = sc.next();

		User user = userManagementService.getUserByEmail(userEmail);
		if (user != null && user.getPassword().equals(userPassword)){
			System.out.printf("Glad to see you back %s %s",user.getFirstName(),user.getLastName() + System.lineSeparator());
			context.setLoggedInUser(user);
		}else{
			System.out.println("Unfortunate such log in user or password doesn't exist");
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** Sign In *****");
	}

}
