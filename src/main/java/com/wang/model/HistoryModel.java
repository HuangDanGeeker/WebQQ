package com.wang.model;

/**
 * @author SteakingCoder
 * @Description: 单条消息记录，相较HistoryEntity更加详细
 */
public class HistoryModel {

	//消息的发送方
	private String from;
	//消息的接收方
	private String to;
	//消息内容
	private String content;
	//消息发送时间戳
    private String timestamp;
    
    /**
     * @Constructor
     * @author SteakingCoder
     */
    public HistoryModel(){}
	
    /**
     * @Constructor
     * @author SteakingCoder
     */
	public HistoryModel(String from, String to, String content, String timestamp){
		this.from = from;
		this.to = to;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string = "{from:"+from+",to:"+to+",content:"+content+",timestamp:"+timestamp+"}";
		return string;
	}

}
