package com.wang.service;

import org.springframework.stereotype.Service;

@Service("basicService")
public class BasicService {

	
	public String getDBName(String id_1, String id_2){
		
		if(id_1.length() < id_2.length()){
			return id_2 +"_"+id_1;
		}else if(id_1.length() > id_2.length()){
			return id_1 +"_" + id_2;
		}else{
			int result = id_1.compareToIgnoreCase(id_2);
			if(result > 0)
				return id_1 +"_" + id_2;
			else
				return id_2 +"_"+id_1;
		}
	}
}