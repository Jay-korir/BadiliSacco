<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />
<%@ page import = "model.Members" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<jsp:useBean id = "membersController"  class = "controller.MembersController" />



<h1>${application.applicationLabel}</h1>
<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Members <a href='./addMembers.jsp'>Add Members</a><br/>
<br/>




<div class="col-lg-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h2 class="card-title">Members</h2>

                      <table class="table table-hover">
                        <thead>
                          <tr>
                            <th>firstname</th>
                                <th> lastname</th>
                                <th> username</th>
                                <th>email</th>
                                <th>phone</th>
                                <th>action</th>


                          </tr>
                        </thead>
                        <%

                             List<Members> members = membersController.list((Connection) application.getAttribute("myConnection"), new Members());
                            for (Members member : members) {
                        %>
                          <tbody>
                         <tr>
                                <td><%= member.getFirstName() %></td>
                                <td><%= member.getLastName() %></td>
                                <td><%= member.getUserName() %></td>
                                <td><%= member.getEmail() %></td>
                                <td><%= member.getPhone() %></td>
                                <td><a href="./edit">Edit</a>  | <a href="./delete">Delete</a></td>

                            </tr>

                        </tbody>
                        <% } %>
                      </table>

 <a href='./dashboard.jsp'>Back</a><br/>
   </div>

</div>
</div>
</body>
</html>