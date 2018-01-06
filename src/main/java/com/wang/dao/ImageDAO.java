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

public class ImageDAO extends BaseDAO implements IBaseDAO{
	
	private String[] imageIds;
	private String[] imageUris;
	private List<IconImageModel> imageEntityList;
	
	public ImageDAO() {
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		imageEntityList = new ArrayList<IconImageModel>();
		IconImageModel item = new IconImageModel();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from image");
			while (resultSet.next()) {
				item.setIconId(resultSet.getString(1));
				item.setUri(resultSet.getString(2));
				imageEntityList.add(item);
				item = new IconImageModel();
            }
			
			imageIds = new String[imageEntityList.size()];
			imageUris = new String[imageEntityList.size()];
			
			for(int i = 0; i < imageEntityList.size(); i++){
				item = imageEntityList.get(i);
				imageIds[i] = item.getIconId();
				imageUris[i] = item.getUri();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean updateUserImage(String id, String imageUri){
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			Statement statement2 = connection.createStatement();
			statement.execute("update user set imageUri='"+imageUri+"' where id='"+id+"'");
			ResultSet resultSet = statement.executeQuery("select friendId from friend_"+id);
			String friendId = "";
			while(resultSet.next()){
				friendId = resultSet.getString(1);
				statement2.executeUpdate("update friend_"+friendId + " set friendImgUri='"+imageUri+"' where friendId='"+id+"';");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Deprecated
	@Override
	public Object get(String id) {
		return null;
	}

	@Override
	public boolean delete(String imageId) {
		
		return true;
	}

	@Override
	public List getAll() {
		
		return null;
	}
	
	public String[] getAllId(){

		return imageIds;
	}
	
	public String[] getAllUri(){
		
		return imageUris;
	}

	@Override
    public <T> List<T> executeSql(final String sql) {
		return null;
    }

	public String[] getImageIds() {
		return imageIds;
	}

	public void setImageIds(String[] imageIds) {
		this.imageIds = imageIds;
	}

	public String[] getImageUris() {
		return imageUris;
	}

	public void setImageUris(String[] imageUris) {
		this.imageUris = imageUris;
	}

	public List<IconImageModel> getImageEntityList() {
		return imageEntityList;
	}

	public void setImageEntityList(List<IconImageModel> imageEntityList) {
		this.imageEntityList = imageEntityList;
	} 

	
}
