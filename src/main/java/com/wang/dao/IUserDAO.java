package com.wang.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wang.bean.User;

public interface IUserDAO{

	public User getUser(@Param("id")String id);
	
	public String getUserImgUri(@Param("id")String id);
	
	public void createRecordTable(@Param("dbName")String dbName);
	
	public void addFriend(@Param("dbName")String dbName, @Param("friendId")String friendId, @Param("groupName")String groupName);

	public boolean deleteUser(@Param("id")String id);
	
	public void deleteFriend(@Param("dbName")String dbName, @Param("friendId")String friendId);
	
	public List<User> getAllUsers();

	public void applyAccount(@Param("userId")String userId);
	
	public String requireAccount();
	
	public boolean updateUser(@Param("user")User user);
	
	public Boolean isAlive(@Param("userId")String userId);
	
	public void setAlive(@Param("userId")String userId, @Param("isAlive")boolean isAlive);

}
