package com.wang.service;

import java.util.List;

public interface IService {

	public Object get(String id);
	public boolean delete(String id);
	public List getAll();
	
	
}
