<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Members" %>
<%@ page import="java.sql.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   <h2> Update Member</h2>
   <jsp:useBean id = "membersController"  class = "controller.MembersController" />


  <%
  Members members = membersController.getMember((Connection) application.getAttribute("myConnection"),
  Integer.parseInt(request.getParameter("id")));
  pageContext.setAttribute("members",members);
  %>

<form action="./updateMember" method="post">
<table>
    <tr> <td>firstname: </td> <td> <input type="text"   name="firstName" value="${members.firstName}"> </td> </tr>
    <tr> <td>lastname: </td> <td> <input type="text"   name="lastName" value="${members.lastName}"> </td> </tr>
     <tr> <td>Username: </td> <td> <input type="text"   name="userName" value="${members.userName}"> </td> </tr>
     <tr> <td>email: </td> <td> <input type="text"   name="email" value="${members.email}"> </td> </tr>
     <tr> <td>phone: </td> <td> <input type="text"   name="phone" value="${members.phone}"> </td> </tr>



<button type="submit" class="btn btn-primary mr-2">Update</button>
</table>
</form>
    <%
        String addError = (String) application.getAttribute("addError");

       if (addError != null && !addError.equals("")) {
       %>
         <span style="color:red"> ${applicationScope.addError} </span><br/>
        <% }
    %>
        <a href='./membersPage.jsp'>Back</a>
       </body>
       </div>
         </div>
</html>