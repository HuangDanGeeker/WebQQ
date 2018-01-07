package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.bean.Friend;
import com.wang.dao.IFriendDAO;

@Service("myFriendService")
public class MyFriendService {

	@Resource
	public IFriendDAO friendDAO;
	
	public Friend getFriend(String userId,String friendId){
		return friendDAO.getFriend("friend_"+userId, friendId);
	}
	
	public List<Friend> getAllFriends(String userId){
		return friendDAO.getAllFriends("friend_"+userId);
	}
	
	public void deleteFriend(String userId,String friendId){
		friendDAO.deleteFriend("friend_"+userId, friendId);
	}
	
	public void addFriend(String userId, String friendId, String friendImgUri) {
		friendDAO.addFriend("friend_"+userId, friendId, friendImgUri);
	}
}
