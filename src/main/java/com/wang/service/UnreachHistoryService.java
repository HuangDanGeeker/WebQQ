package com.wang.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wang.DBConnection.MySqlConnection;
import com.wang.bean.Friend;
import com.wang.model.HistoryEntity;

public class UnreachHistoryService{

	
	public Map<String,List<HistoryEntity>> getUnreachHistory(String id){
		FriendService friendListService = new FriendService();
		List<Friend> friendList = friendListService.getAllFriend(id);
		Map<String, List<HistoryEntity>> map = new HashMap<String, List<HistoryEntity>>();
		for(Friend f : friendList){
			map.put(f.getFriendId(), new ArrayList<HistoryEntity>());
		}
		List<HistoryEntity> listOfHstryEntity = new ArrayList<HistoryEntity>();
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from history_" + id);
			while (resultSet.next()) {
				listOfHstryEntity = map.get(resultSet.getString(1));
				listOfHstryEntity.add(new HistoryEntity("1", resultSet.getString(2)));
				map.put(resultSet.getString(1), listOfHstryEntity);
            }
			statement.execute("delete from history_"+ id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return map;
	}
	
	
	
	public boolean addUnreachHistory(String srcId, String dstId, String content, String timestamp){
		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
		Connection connection = mySqlConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute("insert into history_" + dstId +" values('"+srcId+"', '"+content+"', '"+timestamp+"')");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
