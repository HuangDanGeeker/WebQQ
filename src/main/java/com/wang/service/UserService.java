package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.bean.Friend;
import com.wang.bean.User;
import com.wang.dao.IImageDAO;
import com.wang.dao.IUserDAO;

/**
 * @author SteakingCoder
 * @Description 用户服务Service
 */
@Service("userService")
public class UserService extends BasicService{

	/**
	 * Spring 注入的 UserDAO
	 */
	@Resource
	private IUserDAO userDAO;
	
	/**
	 * Spring 注入的ImageDAO
	 */
	@Resource
	private IImageDAO imageDAO;
	
	/**
	 * Spring 注入的FriendService
	 */
	@Resource
	private FriendService friendService;
	
	
	
	/**
	 * 返回指定id的用户User对象，包含该用户的个人信息
	 *
	 * @param id 用户id
	 * @return 
	 * @author SteakingCoder
	 */
	public User get(String id){
		return userDAO.getUser(id);
	}
	
	
	
	/**
	 * 返回用户头像的uri
	 *
	 * @param id 用户id
	 * @return String
	 * @author SteakingCoder
	 */
	public String getUserImgUri(String id){
		return userDAO.getUserImgUri(id);
	}
	
	
	
	/**
	 * 
	 *
	 * @param id
	 * @return 
	 * @author SteakingCoder
	 */
	//TODO 做逻辑修改
	public boolean isExist(String id) {
		if(null != this.get(id))
			return true;
		return false;
	}
	
	
	
	/**
	 * 申请用户
	 *
	 * @return String 申请到的用户id
	 * @author SteakingCoder
	 */
	public String applyAccount(){
		
		String account = userDAO.requireAccount();
		userDAO.applyAccount(account);
		return account;
	}
	
	
	
	/**
	 * 更新用户的个人信息 
	 *
	 * @param user User
	 * @return 
	 * @author SteakingCoder
	 */
	public boolean update(User user){
		return userDAO.updateUser(user);
	}
	
	
	
	/**
	 * 用户下线
	 *
	 * @param userId 下线用户的id
	 * @author SteakingCoder
	 */
	public void logout(String userId){
		userDAO.logout(userId);
	}
	
	
	
	/**
	 * 添加好友
	 *
	 * @param userId 用户id
	 * @param friendId 好友 id
	 * @param groupName 分组名
	 * @return 
	 * @author SteakingCoder
	 */
	public boolean addFriend(String userId, String friendId, String groupName){
		
		userDAO.createRecordTable("record_"+getDBName(userId, friendId));
//		String userImgUri = userDAO.getUserImgUri(userId);
//		String friendImgUri = userDAO.getUserImgUri(friendId);
		userDAO.addFriend("friend_"+userId, friendId, groupName);
		List<Friend> friendList = friendService.getAllFriends(friendId);
		for(Friend item : friendList){
			if(item.getFriendId().equals(userId)){
				userDAO.deleteUser(userId);
				userDAO.addFriend("friend_"+friendId, userId, "group");
				return true;
			}
		}
		userDAO.addFriend("friend_"+friendId, userId, "group");
		
		return true;
	}
	
	
	
	/**
	 * 返回用户id的在线状态
	 *
	 * @param userId
	 * @return true 用户在线;false 用户下线
	 * @author SteakingCoder
	 */
	public Boolean isAlive(String userId){
		Boolean isAlive = userDAO.isAlive(userId);
		isAlive = isAlive == null ? false : isAlive;
		return isAlive;
	}
	
	
	
	/**
	 * 设置用户的在线状态
	 *
	 * @param userId
	 * @param isAlive true用户在线,false用户下线 
	 * @author SteakingCoder
	 */
	public void setAlive(String userId, Boolean isAlive){
		userDAO.setAlive(userId, isAlive);
	}
	
}
