package com.wang.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wang.bean.User;

/**
 * @author SteakingCoder
 * @Description: 用户DAO
 */
public interface IUserDAO{

	
	
	/**
	 * @Description: 返回指定id的用户个人信息
	 * @param id  用户id
	 * @return User对象
	 * @author SteakingCoder
	 */
	public User getUser(@Param("id")String id);
	
	
	
	/**
	 * @Description: 返回用户头像的uri
	 * @param id 用户id
	 * @return String
	 * @author SteakingCoder
	 */
	public String getUserImgUri(@Param("id")String id);
	
	
	
	/**
	 * @Description: 创建以dbName(recrod_id1_id2)为表名的聊天记录表
	 * @param dbName 聊天记录表名
	 * @author SteakingCoder
	 */
	public void createRecordTable(@Param("dbName")String dbName);
	
	
	
	/**
	 * @Description: 向dbName表中添加好友信息(friendId, groupName)
	 * @param dbName 表名
	 * @param friendId 要添加的好友id
	 * @param groupName 分组名
	 * @author SteakingCoder
	 */
	public void addFriend(@Param("dbName")String dbName, @Param("friendId")String friendId, @Param("groupName")String groupName);

	
	
	/**
	 * @Description: 删除在User表中指定id的用户信息
	 * @param id
	 * @return 
	 * @author SteakingCoder
	 */
	public boolean deleteUser(@Param("id")String id);
	
	
	
	/**
	 * @Description: 删除dbName表中的id为friendId的好友 
	 * @param dbName 指定的数据表名
	 * @param friendId 将要删除的好友id
	 * @author SteakingCoder
	 */
	public void deleteFriend(@Param("dbName")String dbName, @Param("friendId")String friendId);
	
	
	
	/**
	 * @Description: 获取所有的用户信息
	 * @return List<User>
	 * @author SteakingCoder
	 */
	@Deprecated
	public List<User> getAllUsers();

	
	
	/**
	 * @Description: 申请一个新的用户id
	 * @return String 申请到的用户id
	 * @author SteakingCoder
	 */
	public String requireAccount();
	
	
	
	/**
	 * @Description: 为userId初始化各种数据表
	 * @param userId 
	 * @author SteakingCoder
	 */
	public void applyAccount(@Param("userId")String userId);
	
	
	/**
	 * @Description: 更新用户的个人信息
	 * @param user User对象，包含最新的个人信息
	 * @return 
	 * @author SteakingCoder
	 */
	public boolean updateUser(@Param("user")User user);
	
	
	
	/**
	 * @Description: 返回用户的在线状态
	 * @param userId 用户id
	 * @return Boolean true代表在线,false代表下线
	 * @author SteakingCoder
	 */
	public Boolean isAlive(@Param("userId")String userId);
	
	
	/**
	 * @Description: 设置id为userId的上线状态
	 * @param userId 好友id
	 * @param isAlive boolean (true在线 false下线)
	 * @author SteakingCoder
	 */
	public void setAlive(@Param("userId")String userId, @Param("isAlive")boolean isAlive);
	
	
	
	/**
	 * @Description: 用户下线
	 * @param userId 用户id
	 * @author SteakingCoder
	 */
	public void logout(@Param("userId")String userId);

}
