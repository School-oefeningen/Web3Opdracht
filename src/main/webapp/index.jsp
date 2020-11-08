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
            <jsp:param name="page" value="Home"/>
        </jsp:include>

        <main class="main">
            <c:choose>
                <c:when test="${not empty user}">
                    <p>Welcome, <c:out value="${user.firstName}"/>!</p>
                    <br>
                    <form action="Controller?command=Logout" method="POST">
                        <p><input type="submit" id="logout" value="Log out"></p>
                    </form>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty error}">
                        <div class="alert-danger">
                            <ul>
                                <li><c:out value="${error}"/></li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${not empty succes}">
                        <div class="alert-succes">
                            <ul>
                                <li><c:out value="${succes}"/></li>
                            </ul>
                        </div>
                    </c:if>
                    <form action="Controller?command=Login" method="POST">
                        <p><label for="userId">Your user id</label><input type="text" id="userId" name="userId" value="<c:out value="${userIdPrevious}"/>" required></p>
                        <p><label for="password">Your password</label><input type="password" id="password" name="password" required></p>
                        <p><input type="submit" id="login" value="Log in"></p>
                    </form>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
    <footer> &copy; Lorenzo Catalano, r0790963</footer>
</div>
</body>
</html>