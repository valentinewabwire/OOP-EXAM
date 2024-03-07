package com.shifu.menu.impl;

import com.shifu.configs.ApplicationContext;
import com.shifu.menu.Menu;

import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		context.getLoggedInUser().setEmail(userInput);
		System.out.println("Your email has been successfully changed");
	}

	@Override
	public void printMenuHeader() {
		System.out.println("******* CHANGE EMAIL *******");
		System.out.print("Enter New Email: ");
	}

}
