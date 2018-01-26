package com.wang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wang.model.IconImageModel;

/**
 * @author SteakingCoder
 * @Description: 图片相关DAO,主要应对用户头像
 */
public interface IImageDAO{
	
	
	
	
	/**
	 * @Description: 更改用户的头像
	 * @param id 用户id
	 * @param imageUri 用户想要更改的头像uri
	 * @return     
	 * @author SteakingCoder
	 */
	public boolean updateUserImage(@Param("id")String id, @Param("imageUri")String imageUri);
	
	
	
	/**
	 * @Description: 返回所有的头像id
	 * @return List<String>
	 * @author SteakingCoder
	 */
	@Deprecated
	public List<String> getAllImgIds();
	
	/**
	 * @Description: 返回所有头像的uri
	 * @return List<String>
	 * @author SteakingCoder
	 */
	public List<String> getAllImgUris();
	
	/**
	 * @Description: 返回所有头像对象(包括头像id和uri)
	 * @return List<IconImageModel>
	 * @author SteakingCoder
	 */
	public List<IconImageModel> getAllImgs();
	
	
	
	/**
	 * @Description: 删除图片
	 * @param imageId 
	 * @author SteakingCoder
	 */
	@Deprecated
	public void deleteImg(@Param("imageId")String imageId);
	
	
	
	
	/**
	 * @Description: 获取指定imgId的IconImageModel对象
	 * @param imageId 图像id
	 * @return IconImageModel
	 * @author SteakingCoder
	 */
	public IconImageModel getImg(@Param("imageId")String imageId);
	
	
}
