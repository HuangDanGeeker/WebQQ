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
import com.wang.service.UnreachHistoryService;


@ServerEndpoint("/websocket")
@Service
public class WebSocketOperater {

	//TODO 这个时候注入就不可以了
	@Resource
	private UnreachHistoryService unreachHistoryService;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@OnMessage
	public void onMessage(String message, Session session) 
			throws IOException, InterruptedException {
		System.out.println("----------------------------");
		System.out.println("receive message " + message);
		//#toUser:#msgType:content
		String[] msgEntity = message.split(":#", 2);
		Map<Session, String> userMap = UserList.getUserList();
		Iterator iterator = userMap.keySet().iterator();
		Session toSession = null;
		//用户在线
		while(iterator.hasNext()){
			toSession = (Session) iterator.next();
			if(userMap.get(toSession).equalsIgnoreCase(msgEntity[0])){
				toSession.getBasicRemote().sendText(msgEntity[1]);
				break;
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
    public void onClose(Session session) {
    	Map<Session, String> userMap = UserList.getUserList();
        userMap.remove(session);
        System.out.println("Connection closed");
        System.out.println(userMap.toString());
        
    }
}
