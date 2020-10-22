<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Dashboard"></jsp:param>
        </jsp:include>

        <main>
            <div class="dashboard-main">
                <c:choose>
                    <c:when test="${not empty user}">
                        <p>User id: <c:out value="${user.userid}"/></p>
                        <p>Email: <c:out value="${user.email}"/></p>
                        <p>First name: <c:out value="${user.firstName}"/></p>
                        <p>Last name: <c:out value="${user.lastName}"/></p>

                        <br>

                        <form method="POST" action="Controller?command=ChangePasswordForm">
                            <p><input type="submit" id="changePassword" value="Change your password"></p>
                        </form>
                        <c:if test="${user.userid ne 'admin'}">
                            <form method="POST" action="Controller?command=DeleteAccountConfirmation&userId=<c:out value="${user.userid}"/>">
                                <p><input type="submit" id="deleteAccount" value="Delete your account"></p>
                            </form>
                        </c:if>

                        <br>

                        <p>Registered on: <c:out value="${user.getRegisterDateTimeToString()}"/></p>
                        <p>Last logged in on: <c:out value="${user.getlastLoginDateTimeToString()}"/></p>
                        <p>Amount of times logged in: <c:out value="${user.getAmountOfTimesLoggedIn()}"/></p>
                    </c:when>
                    <c:otherwise>
                        <p>You are not logged in!</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>