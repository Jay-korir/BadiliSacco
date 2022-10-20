<%@ page isELIgnored="false" %>
<%@ page import = "model.Contribution" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>


<jsp:useBean id = "contributionController"  class = "controller.ContributionController" />

<%!

	String contributionName;
	String monthName;

%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./assets/CSS/style.css"/>
</head>
<body bgcolor="Lightskyblue">
<h1><%= application.getAttribute("applicationLabel") %></h1>
<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Contribution <a href='./addContribution.jsp'>Add Contribution</a><br/>
<br/>
<h1> Contribution </h1>
<table>
<tr>
    <th>Contributor username</th>
    <th>Contribution month</th>
    <th>Contributed amount</th>
    <th></th>
</tr>

<%
    List<Contribution> contributions = contributionController.list((Connection) application.getAttribute("myConnection"), new Contribution());
    for (Contribution contribution : contributions) {
     contributionName = contribution.getUsername();
     monthName = contribution.getMonth();
%>

    <tr>
        <td>${contributionName}</td>
        <td>${monthName}</td>
        <td><%= contribution.getAmount() %></td>
        <td><a href="./edit?id=<%= contribution.getId() %>">Edit</a>  | <a href="./deleteContribution?id=<%= contribution.getId() %>">Delete</a></td>
    </tr>

<% } %>

</table>

<br/>Logout <a href='./logout'>Logout</a><br/>
</body>
</html>