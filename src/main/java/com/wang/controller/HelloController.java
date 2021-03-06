package com.wang.controller;  

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  


@Controller
public class HelloController {  
	
	/**
	 * @Description: 返回用户初始访问界面
	 * @throws
	 * @author SteakingCoder
	 */
	@RequestMapping("/hello")  
    public ModelAndView hello(){ 
		System.out.println("hello");
		 ModelAndView mv = new ModelAndView();  
		 //mv.addObject("spring", "spring mvc");  
         mv.setViewName("test_bootstrap");  
         return mv;
  
     }   
	
	
	/**
	 * @Description: 展示所有的emoji图片
	 * @param 
	 * @author SteakingCoder
	 */
	@RequestMapping("/emoji")  
    public ModelAndView emoji(){ 
		System.out.println("emoji");
		 ModelAndView mv = new ModelAndView();  
		 //mv.addObject("spring", "spring mvc");  
         mv.setViewName("test_twemoji");  
         return mv;
  
     }   
	
}  