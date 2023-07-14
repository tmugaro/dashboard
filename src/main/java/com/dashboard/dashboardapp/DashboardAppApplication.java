package com.dashboard.dashboardapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class DashboardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardAppApplication.class, args);

		Connection conn = null;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/patient", "username", "password");

		conn.close();
		System.out.println("Connection closed.");
	} catch (SQLException se) {
		// Handle errors for JDBC
		se.printStackTrace();
	} catch (Exception e) {
		// Handle errors for Class.forName
		e.printStackTrace();
	} finally {
		// Finally block used to close resources
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end finally try
	} // end try
        System.out.println("Goodbye!");
} // end main
} // end MySQLConnection

