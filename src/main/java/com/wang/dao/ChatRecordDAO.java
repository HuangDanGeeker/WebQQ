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
import org.springframework.orm.hibernate4.HibernateCallback;

import com.wang.DBConnection.MySqlConnection;
import com.wang.bean.ChatRecordItem;

public class ChatRecordDAO extends BaseDAO{

	public List<ChatRecordItem> getItems(String sql){
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		List<ChatRecordItem> list = new ArrayList<ChatRecordItem>();
		ChatRecordItem item = new ChatRecordItem();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
                //user.setId(resultSet.getString(1));
				item.setSrcId(resultSet.getString(1));
				item.setDstId(resultSet.getString(2));
				item.setContent(resultSet.getString(3));
				item.setTimeStamp(resultSet.getDate(4).toString());
				list.add(item);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addItem(String sql){
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
