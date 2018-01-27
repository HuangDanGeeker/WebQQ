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


/**
 * @author SteakingCoder
 * @Description 用户未读消息Service
 */
@Service
public class UnreachHistoryService{

	/**
	 * Spring注入的FriendService
	 */
	@Resource
	private FriendService friendListService;
	/**
	 * Spring注入的消息记录DAO
	 */
	@Resource
	private IChatRecordDAO chatRecordDAO;
	
	
	
	
	/**
	 * 返回指定用户id的未读消息 Map<String friendId, List<HistoryEntity>>
	 *
	 * @param id 用户id
	 * @return Map<String,List<HistoryEntity>>
	 * @author SteakingCoder
	 */
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
	
	
	
	
	
	/**
	 * 添加未读消息记录
	 *
	 * @param srcId 发送方id
	 * @param dstId 接收方id,根据此id获得存储表名
	 * @param content 消息内容
	 * @param timestamp 消息时间戳
	 * @author SteakingCoder
	 */
	public boolean addUnreachHistory(String srcId, String dstId, String content, String timestamp){

		chatRecordDAO.addUnreadItem("history_"+dstId, srcId, content, timestamp);
		
		return true;
	}
}
