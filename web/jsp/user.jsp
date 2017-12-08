<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add new user</title>
</head>
<body>


<form method="POST" action='UserController' name="frmAddUser">
    First Name : <input
        type="text" name="firstName"
        value="${user.firstName}"> <br/>
    Last Name : <input
        type="text" name="lastName"
        value="${user.lastName}"> <br/>
    Speciality : <input
        type="text" name="speciality"
        value="${user.speciality}"> <br/>
    Email : <input type="text" name="email"
                   value="${user.email}"> <br/> <input
        type="submit" value="Submit"/>
</form>
</body>
</html>