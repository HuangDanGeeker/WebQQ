package com.wang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.bean.Friend;
import com.wang.bean.User;
import com.wang.dao.UserDAO;
import com.wang.model.UserEntity;

@Service
public class UserEntityService implements IService{

	private UserService userService = new UserService();
	private FriendService friendListService = new FriendService();
	@Override
	@Transactional
	public Object get(String id) {
		
		User user = userService.get(id);
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(id);
		if(user == null){
			userEntity.setExist(0);
		}else{
			userEntity.setExist(1);
			userEntity.setIconUri(user.getImageUri());

			List<Friend> friendList = friendListService.getAllFriend(userEntity.getUserId());
			String[] friendIdList = new String[friendList.size()];
			String[] friendImgUriList = new String[friendList.size()];
			Map<String, String> friendMap = new HashMap<String, String>();
			for(int i = 0; i < friendList.size(); i++){
				friendIdList[i] = friendList.get(i).getFriendId();
				friendImgUriList[i] = friendList.get(i).getFriendImgId();
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

	@Override
	public boolean delete(String id) {
		friendListService.delete(id);
		return true;
	}

	@Override
	public List<?> getAll() {
		
		return null;
	}

	
}
