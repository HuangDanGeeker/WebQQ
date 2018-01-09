package com.wang.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.wang.service.UserEntityService;

public class UserEntity {
    private String userId;
    private String[] friendsId;
    private String[] friendsIcons;
    private int exist;
    private String iconUri;

    //对friendsId friendsIcons做冗余
    private Map<String, String> friendMap = new HashMap<String, String>();

    @Resource
    private UserEntityService userEntityService;
    
    public UserEntity(String userId){
    	//TODO 
    	//数据库获得数据
    	
    	//TEST
//    	if(userId.equals("123")){
//	    	this.userId = userId;
//
//	    	friendsId = new String[]{"456","789", "abc"};
//	    	friendsIcons = new String[]{"456","789", "abc"};
//	    	exist = 1;
//    	}else if(userId.equals("abc")){
//    		this.userId = userId;
//	    	friendsId = new String[]{"cde","syz", "123"};
//	    	friendsIcons = new String[]{"cde","syz", "123"};
//	    	exist = 1;
//    	}else{
//    		this.userId = userId;
//    		friendsId = null;
//	    	friendsIcons = null;
//    		exist = 0;
//    	}
//    	
//    	friendMap.put("friendId1", "iconUri1");
//    	friendMap.put("friendId2", "iconUri2");
//    	friendMap.put("friendId3", "iconUri3");
//    	iconUri = "http://localhost:8080/SpringMVC/images/defaultIcon.jpg";
    }

    public UserEntity(){};

    public UserEntity getUserEntity(String id){
    	return userEntityService.get(id);
    }
    
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String[] getFriendsId() {
		return friendsId;
	}


	public void setFriendsId(String[] friendsId) {
		this.friendsId = friendsId;
	}


	public int getExist() {
		return exist;
	}




	public String[] getFriendsIcons() {
		return friendsIcons;
	}

	public void setFriendsIcons(String[] friendsIcons) {
		this.friendsIcons = friendsIcons;
	}

	public Map<String, String> getFriendMap() {
		return friendMap;
	}

	public void setFriendMap(Map<String, String> friendMap) {
		this.friendMap = friendMap;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}

	public String getIconUri() {
		return iconUri;
	}

	public void setIconUri(String iconUri) {
		this.iconUri = iconUri;
	}
   

}
