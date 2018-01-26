package com.wang.bean;
 
/**
 * @author SteakingCoder
 * @Description: 好友Bean
 *
 */
public class Friend {

	//好友id
	private String friendId;
	//好友头像id 
	//@deprecated
	private String friendImgId;
	//好友头像uri
	private String friendImgUri;
	//好友所在分组的祖名
	private String groupName;
	
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getFriendImgId() {
		return friendImgId;
	}
	public void setFriendImgId(String friendImgId) {
		this.friendImgId = friendImgId;
	}
	public String getFriendImgUri() {
		return friendImgUri;
	}
	public void setFriendImgUri(String friendImgUri) {
		this.friendImgUri = friendImgUri;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	

}
