package com.wang.service;

import org.springframework.stereotype.Service;

/**
 * @author SteakingCoder
 * @Description: 基础Service类，提供公共函数getDBName()
 */
@Service("basicService")
public class BasicService {

	//
	
	
	/**
	 * 对于record_id1_id2||record_id2_id1表名的确定
	 *  
	 * @author SteakingCoder
	 */
	public String getDBName(String id_1, String id_2){
		//首先 根据id长度进行确定,id长度大的排在前面
		if(id_1.length() < id_2.length()){
			return id_2 +"_"+id_1;
		}else if(id_1.length() > id_2.length()){
			return id_1 +"_" + id_2;
		//其次 ids长度相同，进行字符串比较(ignore case), 比较结果大的排在前面
		}else{
			int result = id_1.compareToIgnoreCase(id_2);
			if(result > 0)
				return id_1 +"_" + id_2;
			else
				return id_2 +"_"+id_1;
		}
	}
}