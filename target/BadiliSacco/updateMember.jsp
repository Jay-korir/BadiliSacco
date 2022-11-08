<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Members" %>
<%@ page import="java.sql.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   <h2> Update Member</h2>


 <% Long a = Long.valueOf(Integer.parseInt(request.getParameter("id")));
  pageContext.setAttribute("id", a);
 %>

 <jsp:useBean id = "memberController"  class = "controller.MembersBean" />


    <c:set var="members" value = "${memberController.getMember(id)}" />

<form action="./updateMember" method="post">

<div class="col-md-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
        <div class="form-group">
         <input type="hidden" name="id" class="form-control" id="exampleInputUsername1" placeholder="id" value="${members.id}">
          <input type="hidden" name="password" class="form-control" id="exampleInputUsername1" placeholder="id" value="${members.password}">
          <label>First Name</label>
           <input type="text" name= "firstname" class="form-control p_input" value="${members.firstname}">
         </div>
         <div class="form-group">
           <label>Last Name</label>
           <input type="text" name= "lastname" class="form-control p_input" value="${members.lastname}">
         </div>
         <div class="form-group">
           <label>Username</label>
           <input type="text" name= "username" class="form-control p_input" value="${members.username}">
         </div>

          <div class="form-group">
             <label>Email</label>
             <input type="email" name= "email" class="form-control p_input" value="${members.email}">
           </div>
         <div class="form-group">
            <label>Phone</label>
           <input type="tel" name= "phone"class="form-control p_input" value="${members.phone}">
         </div>

<button type="submit" class="btn btn-primary mr-2">Update</button>

</form>
    <%
        String addError = (String) application.getAttribute("addError");

       if (addError != null && !addError.equals("")) {
       %>
         <span style="color:red"> ${applicationScope.addError} </span><br/>
        <% }
    %>
        <a href='./membersPage.jsp'>Back</a>
       </body>
       </div>
         </div>
</html>