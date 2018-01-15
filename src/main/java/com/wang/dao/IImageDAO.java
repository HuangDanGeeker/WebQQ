package com.wang.dao;

import java.util.List;

import com.wang.model.IconImageModel;

public interface IImageDAO{
	
//	private String[] imageIds;
//	private String[] imageUris;
//	private List<IconImageModel> imageEntityList;
	
//TODO 原有的逻辑改变
//	public IImageDAO() {
//		MySqlConnection mySqlConnection = MySqlConnection.getInstance();
//		Connection connection = mySqlConnection.getConnection();
//		imageEntityList = new ArrayList<IconImageModel>();
//		IconImageModel item = new IconImageModel();
//		
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from image");
//			while (resultSet.next()) {
//				item.setIconId(resultSet.getString(1));
//				item.setUri(resultSet.getString(2));
//				imageEntityList.add(item);
//				item = new IconImageModel();
//            }
//			
//			imageIds = new String[imageEntityList.size()];
//			imageUris = new String[imageEntityList.size()];
//			
//			for(int i = 0; i < imageEntityList.size(); i++){
//				item = imageEntityList.get(i);
//				imageIds[i] = item.getIconId();
//				imageUris[i] = item.getUri();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

	
	public boolean updateUserImage(String id, String imageUri);
	
	public List<String> getAllImgIds();
	
	public List<String> getAllImgUris();
	
	public List<IconImageModel> getAllImgs();
	
	public void deleteImg(String imageId);
	public IconImageModel getImg(String imageId);
	
	
}
