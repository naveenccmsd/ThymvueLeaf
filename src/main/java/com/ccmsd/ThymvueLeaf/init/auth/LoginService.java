package com.ccmsd.ThymvueLeaf.init.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private UserDao userDao;

	public int getUserDetails(String username) {
		return userDao.getUserDetails(username);
	}
	public void createUserDetails(User user) {
		 userDao.createUserDetails(user);
	}
	
}
