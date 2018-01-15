package com.wang.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.bean.User;
import com.wang.dao.IImageDAO;
import com.wang.dao.IUserDAO;

@Service("userService")
public class UserService{

	@Resource
	private IUserDAO userDAO;
	
	@Resource
	private IImageDAO imageDAO;
	
	public User get(String id){
		return userDAO.getUser(id);
	}
	
	public boolean isExist(String id) {
		if(null != this.get(id))
			return true;
		return false;
	}
	
	public String applyAccount(){
		
		String account = userDAO.requireAccount();
		userDAO.applyAccount(account);
		return account;
	}
	
	public boolean update(User user){
		return userDAO.updateUser(user);
	}
	
	public boolean addFriend(String userId, String friendId){
		
		userDAO.createRecordTable(getDBName(userId, friendId));
		String userImgUri = userDAO.getUserImgUri(userId);
		String friendImgUri = userDAO.getUserImgUri(friendId);
		userDAO.addFriend("friend_"+userId, friendId, friendImgUri);
		userDAO.addFriend("friend_"+friendId, userId, userImgUri);
		
		return true;
	}
	
	public String getDBName(String id_1, String id_2){
		
		if(id_1.length() < id_2.length()){
			return id_2 +"_"+id_1;
		}else if(id_1.length() > id_2.length()){
			return id_1 +"_" + id_2;
		}else{
			int result = id_1.compareToIgnoreCase(id_2);
			if(result > 0)
				return id_1 +"_" + id_2;
			else
				return id_2 +"_"+id_1;
		}
	}
}
