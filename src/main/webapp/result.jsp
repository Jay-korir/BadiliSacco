<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
<h1>Result</h1>
</head>
<body>
    <center>
        <h3><%=request.getAttribute("Message")%></h3>
    </center>
</body>
</html>