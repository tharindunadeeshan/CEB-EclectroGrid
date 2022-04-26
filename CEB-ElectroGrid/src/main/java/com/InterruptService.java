package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Controller.InterruptController;
import Model.Interrupt;

@Path("/interrupts")
public class InterruptService {

	InterruptController im = new InterruptController();

	
	//get
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInterrupt() {
		return im.viewInterrupts();
	}

	//getByID
	@GET
	@Path("/{idinterrupt}")
	@Produces(MediaType.TEXT_HTML)
	public String readInterruptById(@PathParam("idinterrupt") int idinterrupt) {
		return im.viewInterruptsById(idinterrupt);
	}

	//add
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterInterrupts(String TypeData) {
		
		try {
			if(TypeData == null) throw new Exception("Invaild data type");
			else {
				// Convert the input string to a JSON object
				JsonObject josnObj = new JsonParser().parse(TypeData).getAsJsonObject();
			
				Interrupt i = new Interrupt();
				
				i.setProvince(josnObj.get("province").getAsString());
				i.setDistrict(josnObj.get("district").getAsString());
				i.setDate(josnObj.get("date").getAsString());
				i.setInterruptTime(josnObj.get("interruptTime").getAsDouble());
				i.setRestoreTime(josnObj.get("restoreTime").getAsDouble());
				
				// Read the values from the JSON object
				String output = im.addInterrupt(i);
				return output;
			}
		}catch(Exception e) {
			return "<p> Somethings went wrong <br/> ERROR -  "+ e.toString() +" </p>";
		}
		

	}

	
	
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInterrupts(String TypeData) {

		try {
			if( TypeData == null ) throw  new Exception("Invalid data type");
			
			// Convert the input string to a JSON object
			JsonObject josnObj = new JsonParser().parse(TypeData).getAsJsonObject();
			Interrupt i = new Interrupt();

			i.setIdinterrupt(josnObj.get("idinterrupt").getAsInt());
			i.setProvince(josnObj.get("province").getAsString());
			i.setDistrict(josnObj.get("district").getAsString());
			i.setDate(josnObj.get("date").getAsString());
			i.setInterruptTime(josnObj.get("interruptTime").getAsDouble());
			i.setRestoreTime(josnObj.get("restoreTime").getAsDouble());
				
				
			String output = im.updateInterrupt(i);
			return output;
			
		}catch(Exception e) {
			return "<p> Somethings went wrong <br> ERROR - "+e.toString()+" </p>";
		}
		
		
	}
	

	//delete
		
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInterrupts(String TypeData) {
		try {
			if(TypeData == null ) throw new Exception("Invalid Id");
			// Convert the input string to a JSON object
			JsonObject doc = new JsonParser().parse(TypeData).getAsJsonObject();

			Interrupt i = new Interrupt();
				
			// Read the value from the element <ID>
		   	i.setIdinterrupt(doc.get("idinterrupt").getAsInt());
				
			String output = im.deleteInterrupt(i);
			return output;
		}catch(Exception e) {
			return "<p> Somethings went wrong <br> ERROR - "+e.toString()+ " </p>";
		}
		
		
	}
	
}

// done
