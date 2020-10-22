<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Change password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Change password confirmation"></jsp:param>
        </jsp:include>

        <main>
            <p>Are you sure you want to change your password?</p>
            <form method="POST" action="Controller?command=ChangePassword&newPassword=<c:out value="${newPassword}"/>&newPasswordConfirmation=<c:out value="${newPasswordConfirmation}"/>">
                <p><input type="submit" id="no" value="No, take me to the homepage"></p>
                <p><input type="submit" id="yes" name="yes" value="Yes, change my password"></p>
            </form>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>