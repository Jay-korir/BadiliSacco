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
<% Long a = Long.valueOf(Integer.parseInt(request.getParameter("id")));
 pageContext.setAttribute("id", a);
%>
<jsp:useBean id = "contributionView"  class = "view.ContributionView" />


   <c:set var="contribution" value = "${contributionView.getMyContribution(id)}" />

<div class="row">
<div class="col-md-6 grid-margin stretch-card">
<div class="card">
  <div class="card-body">
      <div class="form-group">
      <input type="hidden" name="id" class="form-control" id="exampleInputUsername1" placeholder="id" value="${contribution.id}">
<label for="exampleInputUsername1"> Contributor Username</label>
<input type="text" name="username" class="form-control" id="exampleInputUsername1" placeholder="Username" value="${contribution.username}">
  </div>
  <div class="form-group">
    <label for="exampleInputUsername1"> Contributor Id</label>
    <input type="text" name="idNumber" class="form-control" id="exampleInputUsername1" placeholder="idNumber" value="${contribution.idNumber}">
      </div>
       <div class="form-group">
<label for="exampleInputEmail1">Contribution Month</label>
<input type="text" name="month" class="form-control" id="exampleInputName" placeholder="Month" value="${contribution.month}">
           </div>
  <div class="form-group">
<label for="exampleInputPassword1">Amount contribution</label>
<input type="text" name="amount" class="form-control" id="exampleInputPassword1" placeholder="Amount" value="${contribution.amount}">
   </div>
<button type="submit" class="btn btn-primary mr-2">Update</button>

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