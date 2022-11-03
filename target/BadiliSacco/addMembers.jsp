<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
   <h2>Members</h2>
  <form action="./add" method="post">
<div class="col-md-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
        <div class="form-group">
           <label>First Name</label>
           <input type="text" name= "firstName" class="form-control p_input">
         </div>
         <div class="form-group">
           <label>Last Name</label>
           <input type="text" name= "lastName" class="form-control p_input">
         </div>
         <div class="form-group">
           <label>Username</label>
           <input type="text" name= "userName" class="form-control p_input">
         </div>

          <div class="form-group">
             <label>Email</label>
             <input type="email" name= "email" class="form-control p_input">
           </div>
         <div class="form-group">
            <label>Phone</label>
           <input type="tel" name= "phone"class="form-control p_input">
         </div>
<button type="submit" class="btn btn-primary mr-2">Submit</button>
<a href='./dashboard.jsp'>Back</a>

   </form>
<%
String addError = (String) application.getAttribute("addError");

if (addError != null && !addError.equals("")) {
%>
 <span style="color:red"> ${applicationScope.addError}  </span><br/>
<% }
%>
</div>
</div>
</div>


</body>
</html>