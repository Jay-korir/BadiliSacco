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
                            <th> id </th>
                            <th>date</th>
                            <th>action </th>
                          </tr>
                        </thead>

                       <tbody>
                       <c:forEach items ="${contributionController.list}" var = "contribution">
                      <tr>
                               <td>${fn:toUpperCase(contribution.username)}</td>
                               <td>${contribution.month}</td>
                               <td>${contribution.amount}</td>
                                <td>${contribution.id}</td>
                                <td>${contribution.timeCreated}</td>
                               <td><a href="./updateContribution.jsp?id=${contribution.id}">Edit</a>  | <a href="./deleteContribution?id=${contribution.id}">Delete</a></td>

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