<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Loan" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


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
                            <th> action <th>
                          </tr>
                        </thead>


                       <tbody>
                       <c:forEach items ="${loanController.list}" var = "loan">
                       <tr>
                               <td>${loan.username}</td>
                               <td>${loan.loanAmount}</td>
                               <td>${loan.interest}</td>
                               <td>${loan.totalPay}</td>
                               <td>${loan.period}</td>
                               <td> ${loan.status}</a>
                               <td><a href="./approveLoan?id=${loan.id}">Accept</a>  | <a href="./decline?id=${loan.id}">Decline</a></td>

                           </tr>

                       </tbody>
                       </c:forEach>
                      </table>



<a href='./dashboard.jsp'>Back</a><br/>
 </div>
         </div>
                </div>
              </div>
</body>
</html>