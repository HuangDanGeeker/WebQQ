package com.wang.bean;


/**
 * @author SteakingCoder
 * Description: 消息记录Bean
 */
public class ChatRecordItem {

	//发送方id
	private String srcId;
	//接收方id
	private String dstId;
	//消息内容
	private String content;
	//时间戳
	private String timeStamp;
	
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public String getDstId() {
		return dstId;
	}
	public void setDstId(String dstId) {
		this.dstId = dstId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
