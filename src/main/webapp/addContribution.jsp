<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
   <h2>Contribution</h2>
<form action="./contribution" method="post">

<div class="row">
<div class="col-md-6 grid-margin stretch-card">
<div class="card">
  <div class="card-body">
      <div class="form-group">
<label for="exampleInputUsername1"> Contributor Username</label>
<input type="text" name="username" class="form-control" id="exampleInputUsername1" placeholder="Username">
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

</select>
</div>
  <div class="form-group">
<label for="exampleInputPassword1">Amount contribution</label>
<input type="text" name="amount" class="form-control" id="exampleInputPassword1" placeholder="Amount">
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