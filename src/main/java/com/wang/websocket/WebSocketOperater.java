package com.wang.websocket;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.wang.model.UserList;
import com.wang.service.ChatRecordService;
import com.wang.service.FriendService;
import com.wang.service.UnreachHistoryService;
import com.wang.service.UserService;


@ServerEndpoint("/websocket")
@Service
public class WebSocketOperater {

	//TODO 这个时候注入就不可以了 ???
	@Resource
	private UnreachHistoryService unreachHistoryService;
	@Resource
	private ChatRecordService chatRecordService;
	@Resource
	private UserService userService;
	@Resource
	private FriendService friendService;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@OnMessage
	public void onMessage(String message, Session session) 
			throws IOException, InterruptedException {
		System.out.println("----------------------------");
		System.out.println("receive message " + message);
		//#toUser:#msgType:content
		String[] msgEntity = message.split(":#", 2);
		Map<Session, String> userMap = UserList.getUserList();
		Iterator<Session> iterator = userMap.keySet().iterator();
		Session toSession = null;
		//FUTURE
		// 测试:非法用户名处理
		
		//用户在线
		while(iterator.hasNext()){
			toSession = (Session) iterator.next();
//			System.out.println(msgEntity[0]+" "+userMap.get(toSession)+" "+userMap.get(toSession).equalsIgnoreCase(msgEntity[0]));
			if(userMap.get(toSession).equalsIgnoreCase(msgEntity[0])){
				toSession.getBasicRemote().sendText(msgEntity[1]);
				JSONObject msgJsonObject = JSONObject.fromObject(msgEntity[1]);
				chatRecordService.addItem(msgJsonObject.getString("from"), msgEntity[0], msgJsonObject.getString("msg"), formatter.format(new Date()));
				return;
			}
		}
		//用户不在线
		JSONObject msgJsonObject = JSONObject.fromObject(msgEntity[1]);
		unreachHistoryService.addUnreachHistory(msgJsonObject.getString("from"), msgEntity[0], msgJsonObject.getString("msg"), formatter.format(new Date()));
	}
	
	
	@OnOpen
    public void onOpen(Session session) {
        System.out.println("=>Client connected" );
        //   /SpringMVC/websocket?uid=123
        String userId = session.getRequestURI().toString().substring(25);

        Map<Session, String> userMap = UserList.getUserList();
        userMap.put(session, userId); 
        
        System.out.println(userMap.toString());
    }

    @OnClose
    public void onClose(Session session) throws IOException {
    	Map<Session, String> userMap = UserList.getUserList();
    	String logoutId = userMap.get(session);
        userService.logout(logoutId);
        userMap.remove(session);
        List<String> alivedFriends = friendService.getAlivedFriends(logoutId);
        Iterator<String> friendsIterator = alivedFriends.iterator();
		Iterator<Session> iterator = userMap.keySet().iterator();
		Session toSession = null;
		String friendId = null;
		while(iterator.hasNext()){
			toSession = (Session) iterator.next();
			friendId = friendsIterator.next();
			if(userMap.get(toSession).equalsIgnoreCase(friendId)){
				toSession.getBasicRemote().sendText("##"+logoutId);
			}
		}
		
        System.out.println("Connection closed");
        System.out.println(userMap.toString());
        
    }
}
