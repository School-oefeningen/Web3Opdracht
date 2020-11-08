<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Register test result</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Register test result"></jsp:param>
        </jsp:include>

        <main>
            <c:if test="${not empty errors}">
                <div class="alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li><c:out value="${error}"/></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <c:if test="${not empty testResult}">
                <p>Your last positive Covid-19 test has been registered on: <c:out value="${testResult.getDateAsString()}"/></p>
                <br>
            </c:if>
            <form method="POST" action="Controller?command=AddRegisterTestResult">
                <p><label for="date">Date</label><input type="date" id="date" name="date" required></p>
                <p><input type="submit" id="registerTestResult" value="Register new positive test result"></p>
            </form>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>