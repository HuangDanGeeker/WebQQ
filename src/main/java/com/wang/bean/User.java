package com.wang.bean;


/**
 * @author SteakingCoder
 * @Description: 用户个人信息Bean
 */
public class User {

	public String id;
	//头像uri
	public String imageUri;
	public String sex;
	public int age;
	//个性签名
	public String signature;
	//在线标志
	public boolean isAlive;
	
	public User(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImageUri() {
		return imageUri;
	}
	public void setImageUri(String imageId) {
		this.imageUri = imageId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	//重写toString
	@Override
	public String toString() {
		return "{\"id\":\""+this.getId()+"\", \"age\":\""+this.getAge()+"\", \"sex\":\""+this.getSex()+"\", \"signature\":\""+this.getSignature()+"\"}";
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
