<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Loan" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Loan Applications <br/>


<br/>

<br/>
<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h2 class="card-title">Manage Loans</h2>
                    <div class="table-responsive">
                      <table class="table table-dark">
                        <thead>
                        <br/>Approve Loan <a href='./unapprovedLoans.jsp'>Online Loans</a><br/>
                        <br/>Add Loan <a href='./loan.jsp'>New Loan</a><br/>
                          <tr>

                            <th> Loaner username </th>
                            <th> loan id </th>
                            <th> loaned amount </th>
                            <th> interest amount</th>
                            <th> total pay </th>
                            <th> payment period(months)</th>
                            <th> status </th>
                            <th>action </th>

                          </tr>
                        </thead>


                       <tbody>
                       <c:forEach items ="${loanView.list}" var = "loan">
                       <tr>
                               <td>${loan.username}</td>
                               <td>${loan.id}</td>
                               <td>${loan.loanAmount}</td>
                               <td>${loan.interest}</td>
                               <td>${loan.totalPay}</td>
                               <td>${loan.period}</td>
                               <td> ${loan.status}</a>
                               <td><a href="./updateLoan.jsp?id=${loan.id}">Edit</a>


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