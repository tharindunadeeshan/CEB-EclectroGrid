package com;

import model.SloarForm;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/SloarForm")
public class SloarFormServices {
	
	SloarForm solar1 = new SloarForm();

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
	return solar1.readSolarForm();
	}
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("account_Number") String account_Number,
	@FormParam("full_Name") String full_Name,
	@FormParam("user_nic") String user_nic,
	@FormParam("address") String address,
	@FormParam("contact_Number") String contact_Number,
	@FormParam("email_Address") String email_Address,
	@FormParam("bank_account_Number") String bank_account_Number,
	@FormParam("bank_Branch") String bank_Branch)

	{
	
	String output = solar1.insertSolarForm(account_Number, full_Name, user_nic, address,contact_Number,email_Address,bank_account_Number,bank_Branch);
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
	String soloar_Form_Id = doc.select("soloar_Form_Id").text();
	String output = solar1.deleteSolarForm(soloar_Form_Id);
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
	String soloar_Form_Id = itemObject.get("soloar_Form_Id").getAsString();
	String account_Number = itemObject.get("account_Number").getAsString();
	String full_Name = itemObject.get("full_Name").getAsString();
	String user_nic = itemObject.get("user_nic").getAsString();
	String address = itemObject.get("address").getAsString();
	String contact_Number = itemObject.get("contact_Number").getAsString();
	String email_Address = itemObject.get("email_Address").getAsString();
	String bank_account_Number = itemObject.get("bank_account_Number").getAsString();
	String bank_Branch = itemObject.get("bank_Branch").getAsString();
	
	String output = solar1.updateSolarForm(soloar_Form_Id, account_Number, full_Name, user_nic, address,contact_Number,email_Address,bank_account_Number,bank_Branch);
	return output;
	}

}
//done
