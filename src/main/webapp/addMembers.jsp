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
           <input type="text" maxlength="10" name= "firstname" class="form-control p_input">
         </div>
         <div class="form-group">
           <label>Last Name</label>
           <input type="text" maxlength="10" name= "lastname" class="form-control p_input">
         </div>
         <div class="form-group">
           <label>Username</label>
           <input type="text" maxlength="10" name= "username" class="form-control p_input">
         </div>
<div class="form-group">
           <label>Id Number</label>
           <input type="text" maxlength="8" name= "idNumber" class="form-control p_input">
         </div>

          <div class="form-group">
             <label>Email</label>
             <input type="email" maxlength="20"name= "email" class="form-control p_input">
           </div>
         <div class="form-group">
            <label>Phone</label>
           <input type="tel"  maxlength="10" name= "phone"class="form-control p_input">
         </div>
         <div class="form-group">
                     <label>Password</label>
                    <input type="password"  maxlength="4"name= "password"class="form-control p_input">
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