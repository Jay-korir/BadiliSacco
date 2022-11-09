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
<input type="text" name="username" class="form-control" id="exampleInputUsername1" placeholder="Username" value=${username}>
  </div>
       <div class="form-group">
<label for="exampleInputEmail1">Contribution Month</label>
<input type="text" name="month" class="form-control" id="exampleInputName" placeholder="Month">
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