<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Loan" %>
<%@ page import="java.sql.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   <h2> Update Loan</h2>





<form action="./updateLoan" method="post">
<% Long a = Long.valueOf(Integer.parseInt(request.getParameter("id")));
 pageContext.setAttribute("id", a);
%>
<jsp:useBean id = "loanView"  class = "view.LoanView" />


   <c:set var="loan" value = "${loanView.getUserLoan(id)}" />


<div class="row">
        <div class="col-md-6 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">

        <div class="form-group">

      <input type="hidden" name="id" class="form-control" id="exampleInputUsername1" placeholder="id" value="${loan.id}">
<label for="exampleInputUsername1">  Username</label>
  <input type="text" name="username" class="form-control" id="exampleInputUsername1" placeholder="Username" value="${loan.username}">
</div>
<div class="form-group">
           <label>Id Number</label>
           <input type="text" name= "idNumber" class="form-control p_input" value="${loan.idNumber}">
         </div>
<div class="form-group">
  <label for="exampleInputPassword1">Amount Loaned</label>
  <input type="text" name="loanAmount" class="form-control" id="exampleInputPassword1" placeholder="Amount" value="${loan.loanAmount}">
</div>
<div class="form-group">
  <label for="exampleInputEmail1">Payment period</label>
  <input type="number" name="period" class="form-control" id="exampleInputName" placeholder="period in months" value="${loan.period}">
</div>

  <div class="form-group">
<label for="exampleInputUsername1">Purpose </label>
  <select class="form-control" name="purpose" class="form-control" id="exampleInputUsername1" placeholder="loan purpose" value="${loan.purpose}">
    <option>Education</option>
    <option>wedding</option>
    <option>health</option>
<option>business</option>
<option>others</option>
<option>Loan&Penalty</option>

 </select>
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
        <a href='./loanPage.jsp'>Back</a>
       </body>
       </div>
         </div>
</html>