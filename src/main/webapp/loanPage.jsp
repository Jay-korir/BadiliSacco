<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Loan" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>


<jsp:useBean id = "loanController"  class = "controller.LoanController" />

<%!

	String contributionName;
	String monthName;

%>




<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Contribution <a href='./wmcontri/addContribution.jsp'>Add Contribution</a><br/>

<br/>
<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h2 class="card-title"> Contributions </h2>
                    <div class="table-responsive">
                      <table class="table table-dark">
                        <thead>
                          <tr>

                            <th> Loaner username </th>
                            <th> loaned amount </th>
                            <th> interest amount</th>
                            <th> total pay </th>
                            <th> payment period(months)</th>
                            <th> status </th>
                          </tr>
                        </thead>
                        <%
                            List<Loan> loans = loanController.list((Connection) application.getAttribute("myConnection"), new Loan());
                            for (Loan loan : loans) {


                        %>

                       <tbody>
                       <tr>
                               <td><%= loan.getUsername() %></td>
                               <td><%= loan.getLoanAmount() %></td>
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