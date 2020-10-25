package com.cs490.onlineshopping.admin.service;

import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.admin.model.Vendor;
import com.cs490.onlineshopping.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

	public Optional<User> findById(int user_id) {
		return userRepository.findById(user_id);
	}

}