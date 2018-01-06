package com.wang.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.wang.DBConnection.MySqlConnection;
import com.wang.bean.User;
import com.wang.model.IconImageModel;
import com.wang.service.ImageService;

public class UserDAO extends BaseDAO implements IBaseDAO{

	
	
	@Override
	public User get(String id) {
		System.out.println("==> UserDAO : " + id);
		
		//User user = hibernateTemplate.get(User.class, id);
		User user = new User();
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user where id='" + id+"'");
			while (resultSet.next()) {
                user.setId(resultSet.getString(1));
                user.setImageUri(resultSet.getString(2));
                user.setSex(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setSignature(resultSet.getString(5));
                return user;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public boolean addFriend(String id, String friendId){
		
		String record_dbName = getDBName(id, friendId);
		String create_record_sql = "create table if not exists "+record_dbName+"(srcId varchar(20), dstId varchar(20), content varchar(512), timestamp Date,constraint foreign key (srcId) references User(id), foreign key (dstId) references User(id));";
		
		String imageUri_1 = this.get(id).getImageUri();
		String imageUri_2 = this.get(friendId).getImageUri();
		
		String add_friend_sql_1 = "insert into friend_"+id+" values('"+friendId+"', '"+imageUri_2+"');";
		String add_friend_sql_2 = "insert into friend_"+friendId+" values('"+id+"', '"+imageUri_1+"');";

		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute(create_record_sql);
			statement.execute(add_friend_sql_1);
			statement.execute(add_friend_sql_2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean delete(String id) {
		User user = new User();
		user.setId(id);
		hibernateTemplate.delete(user);
		return true;
	}

	public List<User> getAll() {
		
		return hibernateTemplate.loadAll(User.class);
	}

	public String applyAccount(){
		
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		
		try {
			String userId = new String();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user_set where flag=0 limit 0,1");
			if(resultSet.next()){
				userId = resultSet.getString(1);
				System.out.println("queryed userID " + userId);
				statement.execute("update user_set set flag=1 where id='"+userId+"'");
				//添加用户记录
				statement.execute("insert into user values('"+userId+"', 'http://localhost:8080/SpringMVC/images/defaultIcon.jpg', 'woman', 0, 'signature', null);");
				//添加未读消息记录表
				statement.executeUpdate("create table history_"+userId+"(friendId varchar(20), content varchar(512), timestamp datetime) character set = utf8;");
				//添加好友记录表
				statement.executeUpdate("create table friend_"+userId+"(friendId varchar(20) DEFAULT NULL, friendImgUri varchar(200) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8");
			}
			System.out.println("queryed userID success");
			return userId;
		} catch (SQLException e) {
			e.printStackTrace();
			return "no account available";
		}
		
	}
	
	public boolean update(User user){
		
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			System.out.println("update user set age="+user.getAge()+", sex='"+user.getSex()+"', signature='"+user.getSignature()+"' where id='"+user.getId()+"';");
			statement.executeUpdate("update user set age="+user.getAge()+", sex='"+user.getSex()+"', signature='"+user.getSignature()+"' where id='"+user.getId()+"';");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public <T> List<T> executeSql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	public String getDBName(String srcId, String dstId){
		String dbName;
		if(srcId.length() < dstId.length()){
			dbName = "record_" + dstId + "_" +srcId;
		}else if(srcId.length() < dstId.length()){
			dbName = "record_" + srcId + "_" + dstId;
		}else{
			
			if(srcId.compareTo(dstId) < 0){
				dbName = "record_" + dstId + "_" + srcId;
			}else{
				dbName = "record_" + srcId + "_" + dstId;
			}
		}
		System.out.println("DB_Name : " + dbName);
		return dbName;
	}

}
