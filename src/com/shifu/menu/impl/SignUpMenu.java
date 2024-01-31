package com.shifu.menu.impl;


import com.shifu.configs.ApplicationContext;
import com.shifu.entities.User;
import com.shifu.entities.impl.DefaultUser;
import com.shifu.menu.Menu;
import com.shifu.services.UserManagementService;
import com.shifu.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {

	private UserManagementService userManagementService;
	private ApplicationContext context;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	/**
	 * The start() function prompts the user to enter their personal information, creates a new user
	 * object, and registers the user with the user management service.
	 */
	@Override
	public void start() {
		printMenuHeader();

		Scanner sc = new Scanner(System.in);
		System.out.print("Please, enter your first name: ");
		String firstName = sc.next();
		System.out.print("Please, enter your last name: ");
		String lastName = sc.next();
		System.out.print("Please, enter your password: ");
		String password = sc.next();
		System.out.print("Please, enter your email: ");

		sc = new Scanner(System.in);
		String email = sc.nextLine();

		User user = new DefaultUser(firstName,lastName,password,email);

		String errorMessage = userManagementService.registerUser(user);
		if (errorMessage == null || errorMessage.isEmpty()){
			context.setLoggedInUser(user);
			System.out.println("New User is created");
		}else {
			System.out.println(errorMessage);
		}

	}

	@Override
	public void printMenuHeader() {
		System.out.println("****** SIGN UP ******");
	}

}
