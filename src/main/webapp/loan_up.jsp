<%@ page isELIgnored="false" %>
<jsp:include page="header1.jsp" />


   <jsp:useBean id ="loan" class = "model.Loan" />
   <jsp:setProperty name ="loan" property="*" />

   <h1>
   Thank you <br/>
   Username : ${loan.username} <br/>
for your loan application keep checking for approval. </h1>

<a href='./userDashboard.jsp'>Back</a>

 <jsp:include page = "footer1.jsp" />