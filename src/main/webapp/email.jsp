<%@ page isELIgnored="false" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<cht:Header></cht:Header>
<h1>${applicationScope.applicationLabel}</h1>
   <h2>Send Information</h2>
<form action="email" method="post">
        <table border="0" width="35%" align="center">
            <h2>Send New E-mail</h2>
   <div class="row">
   <div class="col-md-6 grid-margin stretch-card">
   <div class="card">
     <div class="card-body">
         <div class="form-group">
   <label for="exampleInputUsername1">Recipient address</label>
   <input type="text" name="recipient" class="form-control" id="exampleInputUsername1" placeholder="recipient">
     </div>
     <div class="form-group">
       <label for="exampleInputUsername1"> Subject</label>
       <input type="text" name="subject" class="form-control" id="exampleInputUsername1" placeholder="subject" >
         </div>
       <div class="form-group">

            <textarea rows="10" cols="39" name="content"></textarea>

           <button type="submit" class="btn btn-primary mr-2">Submit</button>
           </div>




    </form>
</body>
</html>