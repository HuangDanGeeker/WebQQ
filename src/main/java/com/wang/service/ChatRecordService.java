package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import com.wang.bean.ChatRecordItem;
import com.wang.dao.IChatRecordDAO;

public class ChatRecordService extends BasicService{

	@Resource
	IChatRecordDAO chatRecordDAO;
	
	public List<ChatRecordItem> getItem(String srcId, String dstId, int num){
		String dbName = getDBName(srcId, dstId);
		if(num < 10)
			num = 10;
		List<ChatRecordItem> chatRecordItems = chatRecordDAO.getItems(getDBName(srcId, dstId), num-10, num);
		
		return chatRecordItems;
	}
	
	
	public boolean addItem(String srcId, String dstId, String content, String timestamp){
		String dbName = getDBName(srcId, dstId);
		String sql = "insert into " + dbName + " values('"+srcId+"', '"+ dstId+"', '"+content+"', '"+timestamp+"');";
		chatRecordDAO.addItem(dbName, srcId, dstId, content, timestamp);
		
		return true;
	}
	

}
