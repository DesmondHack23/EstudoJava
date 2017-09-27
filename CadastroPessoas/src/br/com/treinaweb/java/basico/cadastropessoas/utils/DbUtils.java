package br.com.treinaweb.java.basico.cadastropessoas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {

	public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.out.println("Conectando ao banco.");
		return DriverManager.getConnection("jdbc:mysql://localhost:8484/treinaweb_jse", "root", "Andrius@@rduino");
	}
	
	public static ResultSet getResultSet(Connection conn, String sql)
				throws SQLException {
		Statement statement = conn.createStatement();
		return statement.executeQuery(sql);
	}

	public static PreparedStatement getPrepareStatement(Connection conn, String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
}
