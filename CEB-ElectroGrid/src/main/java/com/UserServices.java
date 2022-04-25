package com;

import model.User;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")


public class UserServices {
	
	
	User user1 = new User();

	/*@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems1()
	{
	return "Hello";
	}
	*/
	
	
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser()
	{
	return user1.readUser();
	}
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("user_Name") String user_Name,
	@FormParam("user_nic") String user_nic,
	@FormParam("user_Email") String user_Email,
	@FormParam("user_Contact") String user_Contact,
	@FormParam("user_password") String user_password)
	
	{
	String output = user1.insertUser(user_Name, user_nic, user_Email, user_Contact,user_password);
	return output;
	

	}
	
	
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String user_id = doc.select("user_id").text();
	String output = user1.deleteUser(user_id);
	return output;
	}
	
	
	
	
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	String user_id = itemObject.get("user_id").getAsString();
	String user_Name = itemObject.get("user_Name").getAsString();
	String user_Nic = itemObject.get("user_Nic").getAsString();
	String user_Email = itemObject.get("user_Email").getAsString();
	String user_Contact = itemObject.get("user_Contact").getAsString();
	String user_password = itemObject.get("user_password").getAsString();
	String output = user1.updateUser(user_id, user_Name, user_Nic, user_Email, user_Contact,user_password);
	return output;
	}
	
	

}
