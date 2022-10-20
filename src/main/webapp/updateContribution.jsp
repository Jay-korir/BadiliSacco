<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />
<%@ page import = "model.Contribution" %>
   <h2>Contribution</h2>
   <jsp:useBean id = "contributionController"  class = "controller.ContributionController" />
  <% Contribution contribution = new Contribution(); %>
<form action="./updateContribution" method="post">

<table>
   <tr> <td>Username: </td> <td> <input type="text"   name="username" value="<%= contribution.getUsername() %>"> </td> </tr>
  <tr> <td> month: </td> <td> <input type="text"  name="month" value="<%= contribution.getMonth() %>"> </td> </tr>
   <tr> <td> amount: </td> <td> <input type="text" name="amount" value="<%= contribution.getAmount() %>"> </td> </tr>


<tr> <td> <input class="button" type="submit" value="Submit"></tr>
</table>
</form>
    <%
        String loginError = (String) application.getAttribute("loginError");

       if (loginError != null && !loginError.equals("")) {
       %>
         <span style="color:red"> ${applicationScope.loginError} %> </span><br/>
        <% }
    %>
        <a href='./dashboard.jsp'>Back</a>
       </body>
       </div>
         </div>
</html>