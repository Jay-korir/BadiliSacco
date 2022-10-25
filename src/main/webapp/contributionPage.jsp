<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Contribution" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<jsp:useBean id = "contributionController"  class = "controller.ContributionController" />

<%!

	String contributionName;
	String monthName;

%>

<td><c:set var="str" value="Welcome to the sacco system badili sacco"/>
       <c:if test="${fn:contains(str, 'badili')}">
       <p>welcome to badili sacco</p>
        </c:if></td>
<c:set var="date" value="25-10-2022" />

<fmt:parseDate value="${date}" var="parsedDate"  pattern="dd-MM-yyyy" />
<p><c:out value="${parsedDate}" /></p>

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
                            <th>date</th>
                          </tr>
                        </thead>
                        <%
                            List<Contribution> contributions = contributionController.list((Connection) application.getAttribute("myConnection"), new Contribution());
                             pageContext.setAttribute("contributions",contributions);
                        %>
                       <tbody>
                       <c:forEach items ="${contributions}" var = "contribution">
                      <tr>
                               <td>${fn:toUpperCase(contribution.username)}</td>
                               <td>${contribution.month}</td>
                               <td>${contribution.amount}</td>
                               <td><a href="./updateContribution?username=${contribution.id}">Edit</a>  | <a href="./deleteContribution?username=${contribution.username}">Delete</a></td>

                           </tr>
                        </c:forEach>
                       </tbody>

                      </table>



<a href='./dashboard.jsp'>Back</a><br/>
 </div>
         </div>
                </div>
              </div>
</body>
</html>