package com.wang.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wang.bean.ChatRecordItem;
import com.wang.bean.Friend;
import com.wang.dao.IChatRecordDAO;
import com.wang.model.HistoryEntity;


@Service
public class UnreachHistoryService{

	@Resource
	private FriendService friendListService;
	@Resource
	private IChatRecordDAO chatRecordDAO;
	
	public Map<String,List<HistoryEntity>> getUnreachHistory(String id){
		List<Friend> friendList = friendListService.getAllFriends(id);
		Map<String, List<HistoryEntity>> map = new HashMap<String, List<HistoryEntity>>();
		for(Friend f : friendList){
			map.put(f.getFriendId(), new ArrayList<HistoryEntity>());
		}
		
		List<HistoryEntity> listOfHstryEntity = null;
		List<ChatRecordItem> unReadRecordItems = chatRecordDAO.getUnreachRecordItems("history_"+id);
		for(ChatRecordItem item : unReadRecordItems){
			listOfHstryEntity = map.get(item.getSrcId());
			listOfHstryEntity.add(new HistoryEntity("1", item.getContent()));
			map.put(item.getSrcId(), listOfHstryEntity);
		}
		chatRecordDAO.deleteUnreachRecordItems("history_"+id);
		
		return map;
	}
	
	
	
	public boolean addUnreachHistory(String srcId, String dstId, String content, String timestamp){

		chatRecordDAO.addUnreadItem("history_"+dstId, srcId, content, timestamp);
		
		return true;
	}
}
