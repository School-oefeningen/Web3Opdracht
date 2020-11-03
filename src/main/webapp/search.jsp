<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Search"></jsp:param>
        </jsp:include>

        <main>
            <c:choose>
                <c:when test="${not empty error}">
                    <p id="error"><c:out value="${error}"/></p>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty testResult}">
                        <p>Your last positive Covid-19 test has been registered on: <c:out value="${testResult.getDateAsString()}"></c:out></p>
                        <br>
                    </c:if>

                    <table>
                        <tr>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Date</th>
                            <th>Phone number</th>
                            <th>Email</th>
                        </tr>
                        <c:forEach var="contact" items="${contacts}">
                            <tr>
                                <td><c:out value="${contact.firstName}"/></td>
                                <td><c:out value="${contact.lastName}"/></td>
                                <td><c:out value="${contact.getDateAsString()}"/></td>
                                <td><c:out value="${contact.phoneNumber}"/></td>
                                <td><c:out value="${contact.email}"/></td>
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