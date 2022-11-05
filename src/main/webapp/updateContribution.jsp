<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Contribution" %>
<%@ page import="java.sql.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   <h2> Update Contribution</h2>





<form action="./updateContribution" method="post">
<c:forEach items ="${contributionController.update}" var = "contribution">
<table>
   <tr> <td>Username: </td> <td> <input type="text"   name="username" value="${contribution.username}"> </td> </tr>
  <tr> <td> month: </td> <td> <input type="text"  name="month" value="${contribution.month}"> </td> </tr>
   <tr> <td> amount: </td> <td> <input type="text" name="amount" value="${contribution.amount}"> </td> </tr>


<button type="submit" class="btn btn-primary mr-2">Update</button>
</c:forEach>
</table>
</form>
    <%
        String contributionError = (String) application.getAttribute("contributionError");

       if (contributionError != null && !contributionError.equals("")) {
       %>
         <span style="color:red"> ${applicationScope.contributionError} </span><br/>
        <% }
    %>
        <a href='./contributionPage.jsp'>Back</a>
       </body>
       </div>
         </div>
</html>