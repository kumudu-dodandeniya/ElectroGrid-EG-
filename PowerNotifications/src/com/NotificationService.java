package com;

import model.Notification;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Notification")
public class NotificationService {
	Notification NotificationObj = new Notification();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readNotification() {
		return NotificationObj.readNotification();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertNotification(
			
	
			@FormParam("description") String description,
			@FormParam("adminName") String adminName,
			@FormParam("nDate") String nDate,
			@FormParam("zone") String zone) {
		
		String output = NotificationObj.insertNotification(description, adminName, nDate, zone);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateNotification(String notificationData) {
		// Convert the input string to a JSON object
		JsonObject nObj = new JsonParser().parse(notificationData).getAsJsonObject();

		// Read the values from the JSON object
		String NotificationID = nObj.get("NotificationID").getAsString();
		String description = nObj.get("description").getAsString();
		String adminName = nObj.get("adminName").getAsString();
		String nDate = nObj.get("nDate").getAsString();
		String zone = nObj.get("zone").getAsString();


		String output = NotificationObj.updateNotification(NotificationID, description, adminName, nDate, zone );
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteNotification(String notificationData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(notificationData, "", Parser.xmlParser());

		// Read the value from the element <Tid>
		String NotificationID = doc.select("NotificationID").text();
		String output = NotificationObj.deleteNotification(NotificationID);
		return output;
	}
}
