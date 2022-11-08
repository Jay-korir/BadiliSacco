<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<%@ page import = "model.Members" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>



<h1>${application.applicationLabel}</h1>
<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Members <a href='./addMembers.jsp'>Add Members</a><br/>
<br/>




<div class="col-lg-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h2 class="card-title">Members</h2>

                      <table class="table table-hover">
                        <thead>
                          <tr>
                            <th>firstname</th>
                                <th> lastname</th>
                                <th> username</th>
                                <th>email</th>
                                <th>phone</th>
                                <th>userType </th>
                                <th>Date </th>
                                <th>action</th>


                          </tr>
                        </thead>

                          <tbody>
                          <c:forEach items ="${memberController.list}" var = "member">
                         <tr>
                                <td>${member.firstname}</td>
                                <td>${member.lastname}</td>
                                 <td>${member.username}</td>
                                <td>${member.email}</td>
                                <td>${member.phone}</td>
                                <td>${member.userType}</td>
                                <td>${member.timeCreated}</td>
                                <td><a href="./updateMember?id=${member.id}">Edit</a>  | <a href="./deleteMember?id=${member.id}">Delete</a></td>

                            </tr>
                          </c:forEach>
                        </tbody>

                      </table>

 <a href='./dashboard.jsp'>Back</a><br/>
   </div>

</div>
</div>
</body>
</html>