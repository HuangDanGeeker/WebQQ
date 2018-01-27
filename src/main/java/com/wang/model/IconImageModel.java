package com.wang.model;

/**
 * @author SteakingCoder
 * @Description: 图片资源Model 
 */
public class IconImageModel {

	// 图像id
	private String imageId;
	// 图像uri
	private String imageUri;
	
	public IconImageModel() {}
	
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
