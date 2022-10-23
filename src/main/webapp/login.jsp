<%@ page isELIgnored="false" %>
<jsp:include page="header1.jsp" />
    <h3 class="card-title text-left mb-3">Login</h3>
    <form action="./login" method="post">
    <div class="form-group">
       <label ><b>Username</b></label>
        <input type="text" name= "username" class="form-control p_input" required placeholder="Enter Username">
        </div>
         <div class="form-group">
          <label ><b>Password</b></label>
           <input type="pass" name= "password" class="form-control p_input" required placeholder="Enter Password">
           </div>
      <div class="form-group d-flex align-items-center justify-content-between">
        <div class="form-check">
          <label class="form-check-label">
            <input type="checkbox" class="form-check-input"> Remember me </label>
        </div>
        <a href="#" class="forgot-pass">Forgot password</a>
      </div>
      <div class="text-center">
        <button type="submit" class="btn btn-primary btn-block enter-btn">Login</button>
      </div>

      <p class="sign-up">Don't have an Account?<a href='./register.jsp'>Register</a></p>
    </form>
     <%
               String loginError = (String) application.getAttribute("loginError");
                if (loginError != null && !loginError.equals("")) {
                        %>
                          <span style="color:red">  ${applicationScope.loginError} </span><br/>
                        <% } %>
 <jsp:include page = "footer1.jsp" />