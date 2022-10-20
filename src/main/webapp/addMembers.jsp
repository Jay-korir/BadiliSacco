<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />

   <h2>Members</h2>
  <form action="./add" method="post">
  <table>
      <tr> <td>Firstname: </td> <td> <input type="text" name="firstName"> </td> </tr>
       <tr> <td>lastname: </td> <td> <input type="text" name="lastName"> </td> </tr>
       <tr> <td>Username: </td> <td> <input type="text" name="userName"> </td> </tr>
      <tr> <td> Email: </td> <td> <input type="text" name="email"> </td> </tr>
       <tr> <td> Phone: </td> <td> <input type="text" name="phone"> </td> </tr>


    <tr> <td> <input class="button" type="submit" value="Submit"></tr>
  </table>
   </form>
<%
String addError = (String) application.getAttribute("addError");

if (addError != null && !addError.equals("")) {
%>
 <span style="color:red"> ${applicationScope.addError}  </span><br/>
<% }
%>
<a href='./dashboard.jsp'>Back</a>

</body>
</html>