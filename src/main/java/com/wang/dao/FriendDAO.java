package com.wang.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.sun.istack.internal.FinalArrayList;
import com.wang.DBConnection.MySqlConnection;
import com.wang.bean.Friend;


public class FriendDAO extends BaseDAO{

//	public <T> List<T> opt(final String sql) {
//		return  hibernateTemplate.execute(new HibernateCallback<List<T>>() {
//			@SuppressWarnings("unchecked")
//			public List<T> doInHibernate(Session session) throws HibernateException {
//				SQLQuery sqlQuery = session.createSQLQuery(sql);
//				return sqlQuery.list();
//			}
//			
//		});
//	} 
	
	public List<Friend> getAllFriend(final String sql) {
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		List<Friend> list  = new ArrayList<Friend>();
		Friend item = new Friend();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
//                user.setId(resultSet.getString(1));
				item.setFriendId(resultSet.getString(1));
				item.setFriendImgId(resultSet.getString(2));
				list.add(item);
				item = new Friend();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	public boolean deleteFriend(String id, String friendId){
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute("delete from friend_"+id+" where friendId='"+friendId+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void add(final String sql) {
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
}
