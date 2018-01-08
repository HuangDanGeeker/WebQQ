package com.wang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.dao.IImageDAO;
import com.wang.model.IconImageModel;

@Service("ImageService")
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
		return (String[])imageDAO.getAllImgIds().toArray();
	}
	
	public String[] getAllImageUris(){
		return (String[])imageDAO.getAllImgUris().toArray();
	}

}
