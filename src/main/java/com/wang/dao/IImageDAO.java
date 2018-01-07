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

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wang.DBConnection.MySqlConnection;
import com.wang.bean.ChatRecordItem;
import com.wang.model.IconImageModel;

public interface IImageDAO{
	
//	private String[] imageIds;
//	private String[] imageUris;
//	private List<IconImageModel> imageEntityList;
	
//TODO 原有的逻辑改变
//	public IImageDAO() {
//		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
//		Connection connection = mySqlConnection.getConnection();
//		imageEntityList = new ArrayList<IconImageModel>();
//		IconImageModel item = new IconImageModel();
//		
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from image");
//			while (resultSet.next()) {
//				item.setIconId(resultSet.getString(1));
//				item.setUri(resultSet.getString(2));
//				imageEntityList.add(item);
//				item = new IconImageModel();
//            }
//			
//			imageIds = new String[imageEntityList.size()];
//			imageUris = new String[imageEntityList.size()];
//			
//			for(int i = 0; i < imageEntityList.size(); i++){
//				item = imageEntityList.get(i);
//				imageIds[i] = item.getIconId();
//				imageUris[i] = item.getUri();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

	public boolean updateUserImage(String id, String imageUri);
	
	

	
	
}
