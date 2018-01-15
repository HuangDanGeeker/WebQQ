package com.wang.controller;  
  

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;  
import com.wang.service.MyFriendService;

@Controller  
public class HelloController {  
   
	@RequestMapping("/hello")  
    public ModelAndView hello(){ 
		System.out.println("hello");
		 ModelAndView mv = new ModelAndView();  
		 //mv.addObject("spring", "spring mvc");  
         mv.setViewName("test");  
         return mv;
  
     }   
	
	@RequestMapping("/emoji")  
    public ModelAndView emoji(){ 
		System.out.println("emoji");
		 ModelAndView mv = new ModelAndView();  
		 //mv.addObject("spring", "spring mvc");  
         mv.setViewName("test_twemoji");  
         return mv;
  
     }   
	
	@Resource
	public MyFriendService friendService;
	
	@RequestMapping("/m")
	@ResponseBody
	public String test(){
		System.out.println("m");
//		Friend f = friendService.getFriend("123", "kun");
		
//		List<Friend> friends = friendService.getAllFriends("123");
//		for(Friend friend : friends){
//			System.out.println(friend.getFriendId());
//		}
//		friendService.deleteFriend("123", "kun");
		friendService.addFriend("123", "111111", "111111111");
		System.out.println("m");
		return "mmmmmm";
		
	}
}  