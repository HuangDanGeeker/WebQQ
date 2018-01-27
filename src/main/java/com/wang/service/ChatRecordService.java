package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.bean.ChatRecordItem;
import com.wang.dao.IChatRecordDAO;

/**
 * @author SteakingCoder
 * @Description: 消息记录Service
 */
@Service("chatRecordService")
public class ChatRecordService extends BasicService{

	/**
	 * Spring 注入的消息记录DAO
	 */
	@Resource
	IChatRecordDAO chatRecordDAO;
	
	
	
	/**
	 * 返回用户与某位好友的[-num+10, -num]条消息记录。
	 * '-' 代表倒数
	 * @param srcId 用户id
	 * @param dstId 好友id
	 * @param num 获取的消息记录终止点
	 * @return List<ChatRecordItem>
	 * @author SteakingCoder
	 */
	public List<ChatRecordItem> getItem(String srcId, String dstId, int num){
		//防止获取的消息记录终止点越界(<0)
		if(num < 10)
			num = 10;
		
		List<ChatRecordItem> chatRecordItems = chatRecordDAO.getItems("record_"+getDBName(srcId, dstId), num-10, num);
		
		return chatRecordItems;
	}
	
	
	
	
	/**
	 * 向srcId与 dstId的消息记录中添加消息记录
	 * 
	 * @param srcId 用户id 
	 * @param dstId 好友id
	 * @param content 消息内容
	 * @param timestamp 消息发送的时间戳
	 * @return 
	 * @author SteakingCoder
	 */
	public boolean addItem(String srcId, String dstId, String content, String timestamp){
		String dbName = "record_"+getDBName(srcId, dstId);
		chatRecordDAO.addItem(dbName, srcId, dstId, content, timestamp);
		
		return true;
	}
	

}
