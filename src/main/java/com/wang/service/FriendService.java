package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.bean.Friend;
import com.wang.dao.IFriendDAO;

@Service("FriendService")
public class FriendService{

	@Resource
	private IFriendDAO friendDAO;

	public void deleteFriend(String userId, String friendId){
		
		friendDAO.deleteFriend("friend_"+userId, friendId);
	}
	
	
	public List<Friend> getAllFriends(String id) {
		return friendDAO.getAllFriends("friend_"+id);
	}
	
	public void addFriend(String id, String friendId) {
		//TODO
		String friendImgeUri = new UserEntityService().getUser(friendId).getImageUri();
		friendDAO.addFriend("friend_"+id, friendId, friendImgeUri);
	}





}
