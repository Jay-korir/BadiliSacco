<!DOCTYPE html>
<html>
    <head>
        <script type='text/javascript' src='./js/sample.js'></script>
    </head>
    <body bgcolor="Lightskyblue" style="margin: auto; width: 220px;">
     <div class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
        <%!
            int one = 1;
            int two = 2;


        %>
              <h3><%= application.getAttribute("applicationLabel") %></h3>
        <%!
            int one2 = 1;
            int two2 = 2;


        %>


        <%
            int results = one+two;
            results = results * 10;

            if (results>3) {
        %>
            To Register <a href='./register.jsp'>Register</a><br/>
            To Login <a href='./login.jsp'>Login</a><br/>

        <% } else { %>

            To Register <a href='./register.jsp'>Register</a><br/>
            To Login <a href='./login.jsp'>Login</a><br/>

        <% } %>



       </div>
    </body>
</html>