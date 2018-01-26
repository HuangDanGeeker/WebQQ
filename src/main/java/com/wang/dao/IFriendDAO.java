package com.wang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wang.bean.Friend;

/**
 * @author SteakingCoder
 * @Description:  好友DAO
 */
/**
 * @author SteakingCoder
 * @Description: 
 */
public interface IFriendDAO {

	/**
	 * @Description: 获取指定friendId的好友信息
	 * @param dbName 好友信息存储表
	 * @param friendId 好友id
	 * @return Friend 好友对象 
	 * @author SteakingCoder
	 */
	public Friend getFriend(@Param("dbName")String dbName, @Param("friendId")String friendId);
	
	
	/**
	 * @Description: 
	 * @param dbName
	 * @return   List<Friend>  
	 * @author SteakingCoder
	 */
	public List<Friend> getAllFriends(@Param("dbName")String dbName);
	
	
	/**
	 * @Description: 删除id为friendId的好友
	 * @param dbName 
	 * @param friendId     
	 * @author SteakingCoder
	 */
	public void deleteFriend(@Param("dbName")String dbName, @Param("friendId")String friendId);
	
	/**
	 * @Description: 
	 * @param dbName
	 * @param friendId
	 * @param groupName     
	 * @author SteakingCoder
	 */
	public void addFriend(@Param("dbName")String dbName, @Param("friendId")String friendId, @Param("groupName")String groupName);
	
	
	/**
	 * @Description: 获取在线好友
	 * @param userId
	 * @return List<String> 在线好友id的List
	 * @author SteakingCoder
	 */
	public List<String> getAlivedFriends(@Param("userId")String userId);
}
