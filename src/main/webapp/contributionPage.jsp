<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />
<%@ page import = "model.Contribution" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>


<jsp:useBean id = "contributionController"  class = "controller.ContributionController" />

<%!

	String contributionName;
	String monthName;

%>




<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Contribution <a href='./addContribution.jsp'>Add Contribution</a><br/>

<br/>
<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h2 class="card-title"> Contributions </h2>
                    <div class="table-responsive">
                      <table class="table table-dark">
                        <thead>
                          <tr>

                            <th> Contributor username </th>
                            <th> Contribution month </th>
                            <th> Contributed amount</th>
                            <th> actions </th>
                          </tr>
                        </thead>
                        <%
                            List<Contribution> contributions = contributionController.list((Connection) application.getAttribute("myConnection"), new Contribution());
                            for (Contribution contribution : contributions) {
                             contributionName = contribution.getUsername();
                             monthName = contribution.getMonth();
                        %>

                       <tbody>
                       <tr>
                               <td><%= contribution.getUsername() %></td>
                               <td><%= contribution.getMonth() %></td>
                               <td><%= contribution.getAmount() %></td>
                               <td><a href="./updateContribution?username=<%= contribution.getUsername() %>">Edit</a>  | <a href="./deleteContribution?username=<%= contribution.getUsername() %>">Delete</a></td>
                           </tr>

                       </tbody>
                       <% } %>
                      </table>



<a href='./dashboard.jsp'>Back</a><br/>
 </div>
         </div>
                </div>
              </div>
</body>
</html>