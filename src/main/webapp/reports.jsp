<%@ page isELIgnored="false" %>

<%@ taglib prefix="jc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<%@ page import = "model.Contribution" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<style>  </style>
<h1>${applicationScope.applicationLabel}</h1>
      <h2>Contribution reports</h2>
<form action="./contributionReport.jsp" method="post">
   <div class="form-group">
<label for="exampleInputEmail1">Contribution Type</label>
<select class="form-control" name="type"  id="exampleInputName" placeholder="Type">


   <option>Daily/monthly</option>
   <option>penalty</option>
   <option>welfare</option>
   <option>payLoan</option>
   <option>Registration</option>

</select>
</div>
<button type="submit" class="btn btn-primary mr-2">Go</button>
</form>


<h2>Loan reports</h2>
<form action="./loanReport.jsp" method="post">
   <div class="form-group">
<label for="exampleInputEmail1">loan Type report</label>
<select class="form-control" name="status"  id="exampleInputName" placeholder="Status">


   <option>Approved</option>
   <option>pending</option>
   <option>declined</option>
   <option>defaulted</option>


</select>
</div>
<button type="submit" class="btn btn-primary mr-2">Go</button>
</form>

