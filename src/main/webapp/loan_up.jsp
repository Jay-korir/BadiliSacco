<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header1.tld" %>
<cht:Header1></cht:Header1>


   <jsp:useBean id ="loan" class = "model.Loan" />
   <jsp:setProperty name ="loan" property="*" />

   <h1>
   Thank you <br/>
   Username : ${loan.username} <br/>
for your loan application keep checking for approval. </h1>

<a href='./userDashboard.jsp'>Back</a>

 <%@ taglib prefix="cft" uri="WEB-INF/tlds/footer1.tld" %>
 <cft:Footer1></cft:Footer1>