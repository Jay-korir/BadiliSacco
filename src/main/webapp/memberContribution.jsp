<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
   <h2>Contribution</h2>
<form action="./memberContribution" method="post">



<div class="row">
<div class="col-md-6 grid-margin stretch-card">
<div class="card">
  <div class="card-body">
      <div class="form-group">
<label for="exampleInputUsername1"> Contributor Username</label>
<input type="text" maxlength="10" name="username" class="form-control" id="exampleInputUsername1" placeholder="Username" value=${username}>
  </div>
   <div class="form-group">
  <label for="exampleInputUsername1"> Contributor Id</label>
  <input type="text" maxlength="8" name="idNumber" class="form-control" id="exampleInputUsername1" placeholder="idNumber">
    </div>
          <div class="form-group">
     <label for="exampleInputEmail1">Contribution Month</label>
     <select class="form-control" name="month"  id="exampleInputName" placeholder="Month">


        <option>January</option>
        <option>February</option>
        <option>March</option>
        <option>April</option>
        <option>May</option>
        <option>June</option>
        <option>July</option>
        <option>August</option>
        <option>September</option>
        <option>October</option>
        <option>November</option>
        <option>December</option>
     </select>
     </div>
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
  <div class="form-group">
<label for="exampleInputPassword1">Amount contribution</label>
<input type="number" max="6" name="amount" class="form-control" id="exampleInputPassword1" placeholder="Amount">
   </div>
<button type="submit" class="btn btn-primary mr-2">Submit</button>


</form>
    <%
        String addError = (String) application.getAttribute("addError");

       if (addError != null && !addError.equals("")) {
       %>
         <span style="color:red"> ${applicationScope.addError}  </span><br/>
        <% }
    %>
        <a href='./dashboard.jsp'>Back</a>
         </div>
           </div>
       </body>
           </div>
            </div>

</html>