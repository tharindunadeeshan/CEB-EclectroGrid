package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private static String url = "jdbc:mysql://localhost:3306/test123";
	private static String userName = "root";
	private static String password = "";

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			System.out.println("Database connection is not success!!!");
		}

		return con;
	}

	// insert method
	public String insertUser(String user_Name, String user_nic, String user_Email, String user_Contact,
			String user_password) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into user_register (user_id,user_Name,user_nic,user_Email,user_Contact,user_password) values (?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, user_Name);
			preparedStmt.setString(3, user_nic);
			preparedStmt.setString(4, user_Email);
			preparedStmt.setString(5, user_Contact);
			preparedStmt.setString(6, user_password);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "User register successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// read

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>user id</th>" + "<th>USer Name</th>" + "<th> user Nic</th>"
					+ "<th> user Email</th>" + "<th> user Contact</th>" + "<th> user password</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from user_register";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String user_id = Integer.toString(rs.getInt("user_id"));
				String user_Name = rs.getString("user_Name");
				String user_Nic = rs.getString("user_Nic");
				String user_Email = rs.getString("user_Email");
				String user_Contact = rs.getString("user_Contact");
				String user_password = rs.getString("user_password");
				// Add a row into the html table
				output += "<tr><td>" + user_id + "</td>";
				output += "<td>" + user_Name + "</td>";
				output += "<td>" + user_Nic + "</td>";

				output += "<td>" + user_Email + "</td>";
				output += "<td>" + user_Contact + "</td>";
				output += "<td>" + user_password + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' " + " type='button' value='Update'></td>"
						+ "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' "
						+ " type='submit' value='Remove'>" + "<input name='itemID' type='hidden' " + " value='"
						+ user_id + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// delete

	public String deleteUser(String user_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from user_register where user_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(user_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update
	public String updateUser(String user_id, String user_Name, String user_Nic, String user_Email, String user_Contact,
			String user_password)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE user_register SET user_id=?,user_Name=?,user_Nic=?,user_Email=?,user_Contact=?,user_password=? WHERE itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, user_Name);
			preparedStmt.setString(2, user_Nic);
			preparedStmt.setString(3, user_Email);
			preparedStmt.setString(4, user_Contact);
			preparedStmt.setString(5, user_password);
			preparedStmt.setInt(6, Integer.parseInt(user_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
