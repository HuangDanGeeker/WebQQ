package com.wang.service;

import java.util.List;

import com.wang.dao.IImageDAO;
import com.wang.model.IconImageModel;

public class ImageService implements IService{

	private IImageDAO imageDAO = new IImageDAO();
	
	@Override
	public IconImageModel get(String id) {
		List<IconImageModel> list = imageDAO.getImageEntityList();
		for(IconImageModel item : list){
			if(item.getIconId().equalsIgnoreCase(id)){
				return item;
			}
		}
		return null;
	}
	
	public boolean updateUserImage(String id, String imageUri){
		return imageDAO.updateUserImage(id, imageUri);
		
	}
	
	@Override
	public List<IconImageModel> getAll() {
		return imageDAO.getImageEntityList();
	}

	@Override
	public boolean delete(String id) {
		imageDAO.delete(id);
		return true;
	}

	public String[] getAllImageId(){
		return imageDAO.getAllId();
	}
	
	public String[] getAllImageUris(){
		return imageDAO.getAllUri();
	}

}
