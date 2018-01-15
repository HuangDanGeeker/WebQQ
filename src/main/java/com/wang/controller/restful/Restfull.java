package com.wang.controller.restful;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.bean.ChatRecordItem;
import com.wang.bean.User;
import com.wang.model.HistoryEntity;
import com.wang.model.HistoryModel;
import com.wang.model.IconImageModel;
import com.wang.model.UserEntity;
import com.wang.service.ChatRecordService;
import com.wang.service.FriendService;
import com.wang.service.ImageService;
import com.wang.service.UnreachHistoryService;
import com.wang.service.UserEntityService;
import com.wang.service.UserService;

@Controller

public class Restfull {

	@Resource
	private UserService userService;
	@Resource
	private FriendService friendService;
	@Resource
	private ChatRecordService chatRecordService;
	@Resource
	private ImageService imageService;
	@Resource
	private UserEntityService userEntityService;
	@Resource
	private UnreachHistoryService unreachHistoryService;
	@Resource
	private ImageService imgeService;
	
	
	@RequestMapping(value="/apply", method=RequestMethod.GET)
	@ResponseBody
	public String apply(){
		System.out.println("apply");		
	
		return userService.applyAccount();
	}
	
	
	@RequestMapping(value="/User/exist/{userId}")
	@ResponseBody
	public UserEntity checkUserExist(@PathVariable String userId){
		
		System.out.println("xml http request arrived");
		//TODO
		return userEntityService.get(userId);
	}
	
	
	@RequestMapping(value="/test",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String test(){
		System.out.println("test");
		return "{\"abc\":{\"1\":\"1\"}}";
	}
	
	@RequestMapping(value="/delete/{userId}/{friendId}/{fullDelete}", method=RequestMethod.GET)
	@ResponseBody
	public String deleteFriend(@PathVariable String userId, @PathVariable String friendId, @PathVariable String fullDelete){
		System.out.println("delete friends");		
		System.out.println("userID " + userId);		
		System.out.println("friendId " + friendId);		
		System.out.println("fullDelete " + fullDelete);		

		friendService.deleteFriend(userId, friendId);
		if(fullDelete.equalsIgnoreCase("true")){
			friendService.deleteFriend(friendId, userId);
		}
		return "{\"result\":1}";
	}
	
	//获取 针针对某个人的消息记录
	@RequestMapping(value="/history/{userId}/{friendId}/{num}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> historyOf(@PathVariable String userId, @PathVariable String friendId, @PathVariable int num){
		System.out.println("/history/{userId}/{friendId}/{num}");		
		System.out.println("userID " + userId);		
		System.out.println("friendId " + friendId);		
		System.out.println("num " + num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<HistoryEntity> list = new ArrayList<HistoryEntity>();
		
		List<ChatRecordItem> chatRecordList = chatRecordService.getItem(userId, friendId, num);
		ChatRecordItem chatRecordItem;
		for(int i = 0; i < chatRecordList.size(); i++){
			chatRecordItem = chatRecordList.get(i);
			if(chatRecordItem.getSrcId().equalsIgnoreCase(friendId)){
				list.add(new HistoryEntity("1", chatRecordItem.getContent()));
			}else{
				list.add(new HistoryEntity("2", chatRecordItem.getContent()));
			}
		}
		
		map.put("history", list);
		return map;

	}
	
	//获取userId用户没有读的消息
	@RequestMapping(value="/history/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, List<HistoryEntity>> history(@PathVariable String userId){
		System.out.println("/history/{userId}");		
		System.out.println("userID " + userId);		
		return unreachHistoryService.getUnreachHistory(userId);

	}
	
	@RequestMapping(value="/queryIcon", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> querDefaultyIcon(){
		System.out.println("=====> queryDefaultIcon");		
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<IconImageModel> list = imageService.getAllImgs();
//		list.add(new IconImageModel("imageId1", "http://localhost:8080/SpringMVC/images/defaultIcon.jpg"));
		map.put("IconImages", list);
		return map;
	}
	
	
	@RequestMapping(value="/changeIcon/{Id}/{imageUri}", method=RequestMethod.GET)
	@ResponseBody
	public String changeIcon(@PathVariable String Id, @PathVariable String imageUri){
		System.out.println("=====> changeIcon");		
		System.out.println("userID " + Id);		
		System.out.println("imageId " + imageUri);		
	
		imageService.updateUserImage(Id, "http://localhost:8080/SpringMVC/images/"+imageUri+".jpg");
		
		return "true";
	}
	
	@RequestMapping(value="/querySelfInfo/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public String queryUser(@PathVariable String userId){
		System.out.println("=====> /querySelf/{userId}");		
		System.out.println("userID " + userId);		
	
		User user = userService.get(userId);
		return user.toString();
			
	}
	
	@RequestMapping(value="/updateSelfInfo", method=RequestMethod.POST)
	@ResponseBody
	public String uploadSelfInfo(@RequestBody User user){
		System.out.println("/uploadHistory");		
	
		System.out.println(user.toString());
		userService.update(user);
		return "";
	}
	
	@RequestMapping(value="/queryUser/{id}/{friendId}", method=RequestMethod.GET)
	@ResponseBody
	public String queryUser(@PathVariable String id, @PathVariable String friendId){
		System.out.println("=====> queryUser");		
		System.out.println("userID " + id);		
	
		if(userService.isExist(friendId)){
			User user = userService.get(friendId);
			userService.addFriend(id, friendId);
			
			return "{\"friendId\":\""+friendId+"\", \"exist\":\"true\", \"imageIconUri\":\""+user.getImageUri()+"\"}";
		}
		return "{\"friendId\":\""+friendId+"\", \"exist\":\"false\"}";
	}
	
	
	@RequestMapping(value="/uploadHistory", method=RequestMethod.POST)
	@ResponseBody
	public String uploadHistory(@RequestBody HistoryModel[] models){
		System.out.println("uploadHistory");		
	
		System.out.println(models + ":" + models.length);
//		System.out.println(models.toString());
		
		for(int i = 0; i < models.length; i++){
			System.out.println(models[i].toString());
			chatRecordService.addItem(models[i].getFrom(), models[i].getTo(), models[i].getContent().replace('\'', '"'), models[i].getTimestamp());
		}
        
		return "";
	}
}
