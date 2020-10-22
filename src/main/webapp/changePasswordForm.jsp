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
            <jsp:param name="page" value="Change password"></jsp:param>
        </jsp:include>

        <main>
            <c:if test="${not empty error}">
                <div class="alert-danger">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </div>
            </c:if>

            <form method="POST" action="Controller?command=ChangePasswordConfirmation">
                <p><label for="currentPassword">Current password:</label><input type="password" id="currentPassword"
                                                                                name="currentPassword" required></p>
                <p><label for="newPassword">New password:</label><input type="password" id="newPassword"
                                                                        name="newPassword" required></p>
                <p><label for="newPasswordConfirmation">New password confirmation:</label><input type="password"
                                                                                                 id="newPasswordConfirmation"
                                                                                                 name="newPasswordConfirmation"
                                                                                                 required></p>
                <p><input type="submit" id="changePassword" value="Change password"></p>
            </form>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>