<%@ page import = "model.Members" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<jsp:useBean id = "membersController"  class = "controller.MembersController" />


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./assets/CSS/style.css"/>
</head>
<body bgcolor="Lightskyblue">
<h1><%= application.getAttribute("applicationLabel") %></h1>
<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Members <a href='./addMembers.jsp'>Add Members</a><br/>
<br/>


<h1> Members </h1>
<table>
<tr>
     <th>firstname</th>
    <th> lastname</th>
    <th> username</th>
    <th>email</th>
    <th>phone</th>
    <th></th>
</tr>
<%

     List<Members> members = membersController.list((Connection) application.getAttribute("myConnection"), new Members());
    for (Members member : members) {
%>
    <tr>
        <td><%= member.getFirstName() %></td>
        <td><%= member.getLastName() %></td>
        <td><%= member.getUserName() %></td>
        <td><%= member.getEmail() %></td>
        <td><%= member.getPhone() %></td>
        <td><a href="./edit">Edit</a>  | <a href="./delete">Delete</a></td>
    </tr>

<% } %>

</table>




<br/>Logout <a href='./logout'>Logout</a><br/>
</body>
</html>