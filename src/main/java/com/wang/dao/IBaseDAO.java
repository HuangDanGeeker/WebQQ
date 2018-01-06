package com.wang.dao;

import java.util.List;

import com.sun.istack.internal.FinalArrayList;

public interface IBaseDAO {

	public Object get(String id);
	public boolean delete(String id);
	public List getAll();
	
	public <T> List<T> executeSql(final String sql);
}
