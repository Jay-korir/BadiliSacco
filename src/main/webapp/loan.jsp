<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />
   <h2>Loan</h2>
<form action="./loan_up.jsp" method="post">
<table>
   <tr> <td>Username: </td> <td> <input type="text" name="username"> </td> </tr>
     <tr> <td> amount: </td> <td> <input type="text" name="amount"> </td> </tr>
      <tr> <td> payment period: </td> <td> <input type="number" name="period"> </td> </tr>
      <tr> <td> purpose: </td> <td> <input type="text" name="purpose"> </td> </tr>

<tr> <td> <input class="button" type="submit" value="Submit"></tr>
</table>
</form>


    <%
        String loginError = (String) application.getAttribute("loginError");

       if (loginError != null && !loginError.equals("")) {
       %>
         <span style="color:red"> ${applicationScope.loginError} </span><br/>
        <% }
    %>
        <a href='./dashboard.jsp'>Back</a>
       </body>
       </div>
         </div>
</html>