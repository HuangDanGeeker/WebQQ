package com.wang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wang.bean.ChatRecordItem;

public interface IChatRecordDAO{

	public List<ChatRecordItem> getItems(@Param("dbName")String dbName, @Param("startNum")int startNum, @Param("endNum")int endNum);
	
	public void addItem(@Param("dbName")String dbName, @Param("srcId")String srcId, @Param("dstId")String dstId, @Param("content")String content, @Param("timestamp")String timestamp);

}
