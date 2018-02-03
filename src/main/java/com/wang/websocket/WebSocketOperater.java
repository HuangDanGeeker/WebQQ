package com.wang.websocket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import org.springframework.web.context.ContextLoader;
import net.sf.json.JSONObject;
import com.wang.model.UserList;
import com.wang.service.ChatRecordService;
import com.wang.service.FriendService;
import com.wang.service.UnreachHistoryService;
import com.wang.service.UserService;


@ServerEndpoint("/websocket")
public class WebSocketOperater {

	private UnreachHistoryService unreachHistoryService = (UnreachHistoryService) ContextLoader.getCurrentWebApplicationContext().getBean("unreachHistoryService");
	private ChatRecordService chatRecordService = (ChatRecordService) ContextLoader.getCurrentWebApplicationContext().getBean("chatRecordService");
	private UserService userService = (UserService) ContextLoader.getCurrentWebApplicationContext().getBean("userService");
	private FriendService friendService = (FriendService) ContextLoader.getCurrentWebApplicationContext().getBean("friendService");
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	
	
	
	/**
	 * websocket 处理消息接收
	 *
	 * @param message
	 * @param session
	 * @throws IOException
	 * @throws InterruptedException 
	 * @author SteakingCoder
	 */
	@OnMessage
	public synchronized void onMessage(String message, Session session) 
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
			if(userMap.get(toSession).equalsIgnoreCase(msgEntity[0])){
				toSession.getBasicRemote().sendText(msgEntity[1]);
				JSONObject msgJsonObject = JSONObject.fromObject(msgEntity[1]);
				chatRecordService.addItem(msgJsonObject.getString("from"), msgEntity[0], msgJsonObject.getString("msg"), formatter.format(new Date()));
				System.out.println("用户在线");
				return;
			}
		}
		System.out.println("用户不在线");
		//用户不在线
		JSONObject msgJsonObject = JSONObject.fromObject(msgEntity[1]);
		unreachHistoryService.addUnreachHistory(msgJsonObject.getString("from"), msgEntity[0], msgJsonObject.getString("msg"), formatter.format(new Date()));
	}
	
	
	
	
	/**
	 * websocket 处理链接建立
	 *
	 * @param session
	 * @throws IOException 
	 * @author SteakingCoder
	 */
	@OnOpen
    public synchronized void onOpen(Session session) throws IOException {
        System.out.println("=>Client connected" );
        //   /SpringMVC/websocket?uid=123
        String userId = session.getRequestURI().toString().substring(21);

        Map<Session, String> userMap = UserList.getUserList();
        userMap.put(session, userId); 
        System.out.println(userMap.toString());
        //通知在线的好友，user上线
        List<String> alivedFriends = friendService.getAlivedFriends(userId);
        Iterator<String> friendsIterator = alivedFriends.iterator();
		Iterator<Session> iterator = userMap.keySet().iterator();
		Session toSession = null;
		String friendId = null;
		while(iterator.hasNext() && friendsIterator.hasNext()){
			toSession = (Session) iterator.next();
			friendId = friendsIterator.next();
			if(userMap.get(toSession).equalsIgnoreCase(friendId)){
				toSession.getBasicRemote().sendText("##friend_log_in:"+userId);
			}
		}
    }

    
    
    /**
     * websocket 处理连接断开
     *
     * @param session
     * @throws IOException 
     * @author SteakingCoder
     */
    @OnClose
    public synchronized void onClose(Session session) throws IOException {
    	Map<Session, String> userMap = UserList.getUserList();
    	String logoutId = userMap.get(session);
        userService.logout(logoutId);
        userMap.remove(session);
        List<String> alivedFriends = friendService.getAlivedFriends(logoutId);
        Iterator<String> friendsIterator = alivedFriends.iterator();
		Iterator<Session> iterator = userMap.keySet().iterator();
		Session toSession = null;
		String friendId = null;
		while(iterator.hasNext() && friendsIterator.hasNext()){
			toSession = (Session) iterator.next();
			friendId = friendsIterator.next();
			System.out.println("session : "+toSession +" friendId : "+friendId);
			if(userMap.get(toSession).equalsIgnoreCase(friendId)){
				toSession.getBasicRemote().sendText("##friend_log_out:"+logoutId);
			}
		}
		
        System.out.println("Connection closed");
        System.out.println(userMap.toString());
        
    }
}
