package com;

import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType; 

import com.google.gson.*; 

import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/users")
public class UserService {
	
	User userObj = new User(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String readuser() 
	 { 
		return userObj.readuser(); 
	 } 

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertuser(@FormParam("userCode") String userCode, 
	 @FormParam("userName") String userName, 
	 @FormParam("nic") String nic, 
	 @FormParam("phone") String phone, 
	 @FormParam("email") String email, 
	 @FormParam("address") String address,
	 @FormParam("password") String password) 
	 
	{ 
	 String output = userObj.insertuser( userCode, userName, nic, phone, email , address, password); 
	return output; 
	}
	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String userData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
	 
	//Read the values from the JSON object
	 String userID = userObject.get("userID").getAsString(); 
	 String userCode = userObject.get("userCode").getAsString(); 
	 String userName = userObject.get("userName").getAsString(); 
	 String nic = userObject.get("nic").getAsString(); 
	 String phone = userObject.get("phone").getAsString(); 
	 String email = userObject.get("email").getAsString(); 
	 String address = userObject.get("address").getAsString(); 
	 String password = userObject.get("password").getAsString(); 
	 
	 String output = userObj.updateUser(userID, userCode, userName, nic, phone , email, address, password); 
	 return output; 
	}
	
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteuser(String userData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String userID = doc.select("userID").text(); 
	 String output = userObj.deleteUser(userID); 
	return output; 
	}
	
	
}
