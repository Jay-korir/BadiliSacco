<%@ page isELIgnored="false" %>

<%@ taglib prefix="jc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<%@ page import = "model.Loan" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>





<% String status = request.getParameter("status");
pageContext.setAttribute("status", status);
%>

<jsp:useBean id = "loanView"  class = "view.LoanView" />
<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h2 class="card-title">${status} Loans</h2>
                    <div class="table-responsive">
                      <table class="table table-dark">
                        <thead>

                          <tr>

                            <th> Loaner username </th>
                            <th> loan id </th>
                            <th> loaned amount </th>
                            <th> interest amount</th>
                            <th> total pay </th>
                            <th> payment period(months)</th>
                            <th> purpose </th>
                            <th> status </th>

                          </tr>
                        </thead>


                       <tbody>
                       <c:forEach items ="${loanView.getLoanReport(status)}" var = "loan">
                       <tr>
                               <td>${loan.username}</td>
                               <td>${loan.id}</td>
                               <td>${loan.loanAmount}</td>
                               <td>${loan.interest}</td>
                               <td>${loan.totalPay}</td>
                               <td>${loan.period}</td>
                               <td>${loan.purpose}</td>
                               <td> ${loan.status}</a>


                           </tr>

                       </tbody>
                       </c:forEach>
                      </table>



<a href='./reports.jsp'>Back</a><br/>
 </div>
         </div>
                </div>
              </div>
</body>
</html>