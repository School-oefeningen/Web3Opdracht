<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/app.js" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>
    <script src="scripts/passwordStrengthMeter.js" defer></script>
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Register"/>
        </jsp:include>

        <main>
            <c:if test="${not empty errors}">
                <div id="alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li><c:out value="${error}"/></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form method="POST" action="Controller?command=Add" novalidate="novalidate">
                <p><label for="userId">User id</label><input type="text" id="userId" name="userId" value="<c:out value="${userIdPrevious}"/>" required></p>
                <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" value="<c:out value="${firstNamePrevious}"/>" required></p>
                <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="<c:out value="${lastNamePrevious}"/>" required></p>
                <p><label for="email">Email</label><input type="email" id="email" name="email" value="<c:out value="${emailPrevious}"/>" required></p>

                <p><label for="password">Password</label><input type="password" id="password" name="password" value="<c:out value="${passwordPrevious}"/>" required></p>
                <p>Password strength: <span id="password-strength-text">please enter a password</span></p>
                <meter max="4" id="password-strength-meter"></meter>

                <p><input type="submit" id="signUp" value="Sign Up"></p>
            </form>
        </main>
    </div>
    <footer> &copy; Lorenzo Catalano, r0790963</footer>
</div>
</body>
</html>
