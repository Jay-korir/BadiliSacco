<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />
   <h2>Loan</h2>
<form action="./loan_up.jsp" method="post">

<div class="row">
        <div class="col-md-6 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">

        <div class="form-group">
<label for="exampleInputUsername1">  Username</label>
  <input type="text" name="username" class="form-control" id="exampleInputUsername1" placeholder="Username">
</div>

<div class="form-group">
  <label for="exampleInputPassword1">Amount Loaned</label>
  <input type="text" name="amount" class="form-control" id="exampleInputPassword1" placeholder="Amount">
</div>
<div class="form-group">
  <label for="exampleInputEmail1">Payment period</label>
  <input type="number" name="period" class="form-control" id="exampleInputName" placeholder="period in months">
</div>
      <div class="form-group">
<label for="exampleInputUsername1">Purpose </label>
  <input type="text" name="purpose" class="form-control" id="exampleInputUsername1" placeholder="loan purpose">
</div>
<button type="submit" class="btn btn-primary mr-2">Submit</button>



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