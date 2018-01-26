package com.wang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wang.bean.ChatRecordItem;

/**
 * @author SteakingCoder
 * @Description: 消息记录DAO
 */
public interface IChatRecordDAO{

	/**
	 * 
	 * @Description: 获取user1与user2的某10条聊天记录
	 * @param dbName 数据表名
	 * @param startNum 获取聊天记录的起始点
	 * @param endNum 获取聊天记录的终止点
	 * @return List<ChatRecordItem>
	 * @author SteakingCoder
	 */
	public List<ChatRecordItem> getItems(@Param("dbName")String dbName, @Param("startNum")int startNum, @Param("endNum")int endNum);
	
	
	
	/**
	 * @Description: 向数据表dbName中插入未读消息(一条)
	 * @param dbName 未读消息数据表名
	 * @param srcId 发送方id
	 * @param dstId 接收方id
	 * @param content 消息内容
	 * @param timestamp 时间戳 
	 * @author SteakingCoder
	 */
	public void addItem(@Param("dbName")String dbName, @Param("srcId")String srcId, @Param("dstId")String dstId, @Param("content")String content, @Param("timestamp")String timestamp);

	
	
	/**
	 * @Description: 
	 * @param dbName
	 * @param srcId
	 * @param content
	 * @param timestamp     
	 * @author SteakingCoder
	 */
	public void addUnreadItem(@Param("dbName")String dbName, @Param("srcId")String srcId, @Param("content")String content, @Param("timestamp")String timestamp);
	
	
	
	/**
	 * @Description: 返回未读消息
	 * @param dbName 未读消息数据表
	 * List<ChatRecordItem>     
	 * @author SteakingCoder
	 */
	public List<ChatRecordItem> getUnreachRecordItems(@Param("dbName")String dbName);
	
	
	
	/**
	 * @Description: 清空未读消息数据表中的所有的内容
	 * @param dbName 未读消息数据表    
	 * @author SteakingCoder
	 */
	public void deleteUnreachRecordItems(@Param("dbName")String dbName);
}
