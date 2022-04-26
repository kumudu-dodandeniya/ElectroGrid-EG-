package com;

import model.Payment;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
			

			@FormParam("customerName") String customerName,
			@FormParam("accountNum") String accountNum,
			@FormParam("Paydate") String Paydate,
			@FormParam("Pemail") String Pemail,
			@FormParam("totAmount") String totAmount ) {
		
		String output = PaymentObj.insertPayment(customerName, accountNum, Paydate, Pemail, totAmount );
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject pObj = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String PaymentID = pObj.get("PaymentID").getAsString();
		String customerName = pObj.get("customerName").getAsString();
		String accountNum = pObj.get("accountNum").getAsString();
		String Paydate = pObj.get("Paydate").getAsString();
		String Pemail = pObj.get("Pemail").getAsString();
		String totAmount = pObj.get("totAmount").getAsString();


		String output = PaymentObj.updatePayment(PaymentID, customerName, accountNum, Paydate, Pemail, totAmount );
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element <Tid>
		String PaymentID = doc.select("PaymentID").text();
		String output = PaymentObj.deletePayment(PaymentID);
		return output;
	}
}
