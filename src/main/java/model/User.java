package model;
import java.sql.*;

public class User {

	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
	
public String insertuser(String code,  String name, String nic, String phone, String email, String address, String password )

	
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database"; 
	 } 
	 // create a prepared statement
	 String query = "insert into user (`userID`, `userCode`,`userName`,`nic`,`phone`,`email`,`address`, `password`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, code); 
	 preparedStmt.setString(3, name); 
	 preparedStmt.setString(4, nic); 
	 preparedStmt.setString(5, phone);
	 preparedStmt.setString(6, email);
	 preparedStmt.setString(7, address);
	 preparedStmt.setString(8, password);
	
	//execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "User Inserted successfully"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while inserting"; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}



public String readuser()
		{ 
		 String output = ""; 
		try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		
				 
				 
				 
				// Prepare the html table to be displayed
				 output = "<table border='1'><tr>"
						+ "<th>User ID</th>"
				 		+ "<th>User Code</th>"
				 		+ "<th>User Name</th>" 
				 		+"<th>User NIC</th>"  
				 		+"<th>Phone</th>" 
				 		+"<th>Email</th>" 
				 		+"<th>Address</th>" 
				 		+"<th>Password</th>" 
				 		+"<th>Update</th><th>Remove</th></tr>"; 
				 
				 
				 
				 
		 String query = "select * from user"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String userID = Integer.toString(rs.getInt("userID")); 
		 String userCode = rs.getString("userCode"); 
		 String userName = rs.getString("userName"); 
		 String nic = rs.getString("nic"); 
		 String phone = rs.getString("phone"); 
		 String email = rs.getString("email"); 
		 String address = rs.getString("address"); 
		 String password = rs.getString("password"); 
		
		 
		
		
		 // Add into the html table
		 output += "<tr><td>" + userID + "</td>"; 
		 output += "<td>" + userCode + "</td>";
		 output += "<td>" + userName + "</td>"; 
		 output += "<td>" + nic + "</td>"; 
		 output += "<td>" + phone + "</td>"; 
		 output += "<td>" + email + "</td>";
		 output += "<td>" + address + "</td>";
		 output += "<td>" + password + "</td>";
		 
		
		// buttons
		
		 
		 
		 output += "<td><input name='btnUpdate' type='button' value='Update'></td>"
				 + "<td><form method='post' action='User.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove'>"
				 + "<input name='userID' type='hidden' value='" + userID + "'>" 
				 + "</form></td></tr>";
		
		 } 
		 
		 
		 
		
		 
		 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}


public String deleteUser(String userID) 
		{ 
		 String output = ""; 
		try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from user where userID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(userID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "User Deleted successfully"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}


public String updateUser(String ID, String code, String name, String nic, String phone, String email, String address, String password ) 
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE user SET userCode=?,userName=?,nic=?,phone=? , email=?, address=?, password=?  WHERE userID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, code); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, nic); 
	 preparedStmt.setString(4, phone);
	 preparedStmt.setString(5, email);
	 preparedStmt.setString(6, address);
	 preparedStmt.setString(7, password);
	
	 preparedStmt.setInt(8, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "User Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
