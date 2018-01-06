package com.wang.DBConnection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnection {

	private static String url = "jdbc:mysql://localhost:3306/web_qq_db?"
            + "user=root&password=&useUnicode=true&characterEncoding=UTF8";
	
	private  Connection conn = null;
	
	private static MySqlConnection mySqlConnection = null;
	
	private MySqlConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static MySqlConnection getInstance(){
		if(mySqlConnection == null)
			mySqlConnection = new MySqlConnection();
		return mySqlConnection;
	}
	
	public  Connection getConnection(){
		if(conn != null)
			return conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动程序");
			
			conn = DriverManager.getConnection(url);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public  void destoryConnection(){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
}
