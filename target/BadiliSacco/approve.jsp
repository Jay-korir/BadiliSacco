<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header1.tld" %>
<cht:Header1></cht:Header1>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<h1>${applicationScope.applicationLabel}</h1>
<h2>Check Loan Approve</h2>
    <form action="./approve" method="post">
<% Long a = Long.valueOf(Integer.parseInt(request.getParameter("id")));
 pageContext.setAttribute("id", a);
%>
<jsp:useBean id = "loanView"  class = "view.LoanView" />


   <c:set var="loan" value = "${loanView.getUserLoan(id)}" />

   <div class="row">
   <div class="col-md-12 grid-margin stretch-card">
   <div class="card">
     <div class="card-body">
         <div class="form-group">
    <table class="table table-hover">
                            <thead>
                              <tr>
                                <label>Contribution wallet</label>
                                    <input type="text" name="amount" class="form-control" id="exampleInputPassword1" placeholder="Amount" value = "${contributionView.getTotalContri()}">
                                <label>Member contribution</label>
                                        <input type="text" name="amount" class="form-control" id="exampleInputPassword1" placeholder="Amount" value = "${loan.userContribution}">
                                 <label>loan applied</label>
                                    <input type="text" name="loanAmount" class="form-control" id="exampleInputPassword1" placeholder="Amount" value = "${loan.loanAmount}">
     <input type="hidden" name="id" class="form-control" id="exampleInputUsername1" placeholder="id" value="${loan.id}">
<input type="hidden" name="username" class="form-control" id="exampleInputUsername1" placeholder="Username" value="${loan.username}">
<input type="hidden" name="period" class="form-control" id="exampleInputUsername1" placeholder="Username" value="${loan.period}">
<input type="hidden" name="purpose" class="form-control" id="exampleInputUsername1" placeholder="Username" value="${loan.purpose}">
                                    </tr>
                            </thead>



                          </table>
<button type="submit" class="btn btn-primary mr-2">Accept</button>
    </form>


<a href='./userDashboard.jsp'>Back</a>

 <%@ taglib prefix="cft" uri="WEB-INF/tlds/footer1.tld" %>
 <cft:Footer1></cft:Footer1>