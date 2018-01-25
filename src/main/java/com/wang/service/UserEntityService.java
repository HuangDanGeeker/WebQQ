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
	private FriendService friendService;

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

			List<Friend> friendList = friendService.getAllFriends(userEntity.getUserId());
			String[] friendIdList = new String[friendList.size()];
			String[] friendImgUriList = new String[friendList.size()];
			String[] friendGroupList = new String[friendList.size()];
			Boolean[] friendAliveList = new Boolean[friendList.size()];
			Map<String, String> friendMap = new HashMap<String, String>();
			for(int i = 0; i < friendList.size(); i++){
				friendIdList[i] = friendList.get(i).getFriendId();
				friendGroupList[i] = friendList.get(i).getGroupName();
				friendImgUriList[i] = userService.getUserImgUri(friendIdList[i]);
				friendAliveList[i] = userService.isAlive(friendIdList[i]);
				friendMap.put(friendIdList[i], friendImgUriList[i]);
			}
			userEntity.setFriendMap(friendMap);
			userEntity.setFriendsGroupNames(friendGroupList);
			userEntity.setFriendsId(friendIdList);
			userEntity.setFriendsIcons(friendImgUriList);
			userEntity.setFriendAlives(friendAliveList);
		}
		userService.setAlive(user.id, new Boolean(true));
		return userEntity;
	}
	
	public User getUser(String id) {
		
		User user = userService.get(id);
		return user;
	}

	public boolean deleteFriend(String userId, String friendId) {
		friendService.deleteFriend(userId, friendId);
		return true;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public FriendService getFriendListService() {
		return friendService;
	}

	public void setFriendListService(FriendService friendListService) {
		this.friendService = friendListService;
	}


}
