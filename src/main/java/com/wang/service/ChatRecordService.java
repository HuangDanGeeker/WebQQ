package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.bean.ChatRecordItem;
import com.wang.dao.IChatRecordDAO;

@Service("chatRecordService")
public class ChatRecordService extends BasicService{

	@Resource
	IChatRecordDAO chatRecordDAO;
	
	public List<ChatRecordItem> getItem(String srcId, String dstId, int num){
		if(num < 10)
			num = 10;
		List<ChatRecordItem> chatRecordItems = chatRecordDAO.getItems("record_"+getDBName(srcId, dstId), num-10, num);
		
		return chatRecordItems;
	}
	
	
	public boolean addItem(String srcId, String dstId, String content, String timestamp){
		String dbName = "record_"+getDBName(srcId, dstId);
		chatRecordDAO.addItem(dbName, srcId, dstId, content, timestamp);
		
		return true;
	}
	

}
