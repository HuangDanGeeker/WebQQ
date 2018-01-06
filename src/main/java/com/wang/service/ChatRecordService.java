package com.wang.service;

import java.util.List;

import com.wang.bean.ChatRecordItem;
import com.wang.dao.ChatRecordDAO;

public class ChatRecordService implements IService{

	ChatRecordDAO chatRecordDAO = new ChatRecordDAO();
	
	@Override
	public Object get(String id) {
		return null;
	}

	@Override
	public boolean delete(String id) {
		return false;
	}

	@Override
	public List getAll() {
		return null;
	}

	
	public List<ChatRecordItem> getItem(String srcId, String dstId, int num){
		String dbName = getDBName(srcId, dstId);
		if(num < 10)
			num = 10;
		String sql = "select * from " + dbName + " order by timestamp desc limit " + (num-10) +","+ num;
		List<ChatRecordItem> chatRecordItems = chatRecordDAO.getItems(sql);
		
		return chatRecordItems;
	}
	
	
	public boolean addItem(String srcId, String dstId, String content, String timestamp){
		String dbName = getDBName(srcId, dstId);
		//TEST
		String sql = "insert into " + dbName + " values('"+srcId+"', '"+ dstId+"', '"+content+"', '"+timestamp+"');";
		System.out.println("==>"+sql);
		chatRecordDAO.addItem(sql);
		
		return true;
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
