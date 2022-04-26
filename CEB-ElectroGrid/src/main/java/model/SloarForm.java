package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SloarForm {
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
	public String insertSolarForm(String account_Number, String full_Name, String user_nic,
			String address,String contact_Number,String email_Address,String bank_account_Number,String bank_Branch) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into solar_Request_form (soloar_Form_Id,account_Number,full_Name,user_nic,address,contact_Number,email_Address,bank_account_Number,bank_Branch) values (?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, account_Number);
			preparedStmt.setString(3, full_Name);
			preparedStmt.setString(4, user_nic);
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, contact_Number);
			preparedStmt.setString(7, email_Address);
			preparedStmt.setString(8, bank_account_Number);
			preparedStmt.setString(9, bank_Branch);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Solar Request Submit successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// read

	public String readSolarForm() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Soloar Form Id</th>" + "<th>Account Number</th>" + "<th>Full Name</th>"
					+ "<th>User Nic</th>" + "<th>Address</th>" + "<th>Contact Number</th>"+ "<th>Email Address</th>"+ "<th>Bank Account Number</th>"+ "<th>Bank Branch</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from solar_Request_form";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String soloar_Form_Id = Integer.toString(rs.getInt("soloar_Form_Id"));
				String account_Number = rs.getString("account_Number");
				String full_Name = rs.getString("full_Name");
				String user_nic = rs.getString("user_nic");
				String address = rs.getString("address");
				String contact_Number = rs.getString("contact_Number");
				String email_Address = rs.getString("email_Address");
				String bank_account_Number = rs.getString("bank_account_Number");
				String bank_Branch = rs.getString("bank_Branch");
				
				// Add a row into the html table
				output += "<tr><td>" + soloar_Form_Id + "</td>";
				output += "<td>" + account_Number + "</td>";
				output += "<td>" + full_Name + "</td>";
				output += "<td>" + user_nic + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + contact_Number + "</td>";
				output += "<td>" + email_Address + "</td>";
				output += "<td>" + bank_account_Number + "</td>";
				output += "<td>" + bank_Branch + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' " + " type='button' value='Update'></td>"
						+ "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' "
						+ " type='submit' value='Remove'>" + "<input name='itemID' type='hidden' " + " value='"
						+ soloar_Form_Id + "'>" + "</form></td></tr>";
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

	public String deleteSolarForm(String soloar_Form_Id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from solar_Request_form where soloar_Form_Id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(soloar_Form_Id));
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
	public String updateSolarForm(String soloar_Form_Id, String account_Number, String full_Name, String user_nic, String address,
			String contact_Number,String email_Address,String bank_account_Number,String bank_Branch)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE solar_Request_form SET soloar_Form_Id=?,account_Number=?,full_Name=?,user_nic=?,address=?,contact_Number=?,email_Address=?,bank_account_Number=?,bank_Branch=? WHERE soloar_Form_Id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, account_Number);
			preparedStmt.setString(2, full_Name);
			preparedStmt.setString(3, user_nic);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, contact_Number);
			preparedStmt.setString(6, email_Address);
			preparedStmt.setString(7, bank_account_Number);
			preparedStmt.setString(8, bank_Branch);
			preparedStmt.setInt(9, Integer.parseInt(soloar_Form_Id));
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
//good
