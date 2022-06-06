package com.highradius;

import java.sql.*;
import java.sql.SQLException;

public class DBconnection {
	public static Connection createConnect() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/grey_goose";
		String uname = "root";
		String pass = "Sunday@01";
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, uname, pass);
			System.out.println("DB Connection successful - " +con);
			
		}
		catch(SQLException e)
		{
			System.out.println("Error Occurred");
			e.printStackTrace();
		}
		return con;
		
	}

}
