package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/egsys", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String insertbilling(String AccNo, String BillUnit)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting!."; }
	 // create a prepared statement
	 String query = " insert into bill_calculation(`bID`,`AccNo`,`BillUnit`)"
	 + " values (?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, AccNo);
	 preparedStmt.setString(3, BillUnit);
	 
	 // execute the statement

	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully !";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	

public String readbilling()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>AccNo</th><th>BillUnits</th>" +

 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from bill_calculation";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String bID = Integer.toString(rs.getInt("bID"));
 String AccNo = rs.getString("AccNo");
 String BillUnit = rs.getString("BillUnit");
 
 // Add into the html table

 output += "<td>" + BillUnit + "</td>";
 output += "<td>" + AccNo + "</td>";
 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
 + "<input name='bID' type='hidden' value='" + bID
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the nitems.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String updatebilling(String bID,String AccNo, String BillUnit)

{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE bill_calculation SET AccNo=?,BillUnit=? WHERE bID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, AccNo);
preparedStmt.setString(2, BillUnit);
preparedStmt.setInt(3, Integer.parseInt(bID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully!";
}
catch (Exception e)
{
output = "Error while updating the Billing!.";
System.err.println(e.getMessage());
}
return output;
}



public String deletebilling(String bID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from bill_calculation where bID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(bID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Deleted successfully";
 }
 catch (Exception e)
 {
 output = "Error while deleting the Billings.";
 System.err.println(e.getMessage());
 }
 return output;
 }


}
