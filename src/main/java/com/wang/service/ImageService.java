package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import com.wang.dao.IImageDAO;
import com.wang.model.IconImageModel;

public class ImageService{

	@Resource
	private IImageDAO imageDAO;
	
	public IconImageModel getImg(String imgeId) {
		return imageDAO.getImg(imgeId);
	}
	
	public boolean updateUserImage(String id, String imageUri){
		return imageDAO.updateUserImage(id, imageUri);
		
	}
	
	public List<IconImageModel> getAllImgs() {
		return imageDAO.getAllImgs();
	}

	public boolean delete(String id) {
		imageDAO.deleteImg(id);
		return true;
	}

	public String[] getAllImageId(){
		return imageDAO.getAllImgIds();
	}
	
	public String[] getAllImageUris(){
		return imageDAO.getAllImgUris();
	}

}
