package com.wang.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Id
	public String id;
	
	public String imageUri;
	public String sex;
	public int age;
	public String signature;
	
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{\"id\":\""+this.getId()+"\", \"age\":\""+this.getAge()+"\", \"sex\":\""+this.getSex()+"\", \"signature\":\""+this.getSignature()+"\"}";
	}
	
}
