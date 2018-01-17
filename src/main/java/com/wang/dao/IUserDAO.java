package com.wang.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wang.bean.User;

public interface IUserDAO{

	public User getUser(@Param("id")String id);
	
	public String getUserImgUri(@Param("id")String id);
	
	public void createRecordTable(@Param("dbName")String dbName);
	
	public void addFriend(@Param("dbName")String dbName, @Param("friendId")String friendId, @Param("friendImgUri")String friendImgUri);

	public boolean deleteUser(@Param("id")String id);

	public List<User> getAllUsers();

	public void applyAccount(@Param("userId")String userId);
	public String requireAccount();
	//TODO 原始applyAccount逻辑
	//requireAccount
	//applyAccount
		
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from user_set where flag=0 limit 0,1");
//			if(resultSet.next()){
//				userId = resultSet.getString(1);
//				System.out.println("queryed userID " + userId);
//				statement.execute("update user_set set flag=1 where id='"+userId+"'");
//				//添加用户记录
//				statement.execute("insert into user values('"+userId+"', 'http://localhost:8080/SpringMVC/images/defaultIcon.jpg', 'woman', 0, 'signature', null);");
//				//添加未读消息记录表
//				statement.executeUpdate("create table history_"+userId+"(friendId varchar(20), content varchar(512), timestamp datetime) character set = utf8;");
//				//添加好友记录表
//				statement.executeUpdate("create table friend_"+userId+"(friendId varchar(20) DEFAULT NULL, friendImgUri varchar(200) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8");
	
	public boolean updateUser(@Param("user")User user);
	


}
