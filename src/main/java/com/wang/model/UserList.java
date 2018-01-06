package com.wang.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;
public class UserList {

	private static Map<Session, String> userListMap = new HashMap<Session, String>();
	
	private UserList(){
		
	}
	
	public static Map<Session, String> getUserList(){
		if(userListMap == null){
			return new HashMap<Session, String>();
		}
		return userListMap;
	}
	
	//TODO
	//增删改查
	
	@Override
	public String toString() {
		
		return userListMap.toString();
	}
}
