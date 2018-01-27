package com.wang.model;


/**
 * @author SteakingCoder
 * @Description: 单条消息记录
 */
public class HistoryEntity {

	//枚举值 1好友发送的消息 0自己发送的消息
	private String flag;
	//消息内容
	private String content;
    
	public HistoryEntity(String flag, String content){
		this.flag = flag;
		this.content = content;
	}
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}
