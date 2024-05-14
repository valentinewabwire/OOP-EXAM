package com.shifu.storage;


import com.shifu.entities.User;

import java.util.List;

public interface UserStoringService {
	
	void saveUser(User user);
	
	List<User> loadUsers();
	
}
