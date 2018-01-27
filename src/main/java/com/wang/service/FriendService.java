package com.wang.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wang.bean.Friend;
import com.wang.dao.IFriendDAO;

/**
 * @author SteakingCoder
 * @Description 向用户提供各种'好友相关'服务 
 */
@Service("friendService")
public class FriendService{

	/**
	 * spring注入的FriendDAO
	 */
	@Resource
	private IFriendDAO friendDAO;

	
	
	/**
	 * 删除用户userId下的id为friendId的好友
	 *
	 * @param userId 用户id 
	 * @param friendId 将要删除的好友id
	 * @author SteakingCoder
	 */
	public void deleteFriend(String userId, String friendId){
		friendDAO.deleteFriend("friend_"+userId, friendId);
	}
	
	
	
	
	/**
	 * 获取用户id的所有好友 
	 *
	 * @param id 用户id
	 * @return List<Friend>
	 * @author SteakingCoder
	 */
	public List<Friend> getAllFriends(String id) {
		return friendDAO.getAllFriends("friend_"+id);
	}
	
	
	
	/**
	 * 将friendId添加到用户id的groupName分组中
	 * 
	 * @param id 用户id
	 * @param friendId 好友id
	 * @param groupName 将好友添加到该分组中
	 * @author SteakingCoder
	 */
	public void addFriend(String id, String friendId, String groupName) {
		friendDAO.addFriend("friend_"+id, friendId, groupName);
	}

	
	
	/**
	 * @Description 返回userId在线的好友列表
	 * @param userId
	 * @return List<String>
	 * @author SteakingCoder
	 */
	public List<String> getAlivedFriends(String userId){
		return friendDAO.getAlivedFriends(userId);
	}



}
