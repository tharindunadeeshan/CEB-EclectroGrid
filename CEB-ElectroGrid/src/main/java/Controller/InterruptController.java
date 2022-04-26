package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DBUtil.DBConnectionInterrupt;
import Model.Interrupt;

public class InterruptController {

	DBConnectionInterrupt dbObj = new DBConnectionInterrupt();

	//view
	public String viewInterrupts() {

		String output = "";
		
		Interrupt  i = new Interrupt();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
		}
			
		// Prepare the html table 
		output = "<table border=\"1\"><tr><th>Interrupt ID</th>"
					+ "<th>Province</th><th>District</th> "+" <th>Date</th> "+" <th>Interrupt Time</th> "+"<th>Restore Time</th></tr>";

		String query = "select * from interrupt";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		// iterate through the rows in the result set
		while (rs.next()) {

			i.setIdinterrupt(rs.getInt("idinterrupt"));
			i.setProvince(rs.getString("province"));
			i.setDistrict(rs.getString("district"));
			i.setDate(rs.getString("date"));
			i.setInterruptTime(rs.getDouble("interruptTime"));
			i.setRestoreTime(rs.getDouble("restoreTime"));
			
			// Add into the html table
			output += "<tr><td>" + i.getIdinterrupt() + "</td>";
			output += "<td>" + i.getProvince() + "</td>";
			output += "<td>" + i.getDistrict() + "</td>";
			output += "<td>" + i.getDate() + "</td>";
			output += "<td>" + i.getInterruptTime() + "</td>";
			output += "<td>" + i.getRestoreTime()+ "</td>";
				
			}
		
			con.close();
			
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Interrupt Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	

	
	//insert	
	public String addInterrupt(Interrupt i) {

		String output = "";
			
		try {

			Connection con = dbObj.connect();
				
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " INSERT INTO interrupt (province, district, date, interruptTime, restoreTime) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, i.getProvince());
			preparedStmt.setString(2, i.getDistrict());
			preparedStmt.setString(3, i.getDate());
			preparedStmt.setDouble(4, i.getInterruptTime());
			preparedStmt.setDouble(5, i.getRestoreTime());
				
				
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted a new interrupt record successfully";

			} catch (Exception e) {
				output = "Error while inserting";
				System.err.println(e.getMessage());
			}

			return output;
		}
	

		//update
		public String updateInterrupt(Interrupt i) {

			String output = "";

			try {
				Connection con = dbObj.connect();
				
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
					
				// create a prepared statement
				String query = "UPDATE interrupt SET province=?,district=?,date=?,interruptTime=?,restoreTime=? WHERE idinterrupt =?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				
				// binding values
				preparedStmt.setString(1, i.getProvince());
				preparedStmt.setString(2, i.getDistrict());
				preparedStmt.setString(3, i.getDate());
				preparedStmt.setDouble(4, i.getInterruptTime());
				preparedStmt.setDouble(5, i.getRestoreTime());
				preparedStmt.setInt(6, i.getIdinterrupt());
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully [ ID : "+i.getIdinterrupt()+" ]";
				
				} catch (Exception e) {
					output = "Error while updating the interrupt Id " + i.getIdinterrupt();
					System.err.println(e.getMessage());
				}
				return output;
				
			}


		 	//delete
			public String deleteInterrupt(Interrupt i) {
				
				String output = "";
				
				try {

					Connection con = dbObj.connect();
					
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "DELETE FROM interrupt WHERE idinterrupt=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					 preparedStmt.setInt(1, i.getIdinterrupt());
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully [ Interrupt Id : "+i.getIdinterrupt()+" ]";

				} catch (Exception e) {

					output = "Error while deleting the Interrupt Id :" + i.getIdinterrupt();
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			
			//view by fund id
			public String viewInterruptsById(int idinterrupt) {
				
				String output = "";
				
				Interrupt  i = new Interrupt();
				
				try {
					Connection con = dbObj.connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
				}
					
				// Prepare the html table 
				output = "<table border=\"1\"><tr><th>Interrupt ID</th>"
							+ "<th>Province</th><th>District</th> "+" <th>Date</th> "+" <th>Interrupt Time</th> "+"<th>Restore Time</th></tr>";

				String query = "select * from interrupt where idinterrupt = '"+idinterrupt+"'";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {

					i.setIdinterrupt(rs.getInt("idinterrupt"));
					i.setProvince(rs.getString("province"));
					i.setDistrict(rs.getString("district"));
					i.setDate(rs.getString("date"));
					i.setInterruptTime(rs.getDouble("interruptTime"));
					i.setRestoreTime(rs.getDouble("restoreTime"));
					
					// Add into the html table
					output += "<tr><td>" + i.getIdinterrupt() + "</td>";
					output += "<td>" + i.getProvince() + "</td>";
					output += "<td>" + i.getDistrict() + "</td>";
					output += "<td>" + i.getDate() + "</td>";
					output += "<td>" + i.getInterruptTime() + "</td>";
					output += "<td>" + i.getRestoreTime()+ "</td>";
						
					}
				
					con.close();
					
					// Complete the html table
					output += "</table>";

				} catch (Exception e) {
					output = "Error while reading the Interrupt Details.";
					System.err.println(e.getMessage());
				}

				return output; 
			}
			
			
}
