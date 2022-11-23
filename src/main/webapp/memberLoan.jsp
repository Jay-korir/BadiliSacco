<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
   <h2>Loan</h2>
<form action="./loan" method="post">

<div class="row">
        <div class="col-md-6 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">

        <div class="form-group">
<label for="exampleInputUsername1">  Username</label>
  <input type="text" maxlength="10" name="username" class="form-control" id="exampleInputUsername1" placeholder="Username" value=${username}>
</div>
<div class="form-group">
           <label>Id Number</label>
           <input type="text" maxlength="8" name= "idNumber" class="form-control p_input">
         </div>
<div class="form-group">
  <label for="exampleInputPassword1">Amount Loaned</label>
  <input type="number"  max="6" name="loanAmount" class="form-control" id="exampleInputPassword1" placeholder="Amount">
</div>
<div class="form-group">
  <label for="exampleInputEmail1">Payment period</label>
  <input type="number"  max="2"name="period" class="form-control" id="exampleInputName" placeholder="period in months">
</div>
      <div class="form-group">
<label for="exampleInputUsername1">Purpose </label>
  <select class="form-control" name="purpose" class="form-control" id="exampleInputUsername1" placeholder="loan purpose">
    <option>Education</option>
    <option>wedding</option>
    <option>health</option>
<option>business</option>
<option>others</option>

 </select>
</div>
<button type="submit" class="btn btn-primary mr-2">Submit</button>



</form>


    <%
        String loanError = (String) application.getAttribute("loanError");

       if (loanError != null && !loanError.equals("")) {
       %>
         <span style="color:red"> ${applicationScope.loanError} </span><br/>
        <% }
    %>
        <a href='./userDashboard.jsp'>Back</a>
       </body>
       </div>
         </div>
</html>