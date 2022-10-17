<%@ include file="header1.jsp" %>
<h3 class="card-title text-left mb-3">Register</h3>
<form action= "./register" method = "post">
  <div class="form-group">
    <label>First Name</label>
    <input type="text" name= "FirstName">
  </div>
  <div class="form-group">
    <label>Last Name</label>
    <input type="text" name= "LastName">
  </div>
  <div class="form-group">
    <label>Username</label>
    <input type="text" name= "UserName">
  </div>
  <div class="form-group">
    <label>Email</label>
    <input type="email" name= "Email">
  </div>
  <div class="form-group">
     <label>Phone</label>
    <input type="tel" name= "Phone">
  </div>
  <div class="form-group">
    <label>Password</label>
    <input type="password" name= "Password">
  </div>
  <div class="form-group">
    <label> Confirm Password</label>
    <input type="password" name= "ConfirmPassword">
  </div>
  <div class="form-group d-flex align-items-center justify-content-between">
    <div class="form-check">
      <label class="form-check-label">
        <input type="checkbox" class="form-check-input"> Remember me </label>
    </div>
    <a href="#" class="forgot-pass">Forgot password</a>
  </div>
  <div class="text-center">
    <button type="submit" class="btn btn-primary btn-block enter-btn">Register</button>
  </div>

  <p class="sign-up text-center">Already have an Account?<a href='./login.jsp'>Login</a></p>
  <p class="terms">By creating an account you are accepting our<a href="#"> Terms & Conditions</a></p>
</form>
  <%
    String loginError = (String) application.getAttribute("loginError");
      if (loginError != null && !loginError.equals("")) {
        %>
        <span style="color:red"> <%= application.getAttribute("loginError") %> </span><br/>
         <% }
  %>
<%@  include file = "footer1.jsp" %>