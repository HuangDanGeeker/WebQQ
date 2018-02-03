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
	
	
	
	
	/**
	 * @Description: 返回用户登录所需要的数据集
	 * @param @param userId 用户登录的id
	 * @author SteakingCoder
	 */
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
		System.out.println("=====> deleteFriend");		
		System.out.println("userID " + userId);		
		System.out.println("friendId " + friendId);		
		System.out.println("fullDelete " + fullDelete);		

		friendService.deleteFriend(userId, friendId);
		if(fullDelete.equalsIgnoreCase("true")){
			friendService.deleteFriend(friendId, userId);
		}
		return "{\"result\":1}";
	}
	
	
	
	
	/**
	 * @Description: 获取用户与某个好友的消息记录
	 * @param @param userId 用户id
	 * @param @param friendId 好友id
	 * @param @param num 返回消息记录的起始条数,每次返回都是固定的10条记录
	 * @author SteakingCoder
	 */
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
	
	
	
	/**
	 * @Description: 获取userId用户没有读的消息
	 * @param @param userId 用户id
	 * @author SteakingCoder
	 */
	@RequestMapping(value="/history/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, List<HistoryEntity>> history(@PathVariable String userId){
		System.out.println("/history/{userId}");		
		System.out.println("userID " + userId);		
		return unreachHistoryService.getUnreachHistory(userId);

	}
	
	
	
	
	/**
	 * @Description: 用户下线
	 * @param @param userId  用户id    
	 * @author SteakingCoder
	 */
	@RequestMapping(value="/logout/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public void logout(@PathVariable String userId){
		System.out.println("/logout/{userId}");
		System.out.println("userId : "+userId);
		userService.logout(userId);
		return ;
	}
	
	
	
	/**
	 * @Description: 获得用户所有可用的头像
	 * @author SteakingCoder
	 */
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
	
	@RequestMapping(value="/queryIcon/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public String querUserIcon(@PathVariable String userId){
		System.out.println("=====> /queryIcon/{userId}");		
		System.out.println("=====> userId " + userId);		
		
		return userService.getUserImgUri(userId);
	}
	
	
	
	
	
	/**
	 * @Description: 
	 * @param @param Id 用户id
	 * @param @param imageUri 要更换为的头像uri
	 * @return "true"    
	 * @author SteakingCoder
	 */
	@RequestMapping(value="/changeIcon/{Id}/{imageUri}", method=RequestMethod.GET)
	@ResponseBody
	public String changeIcon(@PathVariable String Id, @PathVariable String imageUri){
		System.out.println("=====> changeIcon");		
		System.out.println("userID " + Id);		
		System.out.println("imageId " + imageUri);		
	
		imageService.updateUserImage(Id, "http://localhost:8080/SpringMVC/images/"+imageUri+".jpg");
		
		return "true";
	}
	
	
	
	
	/**
	 * @Description: 返回用户的个人信息
	 * @param @param userId
	 * @return User对象的toString     
	 * @author SteakingCoder
	 */
	@RequestMapping(value="/querySelfInfo/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public String querySelfInfo(@PathVariable String userId){
		System.out.println("=====> /querySelf/{userId}");		
		System.out.println("userID " + userId);		
	
		User user = userService.get(userId);
		return user.toString();
			
	}
	
	
	
	/**
	 * @Description: 更新用户的个人信息
	 * @param @param user
	 * @return ""     
	 * @author SteakingCoder
	 */
	@RequestMapping(value="/updateSelfInfo", method=RequestMethod.POST)
	@ResponseBody
	public String uploadSelfInfo(@RequestBody User user){
		System.out.println("/uploadHistory");		
	
		System.out.println(user.toString());
		userService.update(user);
		return "";
	}
	
	
	
	/**
	 * @Description: 添加好友
	 * @param @param id 用户id
	 * @param @param friendId 要添加的好友id
	 * @param @param groupName 添加到groupName分组中
	 * @return json,用户是否存在，如果存在一并返回好友的头像uri     
	 * @author SteakingCoder
	 */
	@RequestMapping(value="/queryUser/{id}/{friendId}/{groupName}", method=RequestMethod.GET)
	@ResponseBody
	public String addFriend(@PathVariable String id, @PathVariable String friendId, @PathVariable String groupName){
		System.out.println("=====> /queryUser/{id}/{friendId}/{groupName}");		
		System.out.println("userID " + id);		
		System.out.println("friendId " + friendId);		
		System.out.println("groupName " + groupName);		
	
		if(userService.isExist(friendId)){
			User user = userService.get(friendId);
			userService.addFriend(id, friendId, groupName);
			
			return "{\"friendId\":\""+friendId+"\", \"exist\":\"true\", \"imageIconUri\":\""+user.getImageUri()+"\"}";
		}
		if(friendId.equals("___")){
			friendService.addFriend(id, friendId, groupName);
		}
		return "{\"friendId\":\""+friendId+"\", \"exist\":\"false\"}";
	}
	
	
	
	
	
	/**
	 * @Description: 用户上传自己本次登录发送过的消息
	 * @param models HistoryModel[] 保存消息信息
	 * @return ""    
	 * @author SteakingCoder
	 */
	@Deprecated
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
