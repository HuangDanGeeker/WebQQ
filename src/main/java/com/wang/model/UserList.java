package com.wang.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;
/**
 * @author SteakingCoder
 * @Description: 服务器websocket端使用的(session, userId)存储集
 */
public class UserList {

	//存储HashMap
	private static Map<Session, String> userListMap = new HashMap<Session, String>();
	
	private UserList(){
		
	}
	
	//TODO 增加线程安全
	public static Map<Session, String> getUserList(){
		if(userListMap == null){
			return new HashMap<Session, String>();
		}
		return userListMap;
	}
	
	
	@Override
	public String toString() {
		
		return userListMap.toString();
	}
}
