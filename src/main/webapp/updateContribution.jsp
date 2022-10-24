<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<%@ page import = "model.Contribution" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   <h2>Contribution</h2>
   <jsp:useBean id = "contributionController"  class = "controller.ContributionController" />
  <% Contribution contribution = new Contribution(); %>

  <%
  List<Contribution> contributions = contributionController.list((Connection) application.getAttribute("myConnection"), new Contribution());
  pageContext.setAttribute("contributions",contributions);
  %>
<tr>name=${username}</tr>
<form action="./updateContribution" method="post">
<table>
<c:forEach items ="${contributions}" var = "contribution">

   if("${contribution.username}"== ${username})

   <tr> <td>Username: </td> <td> <input type="text"   name="username" value="${contribution.username}"> </td> </tr>
  <tr> <td> month: </td> <td> <input type="text"  name="month" value="${contribution.month}"> </td> </tr>
   <tr> <td> amount: </td> <td> <input type="text" name="amount" value="${contribution.amount}"> </td> </tr>

</c:forEach>
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