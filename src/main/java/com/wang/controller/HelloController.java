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
         mv.setViewName("test_bootstrap");  
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
	
	
}  