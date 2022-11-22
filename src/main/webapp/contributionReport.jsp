<%@ page isELIgnored="false" %>

<%@ taglib prefix="jc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<%@ page import = "model.Contribution" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>





<% String type = request.getParameter("type");
pageContext.setAttribute("type", type);
%>

<jsp:useBean id = "contributionView"  class = "view.ContributionView" />


<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h2 class="card-title"> ${type} Contributions </h2>
                    <div class="table-responsive">
                      <table class="table table-dark">
                        <thead>
                          <tr>

                            <th> Contributor username </th>
                            <th> Contributor IdNumber </th>
                            <th> Contribution month </th>
                            <th> Contribution type </th>
                            <th> Contributed amount</th>
                            <th> transaction id </th>
                            <th>date</th>

                          </tr>
                        </thead>

                       <tbody>
                       <c:forEach items ="${contributionView.getContributionReport(type)}" var = "contribution">
                      <tr>
                               <td>${fn:toUpperCase(contribution.username)}</td>
                               <td>${contribution.idNumber}</td>
                               <td>${contribution.month}</td>
                               <td>${contribution.type}</td>
                               <td>${contribution.amount}</td>
                                <td>${contribution.id}</td>
                                <td>${contribution.timeCreated}</td>

                           </tr>
                        </c:forEach>
                       </tbody>

                      </table>



<a href='./reports.jsp'>Back</a><br/>
 </div>
         </div>
                </div>
              </div>
 </body>
</html>