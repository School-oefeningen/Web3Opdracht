<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Postive test results</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Postive test results"></jsp:param>
        </jsp:include>

        <main class="main">
            <c:choose>
                <c:when test="${testResults.size() <= 0}">
                    <p id="error">No postive test results to show.</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>User id</th>
                            <th>Date</th>
                        </tr>
                        <c:forEach var="testResult" items="${testResults}">
                            <tr>
                                <td id="userId"><c:out value="${testResult.userId}"/></td>
                                <td><c:out value="${testResult.getDateAsString()}"/></td>
                            </tr>
                        </c:forEach>
                        <caption>Contacts Overview</caption>
                    </table>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
    <footer> &copy; Lorenzo Catalano, r0790963</footer>
</div>
</body>
</html>