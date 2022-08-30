<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="ISO-8859-1">
<title>Register User</title>
</head>
<body>
   <h3 style="color:red;">Register New User</h3>
   <div id="registerEmployee">
   <form:form action="/register" method="post" modelAttribute="user">
        <p>
        <label>Enter username</label>
        <form:input path="username"/>
         </p>
         <p>
         <label>Enter password</label>
         <form:input path="password"/>
         </p>
        
<p>
<label>Enter UserRole</label>
    <form:select path="userrole">
     <form:option value="ROLE_USER">User</form:option>
     <form:option value="ROLE_ADMIN">Admin</form:option>
     <form:option value="ROLE_MANAGER">Manager</form:option>
     <form:option value="ROLE_DEV">Developer</form:option>
     <form:option value="ROLE_QA">Tester</form:option>
    </form:select>
   </p>

         <input type="SUBMIT" value="Submit">
   </form:form>
   </div>
</body>
</html>