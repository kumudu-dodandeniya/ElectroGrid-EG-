<%@ page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<% 
 //Insert item---------------------------------- 
if (request.getParameter("userCode") != null) 
 { 
 Item itemObj = new Item(); 
 String stsMsg = itemObj.insertItem(request.getParameter("userCode"), 
 request.getParameter("userName"), 
 request.getParameter("userRate"), 
 request.getParameter("userConsumed"), 
 request.getParameter("userCharge")); 
 session.setAttribute("statusMsg", stsMsg); 
 } 

//Delete item----------------------------------
if (request.getParameter("userID") != null) 
{ 
Item itemObj = new Item(); 
String stsMsg = itemObj.deleteItem(request.getParameter("userID")); 
session.setAttribute("statusMsg", stsMsg); 
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
</head>
<body>
<h1>Generate bill</h1>
<form method="post" action="items.jsp">
 User code: <input name="userCode" type="text"><br> User
 name: <input name="userName" type="text"><br> User rate: 
 <input name="userRate" type="text"><br> User
 Consumed<input name="userConsumed" type="text"><br> User
 charge: <input name="userCharge" type="text"><br> <input
 name="btnSubmit" type="submit" value="Save">
</form>
<%
 out.print(session.getAttribute("statusMsg")); 
%>
<br>
<%
 Item itemObj = new Item(); 
 out.print(itemObj.readItems()); 
%>
</body>
</html>
