<%@ include file="header.jsp" %>
               <h2>Contribution</h2>
          <form action="./contribution" method="post">
          <table>
               <tr> <td>Username: </td> <td> <input type="text" name="username"> </td> </tr>
              <tr> <td> month: </td> <td> <input type="text" name="month"> </td> </tr>
               <tr> <td> amount: </td> <td> <input type="text" name="amount"> </td> </tr>


            <tr> <td> <input class="button" type="submit" value="Submit"></tr>
          </table>
           </form>
    <%
        String loginError = (String) application.getAttribute("loginError");

       if (loginError != null && !loginError.equals("")) {
       %>
         <span style="color:red"> <%= application.getAttribute("loginError") %> </span><br/>
        <% }
    %>
        <a href='./dashboard.jsp'>Back</a>
       </body>
       </div>
         </div>
</html>