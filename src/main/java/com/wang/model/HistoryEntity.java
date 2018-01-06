package com.wang.model;

import java.util.Date;

public class HistoryEntity {

	private String flag;
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
