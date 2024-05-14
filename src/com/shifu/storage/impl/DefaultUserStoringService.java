package com.shifu.storage.impl;

import com.shifu.entities.User;
import com.shifu.entities.impl.DefaultUser;
import com.shifu.storage.UserStoringService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DefaultUserStoringService implements UserStoringService {
	private static final String USER_INFO_STORAGE = "users.csv";
	private static final String CURRENT_TASK_RESOURCE_FOLDER = "finaltask";
	private static final String RESOURCES_FOLDER = "resources";
	private static final int USER_EMAIL_INDEX = 4;
	private static final int USER_PASSWORD_INDEX = 3;
	private static final int USER_LASTNAME_INDEX = 2;
	private static final int USER_FIRSTNAME_INDEX = 1;
	private static final int USER_ID_INDEX = 0;

	private static DefaultUserStoringService instance;
	@Override
	public void saveUser(User user) {
		try{
			File directory = new File(RESOURCES_FOLDER);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			File fullPath = new File(directory, CURRENT_TASK_RESOURCE_FOLDER + File.separator + USER_INFO_STORAGE);
			if (!fullPath.getParentFile().exists()) {
				fullPath.getParentFile().mkdirs(); // Creates the parent directory if it doesn't exist
			}

			if (!fullPath.exists()) {
				fullPath.createNewFile();
			}

			// Now, use the Path object to get the OutputStream
			Path outputPath = Paths.get(fullPath.getAbsolutePath());
			OutputStream outputStream = Files.newOutputStream(outputPath);

			// Assuming you have a way to convert your User object to bytes
			String firstName = null;
			String lastName = null;
			String password = null;
			String email = null;
			DefaultUser userSave = new DefaultUser(firstName,lastName,password,email);
			byte[] userData = user.toString().getBytes();
			//byte[] userData = user.toByteArray(); //new byte[0];//user.toByteArray(); // This is just a placeholder, replace with actual conversion logic
			outputStream.write(userData);
			outputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
//		try {
//			InputStream inputStream = getClass().getResourceAsStream("/finaltask/users.csv");
//			OutputStream outputStream = Files.newOutputStream(Paths.get(RESOURCES_FOLDER, CURRENT_TASK_RESOURCE_FOLDER, USER_INFO_STORAGE));
//			outputStream.write(inputStream.readAllBytes());
//			outputStream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	private String convertToStorableString(User user) {
		return user.getId() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getPassword() + ","
				+ user.getEmail();
	}

	@Override
	public List<User> loadUsers() {
		List<User> users = new ArrayList<>();
		try {
			String fullPath = Paths.get(RESOURCES_FOLDER, CURRENT_TASK_RESOURCE_FOLDER, USER_INFO_STORAGE).toString();
			System.out.println("Attempting to load users from: " + fullPath);

			try (Stream<String> stream = Files.lines(Paths.get(fullPath))) {
				users = stream.filter(Objects::nonNull)
						.filter(line ->((String) line).isEmpty()) // Cast line to String before calling isEmpty()
						.map(line -> {
							// Split the line by spaces to get individual fields
							String[] userFields = line.split("\\s+");
							// Parse the second element as an integer (assuming it's the user ID after "ID:")
							int userId = Integer.parseInt(userFields[1]);
							// Construct the rest of the user object using the remaining fields
							return new DefaultUser(userId,
									userFields[2], // First Name
									userFields[3], // Last Name
									userFields[4], // Email
									userFields[5]); // Password - consider hashing passwords
						}).collect(Collectors.toList());
			}


		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to load users: " + e.getMessage());
		}
		return users;
	}

	public static DefaultUserStoringService getInstance() {
		if (instance == null) {
			instance = new DefaultUserStoringService();
		}
		return instance;
	}
}
