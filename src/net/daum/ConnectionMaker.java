package net.daum;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	public abstract Connection getConnection() throws ClassNotFoundException,SQLException;
}