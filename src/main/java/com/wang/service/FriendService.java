package com.wang.service;

import java.util.List;

import com.wang.bean.Friend;
import com.wang.dao.FriendDAO;

public class FriendService implements IService{

	private FriendDAO friendDAO = new FriendDAO();
	


	@Override
	public boolean delete(String id) {
		//TEST
		//sql 正确性
		String sql = "drop table friend_" + id;
		//friendListDAO.opt(sql);
		return true;
	}

	public boolean deleteFriend(String id, String friendId){
		
		return friendDAO.deleteFriend(id, friendId);
	}
	
	
	public List<Friend> getAllFriend(String id) {
		//TEST
		//sql 正确性
		String sql = "select * from friend_" + id;
		List<Friend> friendList = friendDAO.getAllFriend(sql);
		return friendList;
	}
	
	public void add(String id, String friendId) {
		//TEST
		String imageUri = new UserEntityService().getUser(friendId).getImageUri();
		String sql = "insert into friend_" + id + "values(\""+friendId+"\",\""+imageUri+"\")";
		friendDAO.add(sql);
	}


	@Override
	public Object get(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
