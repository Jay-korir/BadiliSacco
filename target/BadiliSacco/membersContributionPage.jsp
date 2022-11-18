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




<jsp:useBean id = "contributionView"  class = "view.ContributionView" />
<h1>${username}</h1>

<c:set var="contribution" value = "${contributionView.getUserContribution(username)}" />


<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Contribution <a href='./memberContribution.jsp'>Add Contribution</a><br/>

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
                       <th> Contribution type </th>
                       <th> Contributed amount</th>
                       <th> id </th>
                       <th>date</th>
                          </tr>
                        </thead>


                       <tbody>
                       <tr><td>${fn:toUpperCase(contribution.username)}</td>
                              <td>${contribution.month}</td>
                              <td>${contribution.type}</td>
                              <td>${contribution.amount}</td>
                               <td>${contribution.id}</td>
                               <td>${contribution.timeCreated}</td>
                           </tr>

                       </tbody>

                      </table>



<a href='./dashboard.jsp'>Back</a><br/>
 </div>
         </div>
                </div>
              </div>
</body>
</html>