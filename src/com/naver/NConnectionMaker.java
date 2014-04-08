package com.naver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.daum.ConnectionMaker;

public class NConnectionMaker implements ConnectionMaker {
	public Connection getConnection() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju?useUnicode=true&characterEncoding=utf8","jeju","jejupw");
		return connection;
	}
}
