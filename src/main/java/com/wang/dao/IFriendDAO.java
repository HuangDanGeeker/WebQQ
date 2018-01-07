package com.wang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wang.bean.Friend;

public interface IFriendDAO {

	public Friend getFriend(@Param("dbName")String dbName, @Param("friendId")String friendId);
	public List<Friend> getAllFriends(@Param("dbName")String dbName);
	public boolean deleteFriend(@Param("dbName")String dbName, @Param("friendId")String friendId);
	public void addFriend(@Param("dbName")String dbName, @Param("friendId")String friendId, @Param("friendImgUri")String friendImgUri);
}
