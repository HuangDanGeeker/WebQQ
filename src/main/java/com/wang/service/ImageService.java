package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.dao.IImageDAO;
import com.wang.model.IconImageModel;

/**
 * @author SteakingCoder
 * @Description 用户头像Service
 */
@Service("ImageService")
public class ImageService{

	/**
	 * spring 注入imageDAO
	 */
	@Resource
	private IImageDAO imageDAO;
	
	
	
	/**
	 * 根据imgId获取图片对象
	 *
	 * @param imgeId
	 * @return IconImageModel
	 * @author SteakingCoder
	 */
	public IconImageModel getImg(String imgeId) {
		return imageDAO.getImg(imgeId);
	}
	
	
	
	/**
	 * 根据id更新imageUri
	 *
	 * @param id
	 * @param imageUri
	 * @return boolean
	 * @author SteakingCoder
	 */
	public boolean updateUserImage(String id, String imageUri){
		return imageDAO.updateUserImage(id, imageUri);
		
	}
	
	
	
	/**
	 * 返回所有的img信息(imgId, ImgUri)
	 *
	 * @return List<IconImageModel>
	 * @author SteakingCoder
	 */
	public List<IconImageModel> getAllImgs() {
		return imageDAO.getAllImgs();
	}

	
	
	
	/**
	 * 根据图片的id删除图片
	 *
	 * @param id 头像id
	 * @return 
	 * @author SteakingCoder
	 */
	public boolean delete(String id) {
		imageDAO.deleteImg(id);
		return true;
	}

	
	
	/**
	 * 获取所有的用户头像id
	 *
	 * @return String[]
	 * @author SteakingCoder
	 */
	public String[] getAllImageId(){
		return (String[])imageDAO.getAllImgIds().toArray();
	}
	
	
	
	/**
	 * 获取所有的用户头像uri列表
	 *
	 * @return String[]
	 * @author SteakingCoder
	 */
	public String[] getAllImageUris(){
		return (String[])imageDAO.getAllImgUris().toArray();
	}

}
