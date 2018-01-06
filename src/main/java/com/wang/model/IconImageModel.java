package com.wang.model;

public class IconImageModel {

	private String imageId;
	private String imageUri;
	
	public IconImageModel() {
		// TODO Auto-generated constructor stub
	}
	
	public IconImageModel(String iconId, String uri) {
		// TODO Auto-generated constructor stub
		this.imageId = iconId;
		this.imageUri = uri;
	}
	
	public String getIconId() {
		return imageId;
	}
	public void setIconId(String iconId) {
		this.imageId = iconId;
	}
	public String getUri() {
		return imageUri;
	}
	public void setUri(String uri) {
		this.imageUri = uri;
	}
	
	
}
