package com.wang.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wang.service.UserEntityService;

/**
 * @author SteakingCoder
 * @Description: 用户实体,包含用户的个人信息,好友信息,在线状态
 */
@Component
public class UserEntity {
	
    private String userId;					//用户id
    private String[] friendsId;				//好友id数组
    private String[] friendsIcons;			//好友头像uri数组
    private String[] friendsGroupNames;		//好友所在分组
    private int exist;						//该id用户是否存在
    private String iconUri;					//该用户的头像uri
    private Boolean[] friendAlives;			//用户登录时，在线的好友列表
    
    //对friendsId friendsIcons做冗余
    private Map<String, String> friendMap = new HashMap<String, String>();

    @Resource
    private UserEntityService userEntityService = new UserEntityService();
    
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

	public String[] getFriendsGroupNames() {
		return friendsGroupNames;
	}

	public void setFriendsGroupNames(String[] friendsGroupNames) {
		this.friendsGroupNames = friendsGroupNames;
	}

	public Boolean[] getFriendAlives() {
		return friendAlives;
	}

	public void setFriendAlives(Boolean[] friendAlives) {
		this.friendAlives = friendAlives;
	}



}
