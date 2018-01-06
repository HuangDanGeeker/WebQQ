package com.wang.service;

import com.wang.bean.User;
import com.wang.dao.UserDAO;

public class UserService {

	private UserDAO userDAO = new UserDAO();
	
	public User get(String id){
		return userDAO.get(id);
	}
	
	public boolean isExist(String id) {
		if(null != this.get(id))
			return true;
		return false;
	}
	
	public String applyAccount(){
		return userDAO.applyAccount();
	}
	
	public boolean update(User user){
		return userDAO.update(user);
	}
	
	public boolean addFriend(String id, String friendId){
		
		userDAO.addFriend(id, friendId);
		
		return true;
	}
	
	
}
