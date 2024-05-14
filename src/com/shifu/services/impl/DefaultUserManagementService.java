package com.shifu.services.impl;

import com.shifu.entities.User;
import com.shifu.entities.impl.DefaultUser;
import com.shifu.services.UserManagementService;
import com.shifu.storage.impl.DefaultUserStoringService;

import java.util.Collections;
import java.util.List;

public class DefaultUserManagementService implements UserManagementService {

	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";

	private static DefaultUserManagementService instance;
	private static DefaultUserStoringService defaultUserStoringService;

	static {
		defaultUserStoringService = DefaultUserStoringService.getInstance();
	}

	private DefaultUserManagementService() {
	}
	
	@Override
	public String registerUser(User user) {
		if (user == null){
			return NO_ERROR_MESSAGE;
		}

		String errorMessage = checkUniqueEmail(user.getEmail());
		if (errorMessage != null && !errorMessage.isEmpty()) {
			return errorMessage;
		}

		defaultUserStoringService.saveUser(user);
		return NO_ERROR_MESSAGE;
	}

	private String checkUniqueEmail(String email) {
		List<User> users = defaultUserStoringService.loadUsers();
		if (email == null || email.isEmpty()) {
			return EMPTY_EMAIL_ERROR_MESSAGE;
		}
		for (User user : users) {
			if (user != null &&
					user.getEmail() != null &&
					user.getEmail().equalsIgnoreCase(email)) {
				return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
			}
		}
		return NO_ERROR_MESSAGE;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	
	@Override
	public List<User> getUsers() {
		List<User> users = defaultUserStoringService.loadUsers();
		if (users == null || users.isEmpty()) {
			// Handle the case where users are not loaded successfully
			// For example, log an error message or throw an exception
			System.err.println("Failed to load users.");
			return Collections.emptyList(); // Return an empty list or handle the error appropriately
		}

		// Set the counter in a single pass
		int maxId = users.stream()
				.mapToInt(User::getId)
				.max()
				.orElseThrow(() -> new IllegalStateException("No users found")); // Throw an exception if no users are found

		DefaultUser.setCounter(maxId);

		return users;
	}


	@Override
	public User getUserByEmail(String userEmail) {
		for (User user : defaultUserStoringService.loadUsers()) {
			if (user != null && user.getEmail().equalsIgnoreCase(userEmail)) {
				return user;
			}
		}
		return null;
	}
	
	void clearServiceState() {
		// <write your code here>
	}
}
