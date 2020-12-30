<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Change password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/app.js" defer></script>
    <script src="scripts/formValidation.js" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>
    <script src="scripts/passwordStrengthMeter.js" defer></script>
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Change password"/>
        </jsp:include>

        <main>
            <c:if test="${not empty error}">
                <div id="alert-danger">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </div>
            </c:if>

            <form method="POST" action="Controller?command=ChangePasswordConfirmation" novalidate>
                <div><p><label for="currentPassword">Current password</label><input type="password" id="currentPassword" name="currentPassword" required></p></div>

                <div><p><label for="newPassword">New password</label><input type="password" id="newPassword" name="newPassword" required></p></div>
                <p>Password strength: <span id="password-strength-text">please enter a password</span></p>
                <meter max="4" id="password-strength-meter"></meter>

                <div><p><label for="newPasswordConfirmation">New password confirmation</label><input type="password" id="newPasswordConfirmation" name="newPasswordConfirmation" required></p></div>
                <p><input type="submit" id="changePassword" value="Change password"></p>
            </form>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>