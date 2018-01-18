package com.wang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.bean.Friend;
import com.wang.bean.User;
import com.wang.model.UserEntity;

@Service("userEntityService")
public class UserEntityService{

	@Resource
	private UserService userService;
	@Resource
	private FriendService friendListService;

	public UserEntityService() {
		// TODO Auto-generated constructor stub
	}
	
	public UserEntity get(String id) {
		User user = userService.get(id);
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(id);
		if(user == null){
			userEntity.setExist(0);
		}else{
			userEntity.setExist(1);
			userEntity.setIconUri(user.getImageUri());

			List<Friend> friendList = friendListService.getAllFriends(userEntity.getUserId());
			String[] friendIdList = new String[friendList.size()];
			String[] friendImgUriList = new String[friendList.size()];
			Map<String, String> friendMap = new HashMap<String, String>();
			for(int i = 0; i < friendList.size(); i++){
				friendIdList[i] = friendList.get(i).getFriendId();
				friendImgUriList[i] = friendList.get(i).getFriendImgUri();
				friendMap.put(friendIdList[i], friendImgUriList[i]);
			}
			userEntity.setFriendMap(friendMap);
			userEntity.setFriendsId(friendIdList);
			userEntity.setFriendsIcons(friendImgUriList);
		}
		
		return userEntity;
	}
	
	public User getUser(String id) {
		
		User user = userService.get(id);
		return user;
	}

	public boolean deleteFriend(String userId, String friendId) {
		friendListService.deleteFriend(userId, friendId);
		return true;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public FriendService getFriendListService() {
		return friendListService;
	}

	public void setFriendListService(FriendService friendListService) {
		this.friendListService = friendListService;
	}


public String getDBName(String id_1, String id_2){
		
		if(id_1.length() < id_2.length()){
			return id_2 +"_"+id_1;
		}else if(id_1.length() > id_2.length()){
			return id_1 +"_" + id_2;
		}else{
			int result = id_1.compareToIgnoreCase(id_2);
			if(result > 0)
				return id_1 +"_" + id_2;
			else
				return id_2 +"_"+id_1;
		}
	}
}
